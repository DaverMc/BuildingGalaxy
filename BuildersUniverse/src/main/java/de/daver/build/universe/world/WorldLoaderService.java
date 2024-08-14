package de.daver.build.universe.world;

import de.daver.build.hub.world.World;
import de.daver.build.hub.world.WorldMaster;
import de.daver.build.universe.Main;
import org.bukkit.Bukkit;

import java.util.Comparator;
import java.util.stream.Stream;

public class WorldLoaderService implements Runnable{

    //This constant defines how many worlds can be loaded at the same time
    private static long loadedWorldsLimit = 5;
    //Defines the time a world can be in idle before being unloaded
    private static long idleWorldTime = 1000;
    //Defines the intervall with which the scheduler is unloading idle worlds
    private static long taskIntervall = 1000;

    private static WorldLoaderService instance;

    //Functional variables and constants
    private final WorldMaster worldMaster;
    //The Bukkit Scheduler Task-Id of the automated unloading
    private int task = -1;

    private WorldLoaderService(WorldMaster worldMaster) {
        this.worldMaster = worldMaster;
    }

    /*
    Checks if the number off current loaded worlds would be exceeded
    Checks if there are any idle worlds.
    If there aren't idle worlds, it loads the world beyond the limit
    otherwise all idle worlds beginning with the longest idle time
    will be unloaded until the limit is reached or there are no mor idle world.
    */
    public boolean loadWorld(String id) {
        World world = worldMaster.getWorld(id);
        if (world == null || world.isLoaded()) return false;
        while(getIdleWorlds().count() >= loadedWorldsLimit) {
            World idleWorld = findOldestIdleWorld();
            if(idleWorld != null) worldMaster.unloadWorld(idleWorld.getId());
            else break;
        }
        return worldMaster.loadWorld(id);
    }

    private World findOldestIdleWorld() {
        return getIdleWorlds().min(Comparator.comparingLong(World::getIdleTimestamp))
                .orElse(null);
    }

    public Stream<World> getIdleWorlds() {
        return worldMaster.getWorlds().stream().filter(World::isInIdle);
    }

    public void start() {
        if(task != -1) return;
        this.task = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.instance(), this,
                0, taskIntervall);
    }

    public void stop() {
        if(task == -1) return;
        Bukkit.getScheduler().cancelTask(task);
        this.task = -1;
    }

    @Override
    public void run() {
        getIdleWorlds().forEach(world -> {
           if(System.currentTimeMillis() - world.getIdleTimestamp() < idleWorldTime) return;
           worldMaster.unloadWorld(world.getId());
        });
    }

    public static void configure(long loadedWorldsLimit, long idleWorldTime, long taskIntervall) {
        WorldLoaderService.loadedWorldsLimit = loadedWorldsLimit;
        WorldLoaderService.idleWorldTime = idleWorldTime;
        WorldLoaderService.taskIntervall = taskIntervall;
    }

    public static WorldLoaderService get() {
        if(instance == null) instance = new WorldLoaderService(WorldMaster.get());
        return instance;
    }
}
