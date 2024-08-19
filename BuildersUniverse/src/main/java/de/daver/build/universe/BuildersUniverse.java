package de.daver.build.universe;

import de.daver.build.hub.UniverseHub;
import de.daver.build.hub.api.annotation.*;
import de.daver.build.hub.api.gate.UniverseAdapter;
import de.daver.build.hub.api.lang.Language;
import de.daver.build.universe.command.world.WorldCommand;
import de.daver.build.universe.util.BUUNKeys;

@PluginName("Builders Universe")
@PluginVersion("v_1.0.0-preview")
@PluginAuthors({"DaverMc", "FusselTV"})
@PluginPlattform({Plattform.SPIGOT, Plattform.MINESTOM})
public class BuildersUniverse implements UniverseAdapter {

    @Override
    public void onInitialisation() {
        System.out.println("Initialising Builders Universe...");
        System.out.println("Initialising Language System...");
        UniverseHub.getLanguageManager().init(BUUNKeys.class, Language.GERMAN, Language.ENGLISH);
        System.out.println("Registering Commands...");
        registerCommands();
        System.out.println("Connecting to Database...");
        connectToDatabase();
        System.out.println("Starting world system...");
        startWorldSystem();
        System.out.println("Finished initialising Builders Universe");
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
        System.out.println("Terminating Builders Universe...");

        System.out.println("Finished Terminating Builders Universe");
    }
}
