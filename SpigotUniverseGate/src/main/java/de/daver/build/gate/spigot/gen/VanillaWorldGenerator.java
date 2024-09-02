package de.daver.build.gate.spigot.gen;

import de.daver.build.hub.api.world.World;
import de.daver.build.hub.api.world.WorldGenerator;
import de.daver.build.hub.core.world.WorldImpl;
import org.bukkit.WorldCreator;

public class VanillaWorldGenerator implements WorldGenerator {

    @Override
    public boolean generate(World world) {
        WorldCreator creator = new WorldCreator(world.getId());
        return creator.createWorld() != null;
    }

    @Override
    public String id() {
        return "vanilla";
    }
}
