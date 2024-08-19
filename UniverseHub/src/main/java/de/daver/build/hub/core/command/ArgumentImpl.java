package de.daver.build.hub.core.command;

import de.daver.build.hub.api.command.Action;
import de.daver.build.hub.api.command.Argument;
import de.daver.build.hub.api.command.ArgumentType;
import de.daver.build.hub.api.command.Suggestion;

public class ArgumentImpl implements Argument {

    private final String key;
    private final int position;
    private ArgumentType<?> type;
    private Suggestion suggestion;
    private Action action;

    public ArgumentImpl(String key, int position, ArgumentType<?> type, Suggestion suggestion, Action action) {
        this.key = key;
        this.position = position;
        this.type = type;
        this.suggestion = suggestion;
        this.action = action;
    }

    public Suggestion getSuggestion() {
        return suggestion;
    }

    public Action getAction() {
        return action;
    }


    public String getKey() {
        return this.key;
    }

    public int getPosition() {
        return this.position;
    }

    public <T> T get(String raw) {
        if(type == null) return (T) raw;
        return (T) type.transformString(raw);
    }
}
