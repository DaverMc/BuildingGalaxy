package de.daver.build.hub.command;


import de.daver.build.hub.command.defaults.SubCommandArgument;

import java.util.*;

public class Command {

    private final Map<String, Argument> arguments;
    private final List<String> aliases;
    private final String name;
    private final String permission;

    private Action action;
    private String description;

    public Command(String name) {
        this(name, null);
    }

    public Command(String name, String permission) {
        this.arguments = new HashMap<>();
        this.aliases = new ArrayList<>();
        this.name = name;
        this.permission = permission;
    }

    public Command addAlias(String...aliases) {
        this.aliases.addAll(Arrays.asList(aliases));
        return this;
    }

    public Command addArgument(Argument argument) {
        this.arguments.put(argument.getKey(), argument);
        return this;
    }

    public Command setAction(Action action) {
        this.action = action;
        return this;
    }

    public Command setDescription(String description) {
        this.description = description;
        return this;
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
