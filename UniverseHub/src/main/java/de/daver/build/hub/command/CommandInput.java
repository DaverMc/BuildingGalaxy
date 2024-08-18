package de.daver.build.hub.command;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CommandInput {

    private final List<String> args;
    private final Command command;

    public CommandInput(Command command, List<String> args) {
        this.args = args;
        this.command = command;
    }

    public <T> T get(String key) {
        Argument argument = command.getArgument(key);
        if (argument == null) return null;
        return (T) args.get(argument.getPosition());
    }

    public <T> T get(int index) {
        List<Argument> argument = command.getArguments(index);
        if (argument == null || argument.isEmpty()) return null;
        return (T) argument.get(0);
    }

}
