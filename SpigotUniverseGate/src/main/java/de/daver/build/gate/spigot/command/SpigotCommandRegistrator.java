package de.daver.build.gate.spigot.command;

import de.daver.build.hub.api.command.Command;
import de.daver.build.hub.api.gate.CommandRegistrator;
import de.daver.build.hub.core.util.ReflectionUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.PluginManager;

public class SpigotCommandRegistrator implements CommandRegistrator {

    @Override
    public void registerCommand(Command command) {

        PluginManager pluginManager = Bukkit.getServer().getPluginManager();
        CommandMap commandMap = ReflectionUtils.getFieldValue(pluginManager, "commandMap");
        if(commandMap == null) throw new NoSuchFieldError("commandMap");
        commandMap.register(command.name(), new SpigotCommand(command));
    }
}
