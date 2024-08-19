package de.daver.build.hub.api.command;

public interface CommandMap {

    Command getCommand(String name);

    void addCommand(Command command);

}
