package de.daver.build.universe.listener;

import de.daver.build.universe.world.WorldMaster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;


public class WorldChangeListener implements Listener {


    @EventHandler
    public void onWorldChange(PlayerChangedWorldEvent event) {
        Player player = event.getPlayer();
        WorldMaster.get().isPermitted(player, player.getWorld().getName());
        player.teleport(event.getFrom().getSpawnLocation()); //TODO Maybe teleport to a warp point
    }

}
