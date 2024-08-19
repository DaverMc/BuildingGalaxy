package de.daver.build.gate.spigot.command;

import de.daver.build.hub.UniverseHub;
import de.daver.build.hub.api.command.Argument;
import de.daver.build.hub.api.command.Command;
import de.daver.build.hub.api.command.Action;
import de.daver.build.hub.core.command.CommandInputImpl;
import de.daver.build.hub.util.User;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

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
        public boolean execute(CommandSender sender, String s, String[] args) {
            List<String> listArgs = Arrays.asList(args);
            Command subCommand = this.command.getSubCommand(listArgs);
            CommandInputImpl input = new CommandInputImpl(subCommand, listArgs);
            Action action = subCommand.getAction(listArgs.size() - 1);
            User user = UniverseHub.connector().getUserManager().getPlayer(((Player) sender).getUniqueId());
            return action.execute(user, input);
        }

        @Override
        public List<String> tabComplete(CommandSender sender, String alias, String[] args, Location location) throws IllegalArgumentException {
            List<String> listArgs = Arrays.asList(args);
            Command subCommand = this.command.getSubCommand(listArgs);
            CommandInputImpl input = new CommandInputImpl(subCommand, listArgs);
            User user = UniverseHub.connector().getUserManager().getPlayer(((Player) sender).getUniqueId());
            return subCommand.getArguments(listArgs.size() - 1).stream()
                    .map(Argument::getSuggestion)
                    .map(suggestion -> suggestion.getSuggestions(user, input))
                    .flatMap(Collection::stream)
                    .toList();
        }
}