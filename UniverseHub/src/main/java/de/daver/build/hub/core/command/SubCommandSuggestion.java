package de.daver.build.hub.core.command;

import de.daver.build.hub.api.command.Suggestion;
import de.daver.build.hub.util.User;

import java.util.List;

public class SubCommandSuggestion implements Suggestion {


    @Override
    public List<String> getSuggestions(User sender, CommandInputImpl input) {
        return List.of();
    }
}
