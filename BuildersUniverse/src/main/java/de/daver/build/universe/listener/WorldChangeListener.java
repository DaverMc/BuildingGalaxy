package de.daver.build.universe.listener;

import de.daver.build.hub.world.WorldMaster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;


public class WorldChangeListener implements Listener {


    @EventHandler
    public void onWorldChange(PlayerChangedWorldEvent event) {
        Player player = event.getPlayer();
        if(WorldMaster.get().isPermitted(player, player.getWorld().getName())) {
            player.sendMessage("You're not allowed to enter this world!"); //TODO Format Message>
            return;
        }
        player.teleport(event.getFrom().getSpawnLocation()); //TODO Maybe teleport to a warp point
    }

}
