package de.daver.build.hub.demo;

import de.daver.build.hub.command.*;
import de.daver.build.hub.command.defaults.SubCommandArgument;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class CommandDemo {

    public boolean demoAction(CommandSender sender, CommandInput input) {
        System.out.println("demoAction");
        return true;
    }

    public List<String> demoSuggestion(CommandSender sender, CommandInput input) {
        return new ArrayList<>();
    }

    public void test() {
        var a = new Command("a")
                .addAlias("AKA")
                .addArgument(new Argument("alias", 0));

        var b = new Command("b")
                .addArgument(new Argument("name", 1)
                        .suggestion(this::demoSuggestion)
                        .action(this::demoAction));

        var newC = new Command("test")
                .addArgument(new SubCommandArgument(0)
                        .addSubCommands(a, b))
                .addArgument(new Argument("age", 1)
                        .type(ArgumentType.INT))
                .setAction(this::demoAction)
                .setDescription("This is a test command to simply do nothing!");

        CommandMaster.get().registerCommand(newC);
    }

}
