package de.daver.build.hub.core.command;

import de.daver.build.hub.api.command.Argument;
import de.daver.build.hub.api.command.Command;
import de.daver.build.hub.api.command.CommandInput;

import java.util.List;

public class CommandInputImpl implements CommandInput {

    private final List<String> args;
    private final Command command;

    public CommandInputImpl(Command command, List<String> args) {
        this.args = args;
        this.command = command;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T get(String key) {
        Argument argument = command.getArgument(key);
        if (argument == null) return null;
        return (T) args.get(argument.getPosition());
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T get(int index) {
        List<Argument> argument = command.getArguments(index);
        if (argument == null || argument.isEmpty()) return null;
        return (T) argument.get(0);
    }

}
