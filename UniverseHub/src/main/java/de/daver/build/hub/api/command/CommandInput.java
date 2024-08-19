package de.daver.build.hub.api.command;

public interface CommandInput {

    <T> T get(String key);

    <T> T get(int position);
}
