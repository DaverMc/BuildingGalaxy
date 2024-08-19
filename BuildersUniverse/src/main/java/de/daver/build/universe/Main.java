package de.daver.build.universe;

import de.daver.build.hub.core.sql.DatabaseConnectionImpl;
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
    }

    @Override
    public void onDisable() {;
        plugin = null;
    }

    public static Main instance() {
        return plugin;
    }

    public DatabaseConnectionImpl getDatabaseConnection() {
        return null;
    }
}
