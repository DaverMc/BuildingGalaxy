package de.daver.build.hub.command;

import de.daver.build.hub.api.command.Suggestion;
import org.bukkit.command.CommandSender;

import java.util.List;

public class SubCommandSuggestion implements Suggestion {


    @Override
    public List<String> getSuggestions(CommandSender sender, CommandInputImpl input) {
        return List.of();
    }
}
