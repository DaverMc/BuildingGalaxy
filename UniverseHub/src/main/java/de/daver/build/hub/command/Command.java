package de.daver.build.hub.command;


public class Command {

    public Command(String name, String permission) {

    }

    public Command alias(String...aliases) {
        return this;
    }

    public Command addSubCommand(Command command, int position) {
        return this;
    }

    public Command addArgument(Argument argument, int position) {
        return this;
    }

    public Command action(Action action) {
        return this;
    }

    public String name() {
        return null;
    }

}
