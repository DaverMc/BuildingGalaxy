package de.daver.build.universe;

import de.daver.build.universe.sql.DatabaseConnection;
import de.daver.build.universe.util.MessageBuilder;
import de.daver.build.universe.world.WorldMaster;
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

    public static final String NAME = "BuildersUniverse";
    public static final String LOG_PREFIX = NAME.toLowerCase();

    private static Main plugin;

    @Override
    public void onEnable() {
        MessageBuilder.setDefaultPrefix(LOG_PREFIX);

        //Database Connection herstellen
        WorldMaster.get().init();
        getLogger().info(MessageBuilder.createMessage("Plugin enabled"));
    }

    @Override
    public void onDisable() {;
        plugin = null;
        WorldMaster.get().terminate();
        //Database Connection schließen
        getLogger().info(MessageBuilder.createMessage("Plugin disabled"));
    }

    public static Main instance() {
        return plugin;
    }

    public DatabaseConnection getDatabaseConnection() {
        return null;
    }
}
