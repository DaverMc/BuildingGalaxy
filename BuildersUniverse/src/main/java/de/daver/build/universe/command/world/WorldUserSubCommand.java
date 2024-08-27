package de.daver.build.universe.command.world;

import de.daver.build.hub.api.command.Command;
import de.daver.build.hub.api.command.CommandBuilder;
import de.daver.build.universe.command.world.user.UserAddSubCommand;
import de.daver.build.universe.command.world.user.UserInviteSubCommand;
import de.daver.build.universe.command.world.user.UserRemoveSubCommand;
import de.daver.build.universe.util.Permissions;

public class WorldUserSubCommand {

    public static Command create() {
        return CommandBuilder.create("user")
                .alias("u")
                .permission(Permissions.COMMAND_WORLD_USER)
                .subCommands(1,
                        UserInviteSubCommand.create(),
                        UserAddSubCommand.create(),
                        UserRemoveSubCommand.create())
                .build();
    }

}
