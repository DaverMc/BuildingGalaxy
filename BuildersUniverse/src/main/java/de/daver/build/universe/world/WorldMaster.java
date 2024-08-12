package de.daver.build.universe.world;

import de.daver.build.universe.util.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
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
        Bukkit.createWorld(createWorldCreator(world));
        worlds.put(id, world);
        return world;
    }

    private WorldCreator createWorldCreator(World world) {
        return new WorldCreator(world.getId()).generator(world.getGenerator());
    }

    public boolean deleteWorld(String id) {
        World world = getWorld(id);
        if(world == null) return false;
        Bukkit.unloadWorld(world.getId(), true);
        if(!FileUtils.deleteFile(new File(worldContainer, world.getId()))) return false;
        return worlds.remove(world.getId()) != null;
    }

    public World importWorld(String filePath, String id, WorldGenerator generator) {
        if(!FileUtils.copyFile(new File(importContainer, filePath), worldContainer)) return null;
        return createWorld(id, generator);
    }

    public boolean loadWorld(String id) {
        World world = getWorld(id);
        if(world == null) return false;
        Bukkit.createWorld(createWorldCreator(world));
        return true;
    }

    public boolean unloadWorld(String id) {
        World world = getWorld(id);
        if(world == null) return false;
        Bukkit.unloadWorld(world.getId(), true);
        return true;
    }

    public World getWorld(String id) {
        return worlds.get(id);
    }

    public Collection<World> getWorlds() {
        return this.worlds.values();
    }

    public void init() {
        //Load all Spigot last used worlds from db
        WorldLoaderService.get().start();
    }

    public void terminate() {
        WorldLoaderService.get().stop();
        //Unload and save all worlds
    }

    public static WorldMaster get() {
        if(instance == null) instance = new WorldMaster();
        return instance;
    }
}
