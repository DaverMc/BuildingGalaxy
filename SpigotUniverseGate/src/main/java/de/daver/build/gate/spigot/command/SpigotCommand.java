package de.daver.build.gate.spigot.command;

import de.daver.build.hub.api.command.Command;
import de.daver.build.hub.api.command.Action;
import de.daver.build.hub.command.ArgumentImpl;
import de.daver.build.hub.command.CommandInputImpl;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class SpigotCommand extends BukkitCommand{

        private final Command command;

        protected SpigotCommand(Command command) {
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
            CommandInputImpl input = new CommandInputImpl(subCommand, listArgs);
            Action action = subCommand.getAction(listArgs.size() - 1);
            return action.execute(commandSender, input);
        }

        @Override
        public List<String> tabComplete(CommandSender sender, String alias, String[] args, Location location) throws IllegalArgumentException {
            List<String> listArgs = Arrays.asList(args);
            Command subCommand = this.command.getSubCommand(listArgs);
            CommandInputImpl input = new CommandInputImpl(subCommand, listArgs);
            return subCommand.getArguments(listArgs.size() - 1).stream()
                    .map(ArgumentImpl::getSuggestion)
                    .map(suggestion -> suggestion.getSuggestions(sender, input))
                    .flatMap(Collection::stream)
                    .toList();
        }
}