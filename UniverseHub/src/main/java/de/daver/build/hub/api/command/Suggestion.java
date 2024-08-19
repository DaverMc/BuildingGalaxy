package de.daver.build.hub.api.command;

import de.daver.build.hub.command.CommandInputImpl;
import org.bukkit.command.CommandSender;

import java.util.List;

public interface Suggestion {

    List<String> getSuggestions(CommandSender sender, CommandInputImpl input);

}
