package de.daver.build.universe.world;

public class World {

    private final String id;
    private final WorldGenerator generator;
    private boolean loaded;
    private long idleTimestamp = -1;

    public World(String id, WorldGenerator generator) {
        this.id = id;
        this.generator = generator;
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


}
