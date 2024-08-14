package de.daver.build.hub.world;

import de.daver.build.hub.sql.DatabaseConnection;
import de.daver.build.hub.sql.transformer.ResultSetTransformer;
import de.daver.build.hub.sql.transformer.StringResultSetTransformer;
import de.daver.build.hub.util.FileUtils;
import de.daver.build.hub.util.UniverseHubPermissions;
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
    
    private DatabaseConnection dbConnection;

    private WorldMaster() {
        this.invitedUsers = new HashMap<>();
        this.worlds = new HashMap<>();
        this.worldContainer = Bukkit.getWorldContainer();
        this.importContainer = new File(Bukkit.getWorldContainer().getParentFile(), "importWorlds"); //TODO Variable path
    }

    public World createWorld(String id, WorldGenerator generator, boolean load) {
        World world = new World(id, generator, new ArrayList<>());
        world.setLoaded(load);
        worlds.put(id, world);
        dbConnection.execute("INSERT INTO");
        createBukkitWorld(world);
        if(!load) unloadBukkitWorld(world);
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
        dbConnection.execute("DELETE FROM ");
        return worlds.remove(world.getId()) != null;
    }

    public boolean loadWorld(String id) {
        World world = getWorld(id);
        if(world == null) return false;
        world.setLoaded(true);
        createBukkitWorld(world);
        dbConnection.execute("UPDATE SET");
        return true;
    }

    public boolean unloadWorld(String id) {
        World world = getWorld(id);
        if(world == null) return false;
        world.setLoaded(false);
        unloadBukkitWorld(world);
        dbConnection.execute("UPDATE SET");
        return true;
    }

    public List<UUID> addAllowed(String id, UUID...userIds) {
        World world = getWorld(id);
        List<UUID> failedUserIds = new ArrayList<>();
        if(world == null) return failedUserIds;
        for(UUID uuid : userIds) {
            if(!world.addUser(uuid)) failedUserIds.add(uuid);
        }
        dbConnection.execute("UPDATE SET");
        return failedUserIds;
    }

    public List<UUID> removeAllowed(String id, UUID...userIds) {
        World world = getWorld(id);
        List<UUID> failedUserIds = new ArrayList<>();
        if(world == null) return failedUserIds;
        for(UUID uuid : userIds) {
            if(!world.removeUser(uuid)) failedUserIds.add(uuid);
        }
        dbConnection.execute("UPDATE SET");
        return failedUserIds;
    }

    public void inviteToWorld(UUID uuid, String worldId) {
        invitedUsers.put(uuid, worldId);
    }

    public boolean isPermitted(Player player, String newWorldId) {
        if(player.hasPermission(UniverseHubPermissions.WORLD_ENTER_ALL)) return true;
        World world = getWorld(newWorldId);
        if(world == null) return false;
        if(world.isAllowed(player.getUniqueId())) return true;
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
        if(!world.isLoaded()) loadWorld(worldId);
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
        this.worlds.putAll(dbConnection
                .executeQuery("SELECT * FROM",
                        ResultSetTransformer.hashMap(new StringResultSetTransformer("id"),
                                new WorldResultSetTransformer())));
        this.worlds.values().stream()
                .filter(World::isLoaded)
                .forEach(this::createBukkitWorld);
    }

    public void terminate() {
        worlds.values().stream().filter(World::isLoaded).forEach(this::unloadBukkitWorld);
    }

    public static WorldMaster get() {
        if(instance == null) instance = new WorldMaster();
        return instance;
    }
}
