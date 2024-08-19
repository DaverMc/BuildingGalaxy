package de.daver.build.hub.api.command;

import de.daver.build.hub.command.CommandInputImpl;
import org.bukkit.command.CommandSender;

public interface Action {

    boolean execute(CommandSender sender, CommandInputImpl input);

}
