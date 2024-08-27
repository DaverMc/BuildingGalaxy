package de.daver.build.universe.command.world;

import de.daver.build.hub.api.command.Command;
import de.daver.build.hub.api.command.CommandBuilder;
import de.daver.build.universe.util.Permissions;

public class WorldTeleportSubCommand {

    public static Command create() {
        return CommandBuilder.create("teleport")
                .alias("tp")
                .permission(Permissions.COMMAND_WORLD_TELEPORT)
                .build();
    }

}
