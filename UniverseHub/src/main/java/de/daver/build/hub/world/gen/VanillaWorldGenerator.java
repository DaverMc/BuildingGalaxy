package de.daver.build.hub.world.gen;

import de.daver.build.hub.world.WorldGenerator;
import org.bukkit.WorldCreator;

public class VanillaWorldGenerator implements WorldGenerator {
    @Override
    public WorldCreator toWorldCreator(String name) {
        return new WorldCreator(name);
    }
}
