package de.daver.build.hub.core.world;

import de.daver.build.hub.UniverseHub;
import de.daver.build.hub.api.sql.DatabaseConnection;
import de.daver.build.hub.api.util.User;
import de.daver.build.hub.api.world.World;
import de.daver.build.hub.api.world.WorldGenerator;
import de.daver.build.hub.api.world.WorldMaster;
import de.daver.build.hub.api.sql.ResultSetTransformer;
import de.daver.build.hub.core.sql.transformer.StringResultSetTransformer;
import de.daver.build.hub.core.util.FileUtils;

import java.io.File;
import java.util.*;

public class WorldMasterImpl implements WorldMaster {

    private final Map<String, World> worlds;
    private final Map<UUID, String> invitedUsers;

    private File worldContainer;
    private File importContainer;
    private DatabaseConnection dbConnection;

    public WorldMasterImpl() {
        this.invitedUsers = new HashMap<>();
        this.worlds = new HashMap<>();
        }

    @Override
    public void setWorldContainer(File worldContainer) {
        this.worldContainer = worldContainer;
        this.importContainer = new File(worldContainer.getParentFile(), "importWorlds"); //TODO Variable path

    }

    @Override
    public void setDatabaseConnection(DatabaseConnection connection) {
        this.dbConnection = connection;
    }

    public World createWorld(String id, WorldGenerator generator, boolean load) {
        World world = new WorldImpl(id, generator, new ArrayList<>());
        world.setLoaded(load);
        worlds.put(id, world);
        dbConnection.execute("INSERT INTO");
        UniverseHub.gate().getWorldSlave().createWorld(world);
        if(!load) UniverseHub.gate().getWorldSlave().unloadWorld(world);
        return world;
    }

    public World importWorld(String filePath, String id, WorldGenerator generator, boolean load) {
        if(!FileUtils.copyFile(new File(importContainer, filePath), worldContainer)) return null;
        return createWorld(id, generator, load);
    }

    public boolean deleteWorld(String id) {
        World world = getWorld(id);
        if(world == null) return false;
        UniverseHub.gate().getWorldSlave().unloadWorld(world);
        if(!FileUtils.deleteFile(new File(worldContainer, world.getId()))) return false;
        dbConnection.execute("DELETE FROM ");
        return worlds.remove(world.getId()) != null;
    }

    public boolean loadWorld(String id) {
        World world = getWorld(id);
        if(world == null) return false;
        world.setLoaded(true);
        UniverseHub.gate().getWorldSlave().createWorld(world);
        dbConnection.execute("UPDATE SET");
        return true;
    }

    public boolean unloadWorld(String id) {
        World world = getWorld(id);
        if(world == null) return false;
        world.setLoaded(false);
        UniverseHub.gate().getWorldSlave().unloadWorld(world);
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

    public boolean isPermitted(User user, String newWorldId) {
        if(user.hasPermission("world.enter-all")) return true;
        World world = getWorld(newWorldId);
        if(world == null) return false;
        if(world.isAllowed(user.getUUID())) return true;
        if(invitedUsers.containsKey(user.getUUID())) {
            String invitedWorldId = invitedUsers.get(user.getUUID());
            return invitedWorldId.equals(newWorldId);
        }
        return false;
    }

    public boolean teleportToWorld(User user, String worldId) {
        World world = getWorld(worldId);
        if(world == null) return false;
        if(!isPermitted(user, worldId)) return false;
        if(!world.isLoaded()) loadWorld(worldId);
        return UniverseHub.gate().getWorldSlave().sendTo(user, world);
    }

    public World getWorld(String id) {
        return worlds.get(id);
    }

    public Collection<World> getWorlds() {
        return this.worlds.values();
    }

    public void loadAll() {
        this.worlds.putAll(dbConnection
                .executeQuery("SELECT * FROM",
                        ResultSetTransformer.hashMap(new StringResultSetTransformer("id"),
                                new WorldResultSetTransformer())));
        this.worlds.values().stream()
                .filter(World::isLoaded)
                .forEach(UniverseHub.gate().getWorldSlave()::createWorld);
    }

    public void clear() {
        worlds.values().stream()
                .filter(World::isLoaded)
                .forEach(UniverseHub.gate().getWorldSlave()::unloadWorld);
    }
}
