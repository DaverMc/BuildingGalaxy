package de.daver.build.command;

import org.bukkit.command.CommandSender;

public interface Action {

    void execute(CommandSender sender, CommandInput input);

}
