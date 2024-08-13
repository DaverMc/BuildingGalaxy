package de.daver.build.command;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public void demoAction() {
        System.out.println("demoAction");
    }

    public List<String> demoSuggestion() {
        return new ArrayList<>();
    }

    public void test() {
        new Command("world", "...")
                //Create a new world
                .addSubCommand(() -> new Command("create", "...")
                                .addArgument(() -> new Command.Argument("id"), 0)
                                .addArgument(() -> new Command.Argument("generator")
                                        .suggestion(this::demoSuggestion)
                                        .action(() -> System.out.println("DEMO!")), 1),
                        0)
                //Import a world into the system
                .addSubCommand(() -> new Command("import", "...")
                                .addArgument(() -> new Command.Argument("id"), 0)
                                .addArgument(() -> new Command.Argument("generator")
                                        .suggestion(this::demoSuggestion), 1)
                                .addArgument(() -> new Command.Argument("filepath")
                                        .action(this::demoAction), 2),
                        0)
                //Delete a world
                .addSubCommand(() -> new Command("delete", "...")
                                .addArgument(() -> new Command.Argument("id")
                                                .suggestion(this::demoSuggestion)
                                                .action(this::demoAction),
                                        0),
                        0)
                //Lists all worlds
                .addSubCommand(() -> new Command("list", "...")
                                .action(this::demoAction),
                        0)
                //Player permission command
                .addSubCommand(() -> new Command("user", "...")
                                .addArgument(() -> new Command.Argument("worldId").suggestion(this::demoSuggestion), 0)
                                //Add a player to a world
                                .addSubCommand(() -> new Command("add", "...")
                                                .addArgument(() -> new Command.Argument("player")
                                                        .suggestion(this::demoSuggestion)
                                                        .action(this::demoAction), 0),
                                        1)
                                //Remove a player from a world
                                .addSubCommand(() -> new Command("remove", "...")
                                                .addArgument(() -> new Command.Argument("player")
                                                        .suggestion(this::demoSuggestion)
                                                        .action(this::demoAction), 0),
                                        1)
                                //Lists all allowed players of a world
                                .addSubCommand(() -> new Command("list", "...")
                                                .action(this::demoAction),
                                        1),
                        0)
                //Loads a world
                .addSubCommand(() -> new Command("load", "...")
                                .addArgument(() -> new Command.Argument("worldId")
                                        .suggestion(this::demoSuggestion)
                                        .action(this::demoAction), 0),
                        0)
                //Unloads a world
                .addSubCommand(() -> new Command("unload", "...")
                                .addArgument(() -> new Command.Argument("worldId")
                                                .suggestion(this::demoSuggestion)
                                                .action(this::demoAction),
                                        0),
                        0);
    }

}
