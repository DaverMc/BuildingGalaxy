package de.daver.build.hub.core.command;

import de.daver.build.hub.api.command.Action;
import de.daver.build.hub.api.command.Argument;
import de.daver.build.hub.api.command.Command;
import de.daver.build.hub.api.command.CommandBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CommandBuilderImpl implements CommandBuilder {

    private final String name;
    private String permission;
    private String description;
    private final List<String> alias;
    private final List<Argument> arguments;
    private Action action;

    public CommandBuilderImpl(String name) {
        this.arguments = new ArrayList<>();
        this.alias = new ArrayList<>();
        this.name = name;
    }

    @Override
    public CommandBuilder permission(String permission) {
        this.permission = permission;
        return this;
    }

    @Override
    public CommandBuilder description(String description) {
        this.description = description;
        return this;
    }

    @Override
    public CommandBuilder alias(String... aliases) {
        this.alias.addAll(List.of(aliases));
        return this;
    }

    @Override
    public CommandBuilder arguments(Argument... arguments) {
        this.arguments.addAll(List.of(arguments));
        return this;
    }

    @Override
    public CommandBuilder action(Action action) {
        this.action = action;
        return this;
    }

    @Override
    public CommandBuilder subCommands(int position, Command... subCommands) {
        return arguments(new SubCommandArgument(position, List.of(subCommands)));
    }

    @Override
    public Command build() {
        return new CommandImpl(name, permission, description, action, alias, argListToMap());
    }

    private Map<String, Argument> argListToMap() {
        return arguments.stream().collect(Collectors.toMap(Argument::getKey, argument -> argument));
    }
}
