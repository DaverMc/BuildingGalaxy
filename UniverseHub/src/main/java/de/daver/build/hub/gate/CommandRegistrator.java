package de.daver.build.hub.gate;

import de.daver.build.hub.api.command.Command;

public interface CommandRegistrator {

    void registerCommand(Command command);

}
