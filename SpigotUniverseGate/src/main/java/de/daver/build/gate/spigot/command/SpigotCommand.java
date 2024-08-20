package de.daver.build.gate.spigot.command;

import de.daver.build.hub.UniverseHub;
import de.daver.build.hub.api.command.Argument;
import de.daver.build.hub.api.command.Command;
import de.daver.build.hub.api.command.Action;
import de.daver.build.hub.api.util.Sender;
import de.daver.build.hub.core.command.CommandInputImpl;
import de.daver.build.hub.api.util.User;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
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
            StringBuilder builder = new StringBuilder("/<");
            builder.append(command.name());
            for (String alias : command.aliases()) builder.append(":").append(alias);
            builder.append(">");
            //TODO Dynamical add the arguments and there id
            return builder.toString();
            //"/<world:w> <subCommand:player:...> <age:...> ...
        }

        @Override
        public boolean execute(@NotNull CommandSender commandSender, @NotNull String s, @NotNull String[] args) {
            List<String> listArgs = new ArrayList<>(Arrays.asList(args));
            Command subCommand = this.command.getSubCommand(listArgs);
            CommandInputImpl input = new CommandInputImpl(subCommand, listArgs);
            Action action = subCommand.getAction(listArgs.size() - 1);
            Sender sender;
            if(commandSender instanceof Player player) sender = UniverseHub.gate().getUserManager().getUser(player.getUniqueId());
            else sender = UniverseHub.gate().getConsoleSender();
            if(!sender.hasPermission(subCommand.permission())) return false;
            return action.execute(sender, input);
        }

        @Override
        @NotNull
        public List<String> tabComplete(@NotNull CommandSender commandSender, @NotNull String alias, String[] args, Location location) throws IllegalArgumentException {
            if(commandSender instanceof ConsoleCommandSender) return new ArrayList<>();
            List<String> listArgs = new ArrayList<>(Arrays.asList(args));
            Command subCommand = this.command.getSubCommand(listArgs);
            CommandInputImpl input = new CommandInputImpl(subCommand, listArgs);
            User user = UniverseHub.gate().getUserManager().getUser(((Player) commandSender).getUniqueId());
            return subCommand.getArguments(listArgs.size() - 1).stream()
                    .map(Argument::getSuggestion)
                    .map(suggestion -> suggestion.getSuggestions(user, input))
                    .flatMap(Collection::stream)
                    .toList();
        }
}