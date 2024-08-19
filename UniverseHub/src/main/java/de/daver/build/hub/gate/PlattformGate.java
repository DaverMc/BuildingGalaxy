package de.daver.build.hub.gate;

public interface PlattformGate {

    UniverseAdapter getAdapter();

    CommandRegistrator getCommandRegistrator();

    UserManager getUserManager();

}
