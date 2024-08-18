package de.daver.build.hub.command.defaults;

import de.daver.build.hub.command.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubCommandArgument extends Argument {

    private final List<Command> subCommands;

    public SubCommandArgument(int position) {
        super("subcommand", position);
        this.subCommands = new ArrayList<>();
        suggestion(new SubCommandSuggestion());
    }

    @Override
    public Argument action(Action action) {
        return this;
    }

    @Override
    public <T> Argument type(ArgumentType<T> type) {
        return this;
    }

    public SubCommandArgument addSubCommands(Command... commands) {
        this.subCommands.addAll(Arrays.asList(commands));
        return this;
    }

    public Command getSubCommand(String s) {
        return this.subCommands.stream()
                .filter(command -> {
                    if(command.name().equalsIgnoreCase(s)) return true;
                    for(String alias : command.aliases()) if(alias.equalsIgnoreCase(s)) return true;
                    return false;
                })
                .findFirst()
                .orElse(null);
    }
}
