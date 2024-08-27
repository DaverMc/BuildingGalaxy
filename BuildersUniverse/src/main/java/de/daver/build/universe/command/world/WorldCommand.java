package de.daver.build.universe.command.world;

import de.daver.build.hub.api.command.*;
import de.daver.build.universe.util.Permissions;

public class WorldCommand {

    public static Command world() {
        return CommandBuilder.create("world")
                .alias("w")
                .permission(Permissions.COMMAND_WORLD)
                .subCommands(0,
                        WorldCreateSubCommand.create(),
                        WorldDeleteSubCommand.create(),
                        WorldListSubCommand.create(),
                        WorldTeleportSubCommand.create(),
                        WorldUserSubCommand.create())
                .build();
    }

    //TODO Probably unnecessary
    private static Command load() {
        return CommandBuilder.create("load")
                .permission(Permissions.COMMAND_WORLD_LOAD)
                .build();
    }

    //TODO Probably unnecessary
    private static Command unload() {
        return CommandBuilder.create("unload")
                .permission(Permissions.COMMAND_WORLD_UNLOAD)
                .build();
    }
}


