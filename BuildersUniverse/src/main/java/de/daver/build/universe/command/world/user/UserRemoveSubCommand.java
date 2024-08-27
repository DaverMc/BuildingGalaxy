package de.daver.build.universe.command.world.user;

import de.daver.build.hub.api.command.Command;
import de.daver.build.hub.api.command.CommandBuilder;
import de.daver.build.universe.util.Permissions;

public class UserRemoveSubCommand {

    public static Command create() {
        return CommandBuilder.create("removeAllowed")
                .alias("remove")
                .permission(Permissions.COMMAND_WORLD_USER_REMOVE_ALLOWED)
                .build();
    }

}
