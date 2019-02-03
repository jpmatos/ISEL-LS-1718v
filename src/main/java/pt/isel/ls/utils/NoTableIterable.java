package pt.isel.ls.utils;

public class NoTableIterable {
    private String message;
    private String commandCalled;

    public NoTableIterable(String message, String commandCalled) {
        this.message = message;
        this.commandCalled = commandCalled;
    }

    public String getCommandCalled() {
        return commandCalled;
    }

    @Override
    public String toString() {
        return commandCalled + ": " + message;
    }
}
