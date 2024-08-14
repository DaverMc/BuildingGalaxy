package de.daver.build.hub.command;

import org.bukkit.command.CommandSender;

public interface Action {

    void execute(CommandSender sender, CommandInput input);

}
