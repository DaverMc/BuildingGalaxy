package de.daver.build.command;

import org.bukkit.command.CommandSender;

import java.util.List;

public interface Suggestion {

    List<String> getSuggestions(CommandSender sender, CommandInput input);

}
