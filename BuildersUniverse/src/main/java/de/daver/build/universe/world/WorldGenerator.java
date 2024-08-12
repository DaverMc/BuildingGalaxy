package de.daver.build.universe.world;

import org.bukkit.WorldCreator;
import org.bukkit.generator.ChunkGenerator;

public interface WorldGenerator {


    WorldCreator toWorldCreator(String name);

}
