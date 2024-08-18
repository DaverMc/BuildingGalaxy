package de.daver.build.hub.command;

public class Argument {

    private final String key;
    private final int position;
    private ArgumentType<?> type;
    private Suggestion suggestion;
    private Action action;

    public Argument(String key, int position) {
        this.key = key;
        this.position = position;
    }

    public Argument suggestion(Suggestion suggestion) {
        this.suggestion = suggestion;
        return this;
    }

    public Argument action(Action action) {
        this.action = action;
        return this;
    }

    public <T> Argument type(ArgumentType<T> type) {
        this.type = type;
        return this;
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
