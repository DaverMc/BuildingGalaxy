package de.daver.build.hub.command;

import de.daver.build.hub.util.ReflectionUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.plugin.PluginManager;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class CommandMaster {

    private static CommandMaster instance;

    private CommandMaster() {}

    public void registerCommand(Command command) {
        PluginManager pluginManager = Bukkit.getServer().getPluginManager();
        CommandMap commandMap = ReflectionUtils.getFieldValue(pluginManager, "commandMap");
        commandMap.register(command.name(), new BukkitCommandRunnable(command));
    }

    public static CommandMaster get() {
        if(instance == null) instance = new CommandMaster();
        return instance;
    }

    private static class BukkitCommandRunnable extends BukkitCommand {

        private final Command command;

        protected BukkitCommandRunnable(Command command) {
            super(command.name(), command.description(), createUsageMessage(command), command.aliases());
            this.command = command;
        }

        private static String createUsageMessage(Command command) {
            return "/command"; //TODO Dynamical add the arguments and there id
            //"/command <subCommand/player/...> <age/...> ...
        }

        @Override
        public boolean execute(CommandSender commandSender, String s, String[] args) {
            List<String> listArgs = Arrays.asList(args);
            Command subCommand = this.command.getSubCommand(listArgs);
            CommandInput input = new CommandInput(subCommand, listArgs);
            Action action = subCommand.getAction(listArgs.size() - 1);
            return action.execute(commandSender, input);
        }

        @Override
        public List<String> tabComplete(CommandSender sender, String alias, String[] args, Location location) throws IllegalArgumentException {
            List<String> listArgs = Arrays.asList(args);
            Command subCommand = this.command.getSubCommand(listArgs);
            CommandInput input = new CommandInput(subCommand, listArgs);
            return subCommand.getArguments(listArgs.size() - 1).stream()
                    .map(Argument::getSuggestion)
                    .map(suggestion -> suggestion.getSuggestions(sender, input))
                    .flatMap(Collection::stream)
                    .toList();
        }
    }

}
