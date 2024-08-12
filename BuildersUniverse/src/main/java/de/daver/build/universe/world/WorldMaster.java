package de.daver.build.universe.world;

import de.daver.build.universe.Main;
import de.daver.build.universe.util.FileUtils;
import de.daver.build.universe.util.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.*;

public class WorldMaster {

    private static WorldMaster instance;
    private final Map<String, World> worlds;
    private final Map<UUID, String> invitedUsers;
    private final File worldContainer;
    private final File importContainer;

    private WorldMaster() {
        this.invitedUsers = new HashMap<>();
        this.worlds = new HashMap<>();
        this.worldContainer = Bukkit.getWorldContainer();
        this.importContainer = new File(Bukkit.getWorldContainer().getParentFile(), "importWorlds"); //TODO Variable path
    }

    public World createWorld(String id, WorldGenerator generator, boolean load) {
        World world = new World(id, generator, new ArrayList<>());
        worlds.put(id, world);
        Main.instance().getDatabaseConnection().insert("");
        createBukkitWorld(world);
        unloadBukkitWorld(world);
        if(load) WorldLoaderService.get().loadWorld(world.getId());
        return world;
    }

    public World importWorld(String filePath, String id, WorldGenerator generator, boolean load) {
        if(!FileUtils.copyFile(new File(importContainer, filePath), worldContainer)) return null;
        return createWorld(id, generator, load);
    }

    private void createBukkitWorld(World world) {
        WorldCreator creator = world.getGenerator().toWorldCreator(world.getId());
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
        world.setLoaded(true);
        createBukkitWorld(world);
        Main.instance().getDatabaseConnection().update("");
        return true;
    }

    public boolean unloadWorld(String id) {
        World world = getWorld(id);
        if(world == null) return false;
        world.setLoaded(false);
        unloadBukkitWorld(world);
        Main.instance().getDatabaseConnection().update("");
        return true;
    }

    public List<UUID> addAllowed(String id, UUID...userIds) {
        World world = getWorld(id);
        List<UUID> failedUserIds = new ArrayList<>();
        if(world == null) return failedUserIds;
        for(UUID uuid : userIds) {
            if(!world.addUser(uuid)) failedUserIds.add(uuid);
        }
        Main.instance().getDatabaseConnection().update("");
        return failedUserIds;
    }

    public List<UUID> removeAllowed(String id, UUID...userIds) {
        World world = getWorld(id);
        List<UUID> failedUserIds = new ArrayList<>();
        if(world == null) return failedUserIds;
        for(UUID uuid : userIds) {
            if(!world.removeUser(uuid)) failedUserIds.add(uuid);
        }
        Main.instance().getDatabaseConnection().update("");
        return failedUserIds;
    }

    public void inviteToWorld(UUID uuid, String worldId) {
        invitedUsers.put(uuid, worldId);
    }

    //For better usage maybe move to World.class
    public boolean isPermitted(Player player, String newWorldId) {
        if(player.hasPermission(Permissions.WORLD_CHANGE_BYPASS)) return true;
        World world = getWorld(newWorldId);
        if(world == null) return false;
        if(world.isPermitted(player.getUniqueId())) return true;
        if(invitedUsers.containsKey(player.getUniqueId())) {
            String invitedWorldId = invitedUsers.get(player.getUniqueId());
            return invitedWorldId.equals(newWorldId);
        }
        return false;
    }

    public boolean teleportToWorld(Player player, String worldId) {
        World world = getWorld(worldId);
        if(world == null) return false;
        if(!isPermitted(player, worldId)) return false;
        if(!world.isLoaded()) WorldLoaderService.get().loadWorld(worldId);
        org.bukkit.World bukkitWorld = Bukkit.getWorld(worldId);
        if(bukkitWorld == null) return false;
        player.teleport(bukkitWorld.getSpawnLocation());
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
