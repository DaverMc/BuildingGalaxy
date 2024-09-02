package de.daver.build.hub.core.world;

import de.daver.build.hub.UniverseHub;
import de.daver.build.hub.api.sql.DatabaseConnection;
import de.daver.build.hub.api.util.User;
import de.daver.build.hub.api.world.WorldGenerator;
import de.daver.build.hub.api.world.WorldMaster;
import de.daver.build.hub.api.sql.ResultSetTransformer;
import de.daver.build.hub.api.sql.ColumnType;
import de.daver.build.hub.api.sql.Condition;
import de.daver.build.hub.api.sql.Statement;
import de.daver.build.hub.core.sql.transformer.StringResultSetTransformer;
import de.daver.build.hub.core.util.FileUtils;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class WorldMasterImpl implements WorldMaster {


    private static final String TABLE_NAME = "worlds";

    private final Map<String, de.daver.build.hub.api.world.World> worlds;
    private final Map<UUID, String> invitedUsers;
    private final Map<String, WorldGenerator> generators;

    private File worldContainer;
    private File importContainer;
    private DatabaseConnection dbConnection;

    public WorldMasterImpl() {
        this.invitedUsers = new HashMap<>();
        this.worlds = new HashMap<>();
        this.generators = new HashMap<>();
    }

    @Override
    public void setWorldContainer(File worldContainer) {
        this.worldContainer = worldContainer;
        this.importContainer = new File(worldContainer.getParentFile(), "importWorlds"); //TODO Variable path
    }

    @Override
    public void setDatabaseConnection(DatabaseConnection connection) {
        this.dbConnection = connection;

        String sqlQuery = Statement.createTable(TABLE_NAME)
                .ifNotExists(true)
                .column("id", new ColumnType.Varchar(255), true)
                .column("generatorId", new ColumnType.Varchar(255))
                .column("loaded", ColumnType.BOOLEAN)
                .column("open", ColumnType.BOOLEAN)
                .column("allowedUsers", ColumnType.TEXT)
                .build();

        this.dbConnection.execute(sqlQuery);
    }

    private String allowedUsersToString(de.daver.build.hub.api.world.World world) {
        return world.getAllowedUsers().stream()
                .map(UUID::toString)
                .collect(Collectors.joining(","));
    }

    public de.daver.build.hub.api.world.World createWorld(String id, WorldGenerator generator, boolean load) {
        de.daver.build.hub.api.world.World world = new WorldImpl(id, generator, new ArrayList<>());
        world.setLoaded(load);
        worlds.put(id, world);
        String sqlQuery = Statement.insertInto(TABLE_NAME)
                .column("id")
                .column("generatorId")
                .column("loaded")
                .column("open")
                .column("allowedUsers")
                .build();

        dbConnection.execute(sqlQuery, world.getId(),
                world.getGenerator().id(),
                world.isLoaded(),
                world.isOpen(),
                allowedUsersToString(world));

        UniverseHub.gate().getWorldSlave().createWorld(world);
        if(!load) UniverseHub.gate().getWorldSlave().unloadWorld(world);
        return world;
    }

    public de.daver.build.hub.api.world.World importWorld(String filePath, String id, WorldGenerator generator, boolean load) {
        if(!FileUtils.copyFile(new File(importContainer, filePath), worldContainer)) return null;
        return createWorld(id, generator, load);
    }

    public boolean deleteWorld(String id) {
        de.daver.build.hub.api.world.World world = getWorld(id);
        if(world == null) return false;
        UniverseHub.gate().getWorldSlave().unloadWorld(world);
        if(!FileUtils.deleteFile(new File(worldContainer, world.getId()))) return false;
        String sqlQuery = Statement.deleteFrom(TABLE_NAME)
                        .where(new Condition.Bool("id", "=", id))
                        .build();
        dbConnection.execute(sqlQuery);

        return worlds.remove(world.getId()) != null;
    }

    public boolean loadWorld(String id) {
        de.daver.build.hub.api.world.World world = getWorld(id);
        if(world == null) return false;
        world.setLoaded(true);
        UniverseHub.gate().getWorldSlave().createWorld(world);
        String sqlQuery = Statement.update(TABLE_NAME)
                .column("loaded")
                .where(new Condition.Bool("id", "=", id))
                .build();
        return dbConnection.execute(sqlQuery, true);
    }

    public boolean unloadWorld(String id) {
        de.daver.build.hub.api.world.World world = getWorld(id);
        if(world == null) return false;
        world.setLoaded(false);
        UniverseHub.gate().getWorldSlave().unloadWorld(world);
        String sqlQuery = Statement.update(TABLE_NAME)
                .column("loaded")
                .where(new Condition.Bool("id", "=", id))
                .build();
        return dbConnection.execute(sqlQuery, false);
    }

    public List<UUID> addAllowed(String id, UUID...userIds) {
        de.daver.build.hub.api.world.World world = getWorld(id);
        List<UUID> failedUserIds = new ArrayList<>();
        if(world == null) return failedUserIds;
        for(UUID uuid : userIds) if(!world.addUser(uuid)) failedUserIds.add(uuid);
        String sqlQuery = Statement.update(TABLE_NAME)
                .column("allowedUsers")
                .where(new Condition.Bool("id", "=", id))
                .build();
        dbConnection.execute(sqlQuery, allowedUsersToString(world));
        return failedUserIds;
    }

    public List<UUID> removeAllowed(String id, UUID...userIds) {
        de.daver.build.hub.api.world.World world = getWorld(id);
        List<UUID> failedUserIds = new ArrayList<>();
        if(world == null) return failedUserIds;
        for(UUID uuid : userIds) {
            if(!world.removeUser(uuid)) failedUserIds.add(uuid);
        }
        String sqlQuery = Statement.update(TABLE_NAME)
                .column("allowedUsers")
                .where(new Condition.Bool("id", "=", id))
                .build();
        dbConnection.execute(sqlQuery, allowedUsersToString(world));
        return failedUserIds;
    }

    public void inviteToWorld(UUID uuid, String worldId) {
        invitedUsers.put(uuid, worldId);
    }

    public boolean isPermitted(User user, String newWorldId) {
        if(user.hasPermission("world.enter-all")) return true;
        de.daver.build.hub.api.world.World world = getWorld(newWorldId);
        if(world == null) return false;
        if(world.isAllowed(user.getUUID())) return true;
        if(invitedUsers.containsKey(user.getUUID())) {
            String invitedWorldId = invitedUsers.get(user.getUUID());
            return invitedWorldId.equals(newWorldId);
        }
        return false;
    }

    public boolean teleportToWorld(User user, String worldId) {
        de.daver.build.hub.api.world.World world = getWorld(worldId);
        if(world == null) return false;
        if(!isPermitted(user, worldId)) return false;
        if(!world.isLoaded()) loadWorld(worldId);
        return UniverseHub.gate().getWorldSlave().sendTo(user, world);
    }

    public de.daver.build.hub.api.world.World getWorld(String id) {
        return worlds.get(id);
    }

    public Collection<de.daver.build.hub.api.world.World> getWorlds() {
        return this.worlds.values();
    }

    public void loadAll() {
        String sqlQuery = Statement.select()
                .from(TABLE_NAME)
                .build();

        var keyTransformer = new StringResultSetTransformer("id");
        var valueTransformer = new WorldResultSetTransformer();
        Map<String, de.daver.build.hub.api.world.World> worlds = dbConnection.executeQuery(sqlQuery, ResultSetTransformer.hashMap(keyTransformer, valueTransformer));
        this.worlds.putAll(worlds);

        if(this.worlds.isEmpty()) return; //TODO
        this.worlds.values().stream()
                .filter(de.daver.build.hub.api.world.World::isLoaded)
                .forEach(UniverseHub.gate().getWorldSlave()::createWorld);
    }

    public void clear() {
        worlds.values().stream()
                .filter(de.daver.build.hub.api.world.World::isLoaded)
                .forEach(UniverseHub.gate().getWorldSlave()::unloadWorld);
    }

    @Override
    public WorldGenerator getGenerator(String id) {
        return this.generators.get(id);
    }

    @Override
    public void addGenerator(WorldGenerator generator) {
        this.generators.put(generator.id().toLowerCase(), generator);
    }

    @Override
    public Collection<WorldGenerator> getGenerators() {
        return this.generators.values();
    }
}
