package de.daver.build.gate.spigot.gen;

import de.daver.build.hub.api.world.WorldGenerator;
import de.daver.build.hub.world.WorldImpl;
import org.bukkit.Material;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.generator.WorldInfo;

import java.util.Random;

public class VoidWorldGenerator extends ChunkGenerator implements WorldGenerator {

    @Override
    public void generateNoise(WorldInfo worldInfo, Random random, int chunkX, int chunkZ, ChunkData chunkData) {
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                for (int y = 0; y < 384; y++) chunkData.setBlock(x, y, z, Material.AIR);
            }
        }
    }

    @Override
    public boolean generate(WorldImpl world) {
        return false;
    }
}
