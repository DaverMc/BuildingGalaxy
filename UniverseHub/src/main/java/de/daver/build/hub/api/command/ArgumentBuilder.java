package de.daver.build.hub.api.command;

import de.daver.build.hub.command.ArgumentBuilderImpl;

public interface ArgumentBuilder {

    ArgumentBuilder suggestion(Suggestion suggestion);

    ArgumentBuilder action(Action action);

    <T> ArgumentBuilder type(ArgumentType<T> type);

    Argument build();

    static ArgumentBuilder create(String key, int position) {
        return new ArgumentBuilderImpl(key, position);
    }

}
