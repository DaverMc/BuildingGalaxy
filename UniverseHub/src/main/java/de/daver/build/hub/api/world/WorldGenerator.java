package de.daver.build.hub.api.world;

import de.daver.build.hub.core.world.WorldImpl;

public interface WorldGenerator {

    boolean generate(WorldImpl world);

    String id();

}
