package de.daver.build.universe.world;

import org.bukkit.WorldCreator;

public interface WorldGenerator {


    WorldCreator toWorldCreator(String name);

}
