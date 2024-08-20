package de.daver.build.gate.spigot.user;

import de.daver.build.hub.UniverseHub;
import de.daver.build.hub.api.util.User;
import de.daver.build.hub.core.user.UserManagerImpl;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class SpigotUserManager extends UserManagerImpl {

    public SpigotUserManager() {
        super();
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new SpigotListener(), (Plugin) UniverseHub.gate());
    }

    private class SpigotListener implements Listener {

        @EventHandler
        public void onJoin(PlayerJoinEvent event) {
            Player player = event.getPlayer();
            User user = new SpigotUser(player);
            addOnlineUser(user);
        }

        @EventHandler
        public void onQuit(PlayerQuitEvent event) {
            Player player = event.getPlayer();
            User user = new SpigotUser(player);
            removeOnlineUser(user);
        }

    }
}
