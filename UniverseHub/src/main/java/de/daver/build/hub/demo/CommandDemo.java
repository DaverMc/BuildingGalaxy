package de.daver.build.hub.demo;

import de.daver.build.hub.UniverseHub;
import de.daver.build.hub.api.command.*;

import de.daver.build.hub.api.util.Sender;
import de.daver.build.hub.core.command.CommandInputImpl;
import de.daver.build.hub.api.util.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SuppressWarnings("unused")
public class CommandDemo implements Demo{

    private boolean demoAction(Sender sender, CommandInput input) {
        System.out.println("demoAction");
        return new Random().nextBoolean();
    }

    public List<String> demoSuggestion(User user, CommandInputImpl input) {
        return new ArrayList<>();
    }

    @Override
    @SuppressWarnings("unused")
    public void demo() {
        Command command = CommandBuilder.create("world")
                .permission("command.world")
                .description("The main command for world management")
                .alias("w", "welt")
                .action(this::demoAction)
                .subCommands(0,
                        CommandBuilder.create("create")
                                .permission("command.world.create")
                                .action(this::demoAction)
                                .build())
                .arguments(ArgumentBuilder.create("player", 0)
                        .suggestion(this::demoSuggestion)
                        .type(ArgumentType.INT)
                        .action(this::demoAction)
                        .build())
                .build();

        UniverseHub.gate().getCommandRegistrator().registerCommand(command);
    }

}
