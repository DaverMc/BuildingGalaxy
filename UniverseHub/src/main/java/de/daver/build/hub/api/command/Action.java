package de.daver.build.hub.api.command;

import de.daver.build.hub.command.CommandInputImpl;
import de.daver.build.hub.util.User;

public interface Action {

    boolean execute(User sender, CommandInputImpl input);

}
