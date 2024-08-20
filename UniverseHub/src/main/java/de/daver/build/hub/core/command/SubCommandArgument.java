package de.daver.build.hub.core.command;

import de.daver.build.hub.api.command.Command;

import java.util.List;

public class SubCommandArgument extends ArgumentImpl {

    private final List<Command> subCommands;

    public SubCommandArgument(int position, List<Command> subCommands) {
        super("subcommand", position, null, new SubCommandSuggestion(subCommands), null);
        this.subCommands = subCommands;
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
