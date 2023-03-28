package tictactoe;

import tictactoe.player.Player;
import tictactoe.player.PlayerHandler;
import tictactoe.tree.TreeHandler;
import tictactoe.utils.*;

public class Main {
    public static void main(String[] args) {
        TreeHandler treeHandler = new TreeHandler();

        String state = getInitialState();
        Output output = new Output();
        Input input = new Input();
        while (true) {
            InputCommand inputCommand = null;

            while (inputCommand == null) {
                System.out.print("Input command: ");
                inputCommand = input.getCommand();
                if (inputCommand == null) {
                    System.out.println("Bad parameters!");
                }
            }

            if (inputCommand.getCommand().equals(Command.EXIT)) return;

            output.print(state);
            Analyser analyser = new Analyser();

            Player player1 = new Player(inputCommand.getPlayerType1(), Symbol.X);
            PlayerHandler playerHandler1 = new PlayerHandler(player1, treeHandler);
            Player player2 = new Player(inputCommand.getPlayerType2(), Symbol.O);
            PlayerHandler playerHandler2 = new PlayerHandler(player2, treeHandler);

            boolean gameOver = false;
            PlayerHandler currentPlayerHandler = playerHandler1;
            while (!gameOver) {

                state = currentPlayerHandler.play(state);
                output.print(state);

                gameOver = analyser.isGameOver(state);

                currentPlayerHandler = currentPlayerHandler.equals(playerHandler1) ? playerHandler2 : playerHandler1;

            }
            state = getInitialState();
        }
    }

    private static String getInitialState() {
        return "_".repeat(9);
    }
}
