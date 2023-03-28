package tictactoe.utils;

import tictactoe.player.PlayerType;

public class InputCommand {
    private final Command command;
    private PlayerType playerType1;
    private PlayerType playerType2;

    public InputCommand(Command command) {
        this.command = command;
    }

    public InputCommand(Command command, PlayerType playerType1, PlayerType playerType2) {
        this.command = command;
        this.playerType1 = playerType1;
        this.playerType2 = playerType2;
    }

    public Command getCommand() {
        return command;
    }

    public PlayerType getPlayerType1() {
        return playerType1;
    }

    public PlayerType getPlayerType2() {
        return playerType2;
    }

}
