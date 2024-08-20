package de.daver.build.hub.api.command;

import de.daver.build.hub.api.util.Sender;

public interface Action {

    boolean execute(Sender sender, CommandInput input);

}
