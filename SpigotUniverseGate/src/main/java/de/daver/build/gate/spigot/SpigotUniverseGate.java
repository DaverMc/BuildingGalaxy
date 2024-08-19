package de.daver.build.gate.spigot;

import de.daver.build.hub.UniverseHub;
import de.daver.build.hub.api.gate.*;
import org.bukkit.plugin.java.JavaPlugin;

public class SpigotUniverseGate extends JavaPlugin implements PlattformGate {

    private final JavaPlugin spigotInstance;
    private final UniverseAdapter adapter;

    public SpigotUniverseGate(UniverseAdapter adapter) {
        this.spigotInstance = this;
        UniverseHub.setConnector(this);
        this.adapter = adapter;
    }

    @Override
    public void onEnable() {
        adapter.onInitialisation();
    }

    @Override
    public void onDisable() {
        adapter.onTermination();
    }

    @Override
    public UniverseAdapter getAdapter() {
        return this.adapter;
    }

    @Override
    public CommandRegistrator getCommandRegistrator() {
        return null;
    }

    @Override
    public UserManager getUserManager() {
        return null;
    }

    @Override
    public GuiManager getGuiManager() {
        return null;
    }

    @Override
    public ItemManager getItemManager() {
        return null;
    }

    @Override
    public WorldSlave getWorldSlave() {
        return null;
    }


}
