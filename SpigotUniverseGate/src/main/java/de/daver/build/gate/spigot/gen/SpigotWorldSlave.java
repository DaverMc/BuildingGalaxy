package de.daver.build.gate.spigot.gen;

import de.daver.build.hub.api.gate.WorldSlave;
import de.daver.build.hub.api.util.User;
import de.daver.build.hub.api.world.World;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class SpigotWorldSlave implements WorldSlave {

    @Override
    public void createWorld(World world) {
        world.getGenerator().generate(world);
    }

    @Override
    public void unloadWorld(World world) {
        Bukkit.unloadWorld(world.getId(), true);
    }

    @Override
    public boolean sendTo(User user, World world) {
        Player player = Bukkit.getPlayer(user.getUUID());
        if(player == null) return false;
        org.bukkit.World bukkitWorld = Bukkit.getWorld(world.getId());
        return player.teleport(bukkitWorld.getSpawnLocation());
    }
}
