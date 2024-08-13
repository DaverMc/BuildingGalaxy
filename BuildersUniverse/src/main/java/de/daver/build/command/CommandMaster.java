package de.daver.build.command;

import de.daver.build.universe.util.ReflectionUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.plugin.PluginManager;

import java.util.List;

public class CommandMaster {

    private static CommandMaster instance;

    private CommandMaster() {

    }

    public void registerCommand(Command command) {
        PluginManager pluginManager = Bukkit.getServer().getPluginManager();
        CommandMap commandMap = ReflectionUtils.getFieldValue(pluginManager, "commandMap");
        commandMap.register(command.name(), new BukkitCommand(command.name()) {
            @Override
            public boolean execute(CommandSender commandSender, String s, String[] strings) {
                return false;
            }

            @Override
            public List<String> tabComplete(CommandSender sender, String alias, String[] args, Location location) throws IllegalArgumentException {
                return super.tabComplete(sender, alias, args, location);
            }
        });
    }

    public static CommandMaster get() {
        if(instance == null) instance = new CommandMaster();
        return instance;
    }

}
