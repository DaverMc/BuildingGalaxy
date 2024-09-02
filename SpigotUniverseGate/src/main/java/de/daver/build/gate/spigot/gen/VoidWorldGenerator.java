package de.daver.build.gate.spigot.gen;

import de.daver.build.hub.api.world.World;
import de.daver.build.hub.api.world.WorldGenerator;
import de.daver.build.hub.core.world.WorldImpl;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.WorldCreator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.generator.WorldInfo;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class VoidWorldGenerator extends ChunkGenerator implements WorldGenerator {

    @Override
    public void generateNoise(@NotNull WorldInfo worldInfo, @NotNull Random random, int chunkX, int chunkZ, @NotNull ChunkData chunkData) {
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                for (int y = 0; y < 384; y++) chunkData.setBlock(x, y, z, Material.AIR);
            }
        }
    }

    @Override
    public boolean generate(World world) {
        WorldCreator creator = new WorldCreator(world.getId());
        creator.generator(this);
        return creator.createWorld() != null;
    }

    @Override
    public String id() {
        return "void";
    }
}
