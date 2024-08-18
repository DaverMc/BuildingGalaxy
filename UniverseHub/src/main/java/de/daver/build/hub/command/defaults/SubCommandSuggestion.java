package de.daver.build.hub.command.defaults;

import de.daver.build.hub.command.CommandInput;
import de.daver.build.hub.command.Suggestion;
import org.bukkit.command.CommandSender;

import java.util.List;

public class SubCommandSuggestion implements Suggestion {


    @Override
    public List<String> getSuggestions(CommandSender sender, CommandInput input) {
        return List.of();
    }
}
