package de.daver.build.gate.spigot.gen;

import de.daver.build.hub.api.world.WorldGenerator;
import de.daver.build.hub.core.world.WorldImpl;

public class VanillaWorldGenerator implements WorldGenerator {

    @Override
    public boolean generate(WorldImpl world) {
        return false;
    }
}
