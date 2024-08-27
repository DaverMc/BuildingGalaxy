package de.daver.build.hub.api.world;

import de.daver.build.hub.api.sql.DatabaseConnection;
import de.daver.build.hub.api.util.User;

import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface WorldMaster {

    void setWorldContainer(File worldContainer);

    void setDatabaseConnection(DatabaseConnection connection);

    World createWorld(String id, WorldGenerator generator, boolean load);

    World importWorld(String filePath, String id, WorldGenerator generator, boolean load);

    boolean deleteWorld(String id);

    boolean loadWorld(String id);

    boolean unloadWorld(String id);

    List<UUID> addAllowed(String id, UUID...userIds);

    List<UUID> removeAllowed(String id, UUID...userIds);

    void inviteToWorld(UUID uuid, String worldId);

    boolean isPermitted(User user, String newWorldId);

    boolean teleportToWorld(User user, String worldId);

    World getWorld(String id);

    Collection<World> getWorlds();

    void loadAll();

    void clear();

    WorldGenerator getGenerator(String id);

    void addGenerator(WorldGenerator generator);

    Collection<WorldGenerator> getGenerators();
}
