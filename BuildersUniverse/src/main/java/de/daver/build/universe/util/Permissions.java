package de.daver.build.universe.util;

public interface Permissions {

    String PREFIX = "buun";

    // Hauptbefehl
    String COMMAND_WORLD = PREFIX + ".command.world";

    // Subcommands
    String COMMAND_WORLD_CREATE = COMMAND_WORLD + ".create";
    String COMMAND_WORLD_DELETE = COMMAND_WORLD + ".delete";
    String COMMAND_WORLD_LOAD = COMMAND_WORLD + ".load";
    String COMMAND_WORLD_UNLOAD = COMMAND_WORLD + ".unload";
    String COMMAND_WORLD_LIST = COMMAND_WORLD + ".list";
    String COMMAND_WORLD_TELEPORT = COMMAND_WORLD + ".teleport";

    // Subcommand: user
    String COMMAND_WORLD_USER = COMMAND_WORLD + ".user";
    String COMMAND_WORLD_USER_INVITE = COMMAND_WORLD_USER + ".invite";
    String COMMAND_WORLD_USER_ADD_ALLOWED = COMMAND_WORLD_USER + ".addAllowed";
    String COMMAND_WORLD_USER_REMOVE_ALLOWED = COMMAND_WORLD_USER + ".removeAllowed";
}

