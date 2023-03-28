package tictactoe.utils;

import tictactoe.player.PlayerType;

import java.util.Arrays;
import java.util.Scanner;

public class Input {
    private final Scanner SCANNER = new Scanner(System.in);

    public String getInput() {
        return SCANNER.nextLine();
    }

    public String getNewState(String state, String input, boolean isAI) {

        if (isWrongFormat(input)) {
            if (isAI) return null;
            System.out.println("You should enter numbers!");
            return null;
        }

        if (isOutOfBound(input)) {
            if (isAI) return null;
            System.out.println("Coordinates should be from 1 to 3!");
            return null;
        }

        if (isOccupied(state, input)) {
            if (isAI) return null;
            System.out.println("This cell is occupied! Choose another one!");
            return null;
        }

        int index = getIndex(input);

        return state.substring(0, index) + getNextPlayer(state) + state.substring(index + 1);

    }

    private boolean isWrongFormat(String input) {
        return !input.matches("\\d \\d");
    }

    private boolean isOutOfBound(String input) {
        return Arrays.stream(input.split(" ")).map(Integer::parseInt).anyMatch(n -> n < 1 || n > 3);
    }

    private boolean isOccupied(String state, String input) {
        return state.charAt(getIndex(input)) != '_';
    }

    private int getIndex(String input) {
        String[] s = input.split(" ");
        return  3 * (Integer.parseInt(s[0]) - 1) + Integer.parseInt(s[1]) - 1;
    }

    private String getNextPlayer(String state) {

        Analyser analyser = new Analyser();
        return analyser.nextPlayer(state);
    }

    public InputCommand getCommand() {
        String[] params = getInput().split(" ", 3);
        if (params.length == 1 && isExit(params[0])) {
            return new InputCommand(Command.EXIT);
        }
        if (params.length == 3 && isStart(params[0]) && isPlayerType(params[1]) && isPlayerType(params[2])) {
            return new InputCommand(Command.START, PlayerType.getPlayerType(params[1]), PlayerType.getPlayerType(params[2]));
        }
        return null;
    }

    private boolean isExit(String param) {
        Command command = Command.getCommand(param);
        if (command == null) return false;
        return command.equals(Command.EXIT);
    }

    private boolean isStart(String param) {
        Command command = Command.getCommand(param);
        if (command == null) return false;
        return command.equals(Command.START);
    }

    private boolean isPlayerType(String param) {
        return PlayerType.getPlayerType(param) != null;
    }

}
