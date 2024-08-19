package de.daver.build.hub.api.world;

import de.daver.build.hub.world.WorldImpl;

public interface WorldGenerator {

    boolean generate(WorldImpl world);

}
