package de.daver.build.command;

import java.util.List;
import java.util.function.Supplier;

public class Command {

    public Command(String name, String permission) {

    }

    public Command addSubCommand(Supplier<Command> commandSupplier, int position) {
        return this;
    }

    public Command addArgument(Supplier<Argument> argumentSupplier, int position) {
        return this;
    }

    public Command action(Action action) {
        return this;
    }

    public static class Argument {

        public Argument(String key) {

        }

        public Argument suggestion(Suggestion suggestion) {
            return this;
        }

        public Argument action(Action action) {
            return this;
        }

    }

    public interface Suggestion {

        List<String> getSuggestions();

    }

    public interface Action {

        void execute();

    }

}
