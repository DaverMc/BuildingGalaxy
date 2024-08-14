package de.daver.build.hub.demo;

import de.daver.build.hub.command.Argument;
import de.daver.build.hub.command.Command;
import de.daver.build.hub.command.CommandInput;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class CommandDemo {

    public void demoAction(CommandSender sender, CommandInput input) {
        System.out.println("demoAction");
    }

    public List<String> demoSuggestion(CommandSender sender, CommandInput input) {
        return new ArrayList<>();
    }

    public void test() {
        //Manages all world based subcommands
        new Command("world", "...")
                //Create a new world
                .addSubCommand(new Command("create", "...")
                                .addArgument(new Argument("id"), 0)
                                .addArgument(new Argument("generator")
                                        .suggestion(this::demoSuggestion)
                                        .action(this::demoAction), 1),
                        0)
                //Import a world into the system
                .addSubCommand(new Command("import", "...")
                                .addArgument(new Argument("id"), 0)
                                .addArgument(new Argument("generator")
                                        .suggestion(this::demoSuggestion), 1)
                                .addArgument(new Argument("filepath")
                                        .action(this::demoAction), 2),
                        0)
                //Delete a world
                .addSubCommand(new Command("delete", "...")
                                .addArgument(new Argument("world")
                                                .suggestion(this::demoSuggestion)
                                                .action(this::demoAction),
                                        0),
                        0)
                //Lists all worlds
                .addSubCommand(new Command("list", "...")
                                .action(this::demoAction),
                        0)
                //Player permission command
                .addSubCommand(new Command("user", "...")
                                .addArgument(new Argument("world").suggestion(this::demoSuggestion), 0)
                                //Add a player to a world
                                .addSubCommand(new Command("add", "...")
                                                .addArgument(new Argument("player")
                                                        .suggestion(this::demoSuggestion)
                                                        .action(this::demoAction), 0),
                                        1)
                                //Remove a player from a world
                                .addSubCommand(new Command("remove", "...")
                                                .addArgument(new Argument("player")
                                                        .suggestion(this::demoSuggestion)
                                                        .action(this::demoAction), 0),
                                        1)
                                //Lists all allowed players of a world
                                .addSubCommand(new Command("list", "...")
                                                .action(this::demoAction),
                                        1),
                        0)
                //Loads a world
                .addSubCommand(new Command("load", "...")
                                .addArgument(new Argument("world")
                                        .type(Argument.Type.INT)
                                        .suggestion(this::demoSuggestion)
                                        .action(this::demoAction), 0),
                        0)
                //Unloads a world
                .addSubCommand(new Command("unload", "...")
                                .addArgument(new Argument("world")
                                                .type(Argument.Type.INT)
                                                .suggestion(this::demoSuggestion)
                                                .action(this::demoAction),
                                        0),
                        0);
    }

}
