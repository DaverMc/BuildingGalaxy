package de.daver.build.universe;

import de.daver.build.universe.sql.DatabaseConnection;
import de.daver.build.universe.util.MessageBuilder;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static final String NAME = "BuildersUniverse";
    public static final String LOG_PREFIX = NAME.toLowerCase();

    private static Main plugin;

    @Override
    public void onDisable() {;
        plugin = null;
        getLogger().info(MessageBuilder.createMessage("Plugin disabled"));
    }

    @Override
    public void onEnable() {
        MessageBuilder.setDefaultPrefix(LOG_PREFIX);

        getLogger().info(MessageBuilder.createMessage("Plugin enabled"));
    }

    public static Main instance() {
        return plugin;
    }

    public DatabaseConnection getDatabaseConnection() {
        return null;
    }
}
