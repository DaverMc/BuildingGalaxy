package de.daver.build.hub.command;

public class Argument {

    public Argument(String key) {

    }

    public Argument suggestion(Suggestion suggestion) {
        return this;
    }

    public Argument action(Action action) {
        return this;
    }

    public <T> Argument type(Type<T> type) {
        return this;
    }

    public interface Type<T> {

        Type<Integer> INT = Integer::parseInt;

        T transformString(String s);

    }

}
