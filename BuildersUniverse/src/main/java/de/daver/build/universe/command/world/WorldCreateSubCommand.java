package de.daver.build.universe.command.world;

import de.daver.build.hub.UniverseHub;
import de.daver.build.hub.api.command.*;
import de.daver.build.hub.api.lang.PlaceHolder;
import de.daver.build.hub.api.world.World;
import de.daver.build.hub.api.world.WorldGenerator;
import de.daver.build.universe.util.BUUNKeys;
import de.daver.build.universe.util.Permissions;

public class WorldCreateSubCommand {

    public static Command create() {
        return CommandBuilder.create("create")
                .alias("c")
                .permission(Permissions.COMMAND_WORLD_CREATE)
                .arguments(ArgumentBuilder.create("id", 0).build(),
                        ArgumentBuilder.create("generator", 1)
                                .type(generatorType())
                                .suggestion(generatorSuggestion())
                                .action(createWorldAction())
                                .build())
                .build();
    }

    private static Suggestion generatorSuggestion() {
        return (sender, input) -> UniverseHub.getWorldMaster().getGenerators().stream().map(WorldGenerator::id).toList();
    }

    private static ArgumentType<WorldGenerator> generatorType() {
        return id -> UniverseHub.getWorldMaster().getGenerator(id);
    }

    private static Action createWorldAction() {
        return (sender, input) -> {
            WorldGenerator generator = input.get("generator");
            String id = input.get("id");
            World world = UniverseHub.getWorldMaster().createWorld(id, generator, true); //TODO Check if loading state is irrelevant
            if(world == null) UniverseHub.getLanguageManager().get(BUUNKeys.COMMAND_WORLD_CREATE_FAILED)
                        .placeholder(new PlaceHolder<>("id", id))
                        .send(sender);
            else UniverseHub.getLanguageManager().get(BUUNKeys.COMMAND_WORLD_CREATE_SUCCESS)
                    .placeholder(new PlaceHolder<>("id", id))
                    .send(sender);
            return true;
        };
    }
}
