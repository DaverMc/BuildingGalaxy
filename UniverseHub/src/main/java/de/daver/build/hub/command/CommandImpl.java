package de.daver.build.hub.command;


import de.daver.build.hub.api.command.Action;
import de.daver.build.hub.api.command.Argument;
import de.daver.build.hub.api.command.Command;

import java.util.*;

public class CommandImpl implements Command {

    private final Map<String, Argument> arguments;
    private final List<String> aliases;
    private final String name;
    private final String permission;

    private Action action;
    private String description;

    public CommandImpl(String name, String permission, String description, Action action,
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

    public List<String> aliases() {
        return this.aliases;
    }

    public Action action() {
        return this.action;
    }

    public Command getSubCommand(List<String> args) {
        if(args == null || args.isEmpty()) return this;
        return this.arguments.values().stream()
                .filter(argument -> argument instanceof SubCommandArgument)
                .map(argument -> (SubCommandArgument) argument)
                .map(argument -> argument.getSubCommand(args.remove(argument.getPosition())))
                .findFirst()
                .orElse(null);
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
