package de.daver.build.hub.api.command;

public interface ArgumentType<T> {

    ArgumentType<Integer> INT = Integer::parseInt;

    T transformString(String s);

}
