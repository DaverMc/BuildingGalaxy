package de.daver.build.universe;

import de.daver.build.hub.sql.DatabaseConnection;
import de.daver.build.hub.world.WorldMaster;
import org.bukkit.plugin.PluginLoadOrder;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.annotation.plugin.LoadOrder;
import org.bukkit.plugin.java.annotation.plugin.Plugin;
import org.bukkit.plugin.java.annotation.plugin.author.Author;

@Plugin(
        name = "Name",
        version = "1.2")
@Author("DaverMc")
@LoadOrder(PluginLoadOrder.STARTUP)
public class Main extends JavaPlugin {

    private static Main plugin;

    @Override
    public void onEnable() {
        WorldMaster.get().init();
    }

    @Override
    public void onDisable() {;
        plugin = null;
        WorldMaster.get().terminate();
    }

    public static Main instance() {
        return plugin;
    }

    public DatabaseConnection getDatabaseConnection() {
        return null;
    }
}
