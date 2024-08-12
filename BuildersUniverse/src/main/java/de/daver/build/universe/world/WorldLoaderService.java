package de.daver.build.universe.world;

import java.util.Comparator;
import java.util.stream.Stream;

public class WorldLoaderService implements Runnable{

    //This constant defines how many worlds can be loaded at the same time
    private final long loadedWorldsLimit;
    private final long idleWorldTime;

    private final WorldMaster worldMaster;

    private WorldLoaderService(long loadedWorldsLimit, WorldMaster worldMaster, long idleWorldTime) {
        this.loadedWorldsLimit = loadedWorldsLimit;
        this.worldMaster = worldMaster;
        this.idleWorldTime = idleWorldTime;
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

    @Override
    public void run() {
        getIdleWorlds().forEach(world -> {
           if(System.currentTimeMillis() - world.getIdleTimestamp() < idleWorldTime) return;
           worldMaster.unloadWorld(world.getId());
        });
    }
}
