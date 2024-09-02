package de.daver.build.universe.command.world;

import de.daver.build.hub.UniverseHub;
import de.daver.build.hub.api.command.*;
import de.daver.build.hub.api.gate.WorldSlave;
import de.daver.build.hub.api.util.Sender;
import de.daver.build.hub.api.util.User;
import de.daver.build.hub.api.world.World;
import de.daver.build.universe.util.Permissions;

public class WorldTeleportSubCommand {

    public static Command create() {
        return CommandBuilder.create("teleport")
                .alias("tp")
                .arguments(ArgumentBuilder.create("id", 0)
                        .action(WorldTeleportSubCommand::teleportAction)
                        .build())
                .permission(Permissions.COMMAND_WORLD_TELEPORT)
                .build();
    }

    private static boolean teleportAction(Sender sender, CommandInput input) {
        String id = input.get("id");
        WorldSlave slave = UniverseHub.gate().getWorldSlave();
        World world = UniverseHub.getWorldMaster().getWorld(id);
        slave.sendTo((User) sender, world);
        return true;
    }

}
