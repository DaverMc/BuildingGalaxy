package de.daver.build.gate.spigot.command;

import de.daver.build.hub.api.command.Command;
import de.daver.build.hub.gate.CommandRegistrator;
import de.daver.build.hub.util.ReflectionUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.PluginManager;

public class SpigotCommandRegistrator implements CommandRegistrator {

    @Override
    public void registerCommand(Command command) {

        PluginManager pluginManager = Bukkit.getServer().getPluginManager();
        CommandMap commandMap = ReflectionUtils.getFieldValue(pluginManager, "commandMap");
        commandMap.register(command.name(), new SpigotCommand(command));
    }
}
