package de.daver.build.universe;

import de.daver.build.gate.spigot.SpigotUniverseGate;
import de.daver.build.hub.UniverseHub;
import org.bukkit.plugin.PluginLoadOrder;
import org.bukkit.plugin.java.annotation.plugin.LoadOrder;
import org.bukkit.plugin.java.annotation.plugin.Plugin;
import org.bukkit.plugin.java.annotation.plugin.author.Author;

@Plugin(
        name = "Name",
        version = "1.2")
@Author("DaverMc")
@LoadOrder(PluginLoadOrder.STARTUP)
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
