package de.daver.build.hub.api.world;

import java.util.List;
import java.util.UUID;

public interface World {

    String getId();

    WorldGenerator getGenerator();

    boolean isLoaded();

    void setLoaded(boolean loaded);

    boolean isInIdle();

    void setIdle();

    long getIdleTimestamp();

    boolean addUser(UUID uuid);

    boolean removeUser(UUID uuid);

    boolean isAllowed(UUID uuid);

    List<UUID> getAllowedUsers();

    boolean isOpen();
}
