package de.daver.build.universe.command.world;

import de.daver.build.hub.api.command.Command;
import de.daver.build.hub.api.command.CommandBuilder;
import de.daver.build.universe.util.Permissions;

public class WorldListSubCommand {

    public static Command create() {
        return CommandBuilder.create("list")
                .alias("l")
                .permission(Permissions.COMMAND_WORLD_LIST)
                .build();
    }

}
