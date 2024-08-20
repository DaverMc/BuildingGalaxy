package de.daver.build.hub.core.command;

import de.daver.build.hub.api.command.Command;
import de.daver.build.hub.api.command.Suggestion;
import de.daver.build.hub.api.util.User;

import java.util.List;

public class SubCommandSuggestion implements Suggestion {

    private final List<Command> commands;

    public SubCommandSuggestion(List<Command> commands) {
        this.commands = commands;
    }

    @Override
    public List<String> getSuggestions(User sender, CommandInputImpl input) {
        return commands.stream().map(Command::name).toList();
    }
}
