package de.daver.build.gate.spigot;

import de.daver.build.gate.spigot.command.SpigotCommandRegistrator;
import de.daver.build.gate.spigot.gen.VanillaWorldGenerator;
import de.daver.build.gate.spigot.gen.VoidWorldGenerator;
import de.daver.build.gate.spigot.gui.GuiListener;
import de.daver.build.gate.spigot.gui.SpigotGuiConnection;
import de.daver.build.gate.spigot.user.SpigotConsole;
import de.daver.build.gate.spigot.user.SpigotUserManager;
import de.daver.build.hub.UniverseHub;
import de.daver.build.hub.api.gate.*;
import de.daver.build.hub.api.util.Sender;
import de.daver.build.hub.api.world.WorldMaster;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class SpigotUniverseGate extends JavaPlugin implements PlattformGate {

    private final UniverseAdapter adapter;
    private UserManager userManager;

    public SpigotUniverseGate(UniverseAdapter adapter) {
        UniverseHub.setGate(this);
        this.adapter = adapter;
    }

    @Override
    public void onEnable() {
        UniverseHub.getWorldMaster().setWorldContainer(this.getServer().getWorldContainer());
        this.userManager = new SpigotUserManager();
        registerListeners();
        adapter.onInitialisation();
    }

    private void registerListeners() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new GuiListener(), this);
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
        return new SpigotCommandRegistrator();
    }

    @Override
    public UserManager getUserManager() {
        return this.userManager;
    }

    @Override
    public GuiConnection getGuiConnection() {
        return new SpigotGuiConnection();
    }

    @Override
    public ItemManager getItemManager() {
        return null;
    }

    @Override
    public WorldSlave getWorldSlave() {
        return null;
    }

    @Override
    public SchedulerMaster getSchedulerMaster() {
        return null;
    }

    @Override
    public Sender getConsoleSender() {
        return new SpigotConsole();
    }

    @Override
    public File getMainDirectory() {
        return this.getDataFolder();
    }


}
