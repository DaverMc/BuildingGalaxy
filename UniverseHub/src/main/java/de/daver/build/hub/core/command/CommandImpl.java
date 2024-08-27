package de.daver.build.hub.core.command;


import de.daver.build.hub.api.command.Action;
import de.daver.build.hub.api.command.Argument;
import de.daver.build.hub.api.command.Command;

import java.util.*;

public class CommandImpl implements Command {

    private final Map<String, Argument> arguments;
    private final List<String> aliases;
    private final String name;
    private final String permission;
    private final Action action;
    private final String description;

    public CommandImpl(String name, String permission,
                       String description, Action action,
                       List<String> alias, Map<String, Argument> arguments) {
        this.arguments = arguments;
        this.aliases = alias;
        this.name = name;
        this.permission = permission;
        this.description = description;
        this.action = action;
    }


    public String name() {
        return this.name;
    }

    public String description() {
        return this.description;
    }

    @Override
    public String permission() {
        return this.permission;
    }

    public List<String> aliases() {
        return this.aliases;
    }

    public Command getSubCommand(List<String> args) {
        return getSubCommandRecursive(this, args, 0);
    }

    private static CommandImpl getSubCommandRecursive(CommandImpl root, List<String> args, int iteration) {
        if (args.isEmpty() || args.size() <= iteration) return root;
        String name = args.get(iteration);
        CommandImpl subCommand = root.arguments.values().stream()
                .filter(argument -> argument.getPosition() == iteration)
                .filter(argument -> argument instanceof SubCommandArgument)
                .map(argument -> (SubCommandArgument) argument)
                .map(subCommandArgument -> subCommandArgument.getSubCommand(name))
                .filter(Objects::nonNull)
                .map(command -> (CommandImpl) command)
                .findFirst()
                .orElse(root);

        int newIteration = iteration;
        if(subCommand == root) newIteration++;
        else {
            args.subList(0, newIteration + 1).clear();
            newIteration = 0;
        }
        return getSubCommandRecursive(subCommand, args, newIteration);
    }


    public List<Argument> getArguments(int position) {
        return this.arguments.values().stream()
                .filter(argument -> argument.getPosition() == position)
                .toList();
    }

    public Argument getArgument(String key) {
        return this.arguments.get(key);
    }

    public Action getAction(int position) {
        if(position == -1) return this.action;
        for(Argument argument : getArguments(position)) {
            if(argument.getAction() == null) continue;
            return argument.getAction();
        }
        return null;
    }
}
