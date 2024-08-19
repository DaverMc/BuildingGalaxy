package de.daver.build.hub.demo;

import de.daver.build.hub.UniverseHub;
import de.daver.build.hub.api.command.ArgumentBuilder;
import de.daver.build.hub.api.command.ArgumentType;
import de.daver.build.hub.api.command.Command;
import de.daver.build.hub.api.command.CommandBuilder;
import de.daver.build.hub.command.*;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class CommandDemo {

    public boolean demoAction(CommandSender sender, CommandInputImpl input) {
        System.out.println("demoAction");
        return true;
    }

    public List<String> demoSuggestion(CommandSender sender, CommandInputImpl input) {
        return new ArrayList<>();
    }

    public void test() {
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

        UniverseHub.connector().getCommandRegistrator().registerCommand(command);
    }

}
