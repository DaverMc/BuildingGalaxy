package de.daver.build.universe.gate;

import de.daver.build.gate.spigot.SpigotUniverseGate;
import de.daver.build.hub.UniverseHub;
import de.daver.build.universe.BuildersUniverse;

public class BuildersUniverseSpigotGate extends SpigotUniverseGate {

    public BuildersUniverseSpigotGate() {
        super(new BuildersUniverse());
    }

    @Override
    public void onEnable() {
        UniverseHub.instance().onInitialisation();
    }

    @Override
    public void onDisable() {
        UniverseHub.instance().onTermination();
    }
}
