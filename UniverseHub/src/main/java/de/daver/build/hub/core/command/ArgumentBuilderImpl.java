package de.daver.build.hub.core.command;

import de.daver.build.hub.api.command.*;

public class ArgumentBuilderImpl implements ArgumentBuilder {

    private final String key;
    private final int position;
    private Suggestion suggestion;
    private Action action;
    private ArgumentType<?> type;

    public ArgumentBuilderImpl(String key, int position) {
        this.key = key;
        this.position = position;
    }

    @Override
    public ArgumentBuilder suggestion(Suggestion suggestion) {
        this.suggestion = suggestion;
        return this;
    }

    @Override
    public ArgumentBuilder action(Action action) {
        this.action = action;
        return this;
    }

    @Override
    public <T> ArgumentBuilder type(ArgumentType<T> type) {
        this.type = type;
        return this;
    }

    @Override
    public Argument build() {
        return new ArgumentImpl(key, position, type, suggestion, action);
    }
}
