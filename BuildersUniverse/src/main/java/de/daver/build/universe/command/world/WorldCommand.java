package de.daver.build.universe.command.world;

import de.daver.build.hub.api.command.Command;
import de.daver.build.hub.api.command.CommandBuilder;
import de.daver.build.universe.util.Permissions;

public class WorldCommand {

    public static Command world() {
        return CommandBuilder.create("world")
                .alias("w")
                .permission(Permissions.COMMAND_WORLD)
                .subCommands(0, create(), delete(), load(), unload(), list(), teleport(), user())
                .build();
    }

    private static Command create() {
        return CommandBuilder.create("create")
                .permission(Permissions.COMMAND_WORLD_CREATE)
                .build();
    }

    private static Command delete() {
        return CommandBuilder.create("delete")
                .permission(Permissions.COMMAND_WORLD_DELETE)
                .build();
    }

    private static Command load() {
        return CommandBuilder.create("load")
                .permission(Permissions.COMMAND_WORLD_LOAD)
                .build();
    }

    private static Command unload() {
        return CommandBuilder.create("unload")
                .permission(Permissions.COMMAND_WORLD_UNLOAD)
                .build();
    }

    private static Command list() {
        return CommandBuilder.create("list")
                .permission(Permissions.COMMAND_WORLD_LIST)
                .build();
    }

    private static Command teleport() {
        return CommandBuilder.create("teleport")
                .permission(Permissions.COMMAND_WORLD_TELEPORT)
                .build();
    }

    private static Command user() {
        return CommandBuilder.create("user")
                .permission(Permissions.COMMAND_WORLD_USER)
                .subCommands(1, invite(), addAllowed(), removeAllowed())
                .build();
    }

    private static Command invite() {
        return CommandBuilder.create("invite")
                .permission(Permissions.COMMAND_WORLD_USER_INVITE)
                .build();
    }

    private static Command addAllowed() {
        return CommandBuilder.create("addAllowed")
                .alias("add")
                .permission(Permissions.COMMAND_WORLD_USER_ADD_ALLOWED)
                .build();
    }

    private static Command removeAllowed() {
        return CommandBuilder.create("removeAllowed")
                .alias("remove")
                .permission(Permissions.COMMAND_WORLD_USER_REMOVE_ALLOWED)
                .build();
    }
}


