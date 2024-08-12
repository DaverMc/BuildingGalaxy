package de.daver.build.universe.world;

import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

public class World {

    private final String id;
    private final WorldGenerator generator;
    private boolean loaded;
    private long idleTimestamp = -1;
    private final List<UUID> allowedUsers;

    protected World(String id, WorldGenerator generator, List<UUID> allowedUsers) {
        this.id = id;
        this.generator = generator;
        this.allowedUsers = allowedUsers;
    }

    public String getId() {
        return id;
    }

    public WorldGenerator getGenerator() {
        return generator;
    }

    public boolean isLoaded() {
        return this.loaded;
    }

    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

    public boolean isInIdle() {
        if(!isLoaded()) return false;
        return  idleTimestamp < 0;
    }

    public void setIdle() {
        idleTimestamp = System.currentTimeMillis();
    }

    public long getIdleTimestamp() {
        return idleTimestamp;
    }

    public boolean addUser(UUID uuid) {
        if(allowedUsers.contains(uuid)) return false;
        allowedUsers.add(uuid);
        return true;
    }

    public boolean removeUser(UUID uuid) {
        return allowedUsers.remove(uuid);
    }

    public boolean isAllowed(UUID uuid) {
        return allowedUsers.contains(uuid);
    }

    public boolean isPermitted(Player uuid) {
        return WorldMaster.get().isPermitted(uuid, id);
    }
}
