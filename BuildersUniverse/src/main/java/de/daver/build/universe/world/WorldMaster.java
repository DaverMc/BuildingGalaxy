package de.daver.build.universe.world;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class WorldMaster {

    private final Map<String, World> worlds;

    public WorldMaster() {
        this.worlds = new HashMap<>();
    }

    public World createWorld(String id, WorldGenerator generator) {
        World world = new World(id, generator);
        worlds.put(id, world);
        return world;
    }

    public boolean deleteWorld(String id) {
        World world = worlds.remove(id);
        //Unload Spigot World
        //Delete Dir
        return world != null;
    }

    public World importWorld(String filePath, String id, WorldGenerator generator) {
        //Copy Dir from ImportFolder
        return createWorld(id, generator);
    }

    public boolean loadWorld(String id) {
        World world = getWorld(id);
        if(world == null) return false;
        //Unload Spigot World
        return true;
    }

    public boolean unloadWorld(String id) {
        World world = getWorld(id);
        if(world == null) return false;
        //Unload Spigot World
        return true;
    }

    public World getWorld(String id) {
        return worlds.get(id);
    }

    public Collection<World> getWorlds() {
        return this.worlds.values();
    }
}
