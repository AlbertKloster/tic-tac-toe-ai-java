package tictactoe.utils;

public enum Command {
    START("start"),
    EXIT("exit");

    public final String command;

    Command(String command) {
        this.command = command;
    }

    public static Command getCommand(String input) {
        for (Command command : Command.values()) {
            if (command.command.equals(input.toLowerCase())) {
                return command;
            }
        }
        return null;
    }

}
