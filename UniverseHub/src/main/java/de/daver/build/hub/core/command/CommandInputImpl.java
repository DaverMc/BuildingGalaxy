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

    @Override
    public <T> T get(String key) {
        Argument argument = command.getArgument(key);
        if (argument == null) return null;
        return argument.get(this.args.get(argument.getPosition()));
    }

    @Override
    public <T> T get(int index) {
        List<Argument> argument = command.getArguments(index);
        if (argument == null || argument.isEmpty()) return null;
        return argument.get(0).get(getRaw(index));
    }

    @Override
    public String getRaw(String key) {
        Argument argument = command.getArgument(key);
        if (argument == null) return null;
        return this.args.get(argument.getPosition());
    }

    @Override
    public String getRaw(int index) {
        return this.args.get(index);
    }

}
