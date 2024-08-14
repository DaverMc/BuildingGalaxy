package de.daver.build.hub.world;

import org.bukkit.WorldCreator;

public interface WorldGenerator {


    WorldCreator toWorldCreator(String name);

}
