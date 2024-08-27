package de.daver.build.universe.command.world;

import de.daver.build.hub.UniverseHub;
import de.daver.build.hub.api.command.*;
import de.daver.build.hub.api.world.World;
import de.daver.build.universe.util.BUUNKeys;
import de.daver.build.universe.util.Permissions;

public class WorldDeleteSubCommand {

    public static Command create() {
        return CommandBuilder.create("delete")
                .alias("del", "d")
                .permission(Permissions.COMMAND_WORLD_DELETE)
                .arguments(ArgumentBuilder.create("id", 0)
                        .suggestion(idSuggestion())
                        .action(deleteAction())
                        .build())
                .build();
    }

    private static Suggestion idSuggestion() {
        return (sender, input) -> UniverseHub.getWorldMaster().getWorlds().stream()
                .map(World::getId)
                .toList();
    }

    private static Action deleteAction() {
        return (sender, input) -> {
            String id = input.get("id");
            if(UniverseHub.getWorldMaster().deleteWorld(id)) UniverseHub.getLanguageManager()
                    .get(BUUNKeys.COMMAND_WORLD_DELETE_SUCCESS)
                    .send(sender);
            return true;
        };
    }

}
