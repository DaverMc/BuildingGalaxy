package de.daver.build.hub.api.command;

import java.util.List;

public interface Command {

    String name();

    String description();

    List<String> aliases();

    Command getSubCommand(List<String> arguments);

    Action getAction(int position);

    List<Argument> getArguments(int position);

    Argument getArgument(String name);

}
