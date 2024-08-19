package de.daver.build.hub.api.command;

public interface Argument {

    Suggestion getSuggestion();

    Action getAction();

    String getKey();

    int getPosition();

    <T> T get(String raw);

}
