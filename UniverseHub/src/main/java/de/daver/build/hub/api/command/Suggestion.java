package de.daver.build.hub.api.command;

import de.daver.build.hub.core.command.CommandInputImpl;
import de.daver.build.hub.api.util.User;

import java.util.List;

public interface Suggestion {

    List<String> getSuggestions(User sender, CommandInputImpl input);

}
