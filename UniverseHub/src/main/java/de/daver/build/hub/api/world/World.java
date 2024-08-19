package de.daver.build.hub.api.world;

import de.daver.build.hub.api.util.User;
import de.daver.build.hub.world.WorldMasterImpl;

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

}
