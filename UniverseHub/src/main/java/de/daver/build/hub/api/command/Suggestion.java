package de.daver.build.hub.api.command;

import de.daver.build.hub.command.CommandInputImpl;
import de.daver.build.hub.util.User;

import java.util.List;

public interface Suggestion {

    List<String> getSuggestions(User sender, CommandInputImpl input);

}
