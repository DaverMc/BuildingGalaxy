package de.daver.build.hub.api.world;


public interface WorldGenerator {

    boolean generate(World world);

    String id();

}
