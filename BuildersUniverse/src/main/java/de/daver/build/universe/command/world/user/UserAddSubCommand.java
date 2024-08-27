package de.daver.build.universe.command.world.user;

import de.daver.build.hub.api.command.Command;
import de.daver.build.hub.api.command.CommandBuilder;
import de.daver.build.universe.util.Permissions;

public class UserAddSubCommand {

    public static Command create() {
        return CommandBuilder.create("addAllowed")
                .alias("add")
                .permission(Permissions.COMMAND_WORLD_USER_ADD_ALLOWED)
                .build();
    }

}
