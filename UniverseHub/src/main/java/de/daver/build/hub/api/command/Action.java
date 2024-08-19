package de.daver.build.hub.api.command;

import de.daver.build.hub.core.command.CommandInputImpl;
import de.daver.build.hub.api.util.User;

public interface Action {

    boolean execute(User sender, CommandInputImpl input);

}
