package de.daver.build.universe.world;

import de.daver.build.universe.Main;
import de.daver.build.universe.util.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorldMaster {

    private static WorldMaster instance;
    private final Map<String, World> worlds;
    private final File worldContainer;
    private final File importContainer;

    private WorldMaster() {
        this.worlds = new HashMap<>();
        this.worldContainer = Bukkit.getWorldContainer();
        this.importContainer = new File(Bukkit.getWorldContainer().getParentFile(), "importWorlds"); //TODO Variable path
    }

    public World createWorld(String id, WorldGenerator generator) {
        World world = new World(id, generator);
        createBukkitWorld(world);
        worlds.put(id, world);
        Main.instance().getDatabaseConnection().insert("");
        return world;
    }

    public World importWorld(String filePath, String id, WorldGenerator generator) {
        if(!FileUtils.copyFile(new File(importContainer, filePath), worldContainer)) return null;
        return createWorld(id, generator);
    }

    private void createBukkitWorld(World world) {
        WorldCreator creator = new WorldCreator(world.getId()).generator(world.getGenerator());
        Bukkit.createWorld(creator);
    }

    private void unloadBukkitWorld(World world) {
        Bukkit.unloadWorld(world.getId(), true);
    }

    public boolean deleteWorld(String id) {
        World world = getWorld(id);
        if(world == null) return false;
        unloadBukkitWorld(world);
        if(!FileUtils.deleteFile(new File(worldContainer, world.getId()))) return false;
        Main.instance().getDatabaseConnection().delete("");
        return worlds.remove(world.getId()) != null;
    }

    public boolean loadWorld(String id) {
        World world = getWorld(id);
        if(world == null) return false;
        createBukkitWorld(world);
        Main.instance().getDatabaseConnection().update("");
        return true;
    }

    public boolean unloadWorld(String id) {
        World world = getWorld(id);
        if(world == null) return false;
        unloadBukkitWorld(world);
        Main.instance().getDatabaseConnection().update("");
        return true;
    }

    public World getWorld(String id) {
        return worlds.get(id);
    }

    public Collection<World> getWorlds() {
        return this.worlds.values();
    }

    public void init() {
        List<World> worlds = Main.instance().getDatabaseConnection().select("");
        for(World world : worlds) {
            if(world.isLoaded()) createBukkitWorld(world);
            this.worlds.put(world.getId(), world);
        }
        WorldLoaderService.get().start();
    }

    public void terminate() {
        WorldLoaderService.get().stop();
        worlds.values().stream().filter(World::isLoaded).forEach(this::unloadBukkitWorld);
    }

    public static WorldMaster get() {
        if(instance == null) instance = new WorldMaster();
        return instance;
    }
}
