package de.daver.build.hub.api.command;

import de.daver.build.hub.command.CommandBuilderImpl;

public interface CommandBuilder {

    CommandBuilder permission(String permission);

    CommandBuilder description(String description);

    CommandBuilder alias(String...aliases);

    CommandBuilder arguments(Argument...arguments);

    CommandBuilder action(Action action);

    CommandBuilder subCommands(int position, Command...subCommands);

    Command build();

    static CommandBuilder create(String name) {
        return new CommandBuilderImpl(name);
    }

}
