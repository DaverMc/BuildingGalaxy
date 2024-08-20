package de.daver.build.universe;

import de.daver.build.hub.UniverseHub;
import de.daver.build.hub.api.annotation.*;
import de.daver.build.hub.api.gate.UniverseAdapter;
import de.daver.build.hub.api.lang.Language;
import de.daver.build.universe.command.world.WorldCommand;
import de.daver.build.universe.util.BUUNKeys;

import java.util.logging.Level;
import java.util.logging.Logger;

@PluginName("Builders Universe")
@PluginVersion("v_1.0.0-preview")
@PluginAuthors({"DaverMc", "FusselTV"})
@PluginPlattform({Plattform.SPIGOT, Plattform.MINESTOM})
public class BuildersUniverse implements UniverseAdapter {

    @Override
    public void onInitialisation() {
        Logger logger = UniverseHub.gate().getLogger();
        logger.log(Level.INFO, "Initialising Builders Universe...");
        logger.log(Level.INFO, "Initialising Language System...");
        UniverseHub.getLanguageManager().init(BUUNKeys.class, Language.GERMAN, Language.ENGLISH);
        logger.log(Level.INFO, "Registering Commands...");
        registerCommands();
        logger.log(Level.INFO, "Connecting to Database...");
        connectToDatabase();
        logger.log(Level.INFO, "Starting world system...");
        startWorldSystem();
        logger.log(Level.INFO, "Finished initialising Builders Universe");
    }

    private void registerCommands() {
        UniverseHub.gate().getCommandRegistrator().registerCommand(WorldCommand.world());
    }

    private void connectToDatabase() {

    }

    private void startWorldSystem() {

    }

    @Override
    public void onTermination() {
        Logger logger = UniverseHub.gate().getLogger();
        logger.log(Level.INFO, "Terminating Builders Universe...");

        logger.log(Level.INFO, "Finished Terminating Builders Universe");
    }
}
