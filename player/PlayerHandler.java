package tictactoe.player;

import tictactoe.tree.Node;
import tictactoe.tree.TreeHandler;
import tictactoe.utils.Input;
import tictactoe.utils.Symbol;

import java.util.List;
import java.util.Random;

public class PlayerHandler {

    private final Player player;
    private final TreeHandler treeHandler;


    public PlayerHandler(Player player, TreeHandler treeHandler) {
        this.player = player;
        this.treeHandler = treeHandler;
    }

    public String play(String state) {
        String newState = null;
        Input input = new Input();
        if (player.getPlayerType().equals(PlayerType.USER)) {
            while (newState == null) {
                System.out.print("Enter the coordinates: ");
                newState = input.getNewState(state, input.getInput(), false);
            }
        }

        if (player.getPlayerType().equals(PlayerType.EASY)) {
            System.out.println("Making move level \"easy\"");
            Random random = new Random();
            while (newState == null) {
                int row = random.nextInt(3) + 1;
                int column = random.nextInt(3) + 1;
                newState = input.getNewState(state, row + " " + column, true);
            }
        }

        if (player.getPlayerType().equals(PlayerType.MEDIUM)) {
            System.out.println("Making move level \"medium\"");
            String s = player.getSymbol().name();
            newState = getNewStateMedium(state, s);
            if (newState != null)
                return newState;

            s = s.equals("X") ? "O" : "X";

            newState = getNewStateMedium(state, s);
            if (newState != null)
                return newState;

            Random random = new Random();
            while (newState == null) {
                int row = random.nextInt(3) + 1;
                int column = random.nextInt(3) + 1;
                newState = input.getNewState(state, row + " " + column, true);
            }
        }

        if (player.getPlayerType().equals(PlayerType.HARD)) {
            System.out.println("Making move level \"hard\"");

            // random first move
            if (state.equals("_".repeat(9))) {
                Random random = new Random();
                int row = random.nextInt(3) + 1;
                int column = random.nextInt(3) + 1;
                return input.getNewState(state, row + " " + column, true);
            }

            Node nodeByState = treeHandler.getNodeByState(state);
            List<Node> nextNodes = nodeByState.getNextNodes();
            Node newStateNode = nextNodes.get(0);
            for (Node node : nextNodes) {
                if (player.getSymbol().equals(Symbol.X)) {
                    if (node.getScore() < newStateNode.getScore()) {
                        newStateNode = node;
                    }
                } else {
                    if (node.getScore() > newStateNode.getScore()) {
                        newStateNode = node;
                    }
                }
            }
            newState = newStateNode.getState();
        }
        return newState;
    }

    private String getNewStateMedium(String state, String s) {
        Input input = new Input();
        String newState = null;
        if (state.matches(String.format("%s..%s.._..", s, s))) newState = input.getNewState(state, "3 1", true);
        if (state.matches(String.format("%s.._..%s..", s, s))) newState = input.getNewState(state, "2 1", true);
        if (state.matches(String.format("_..%s..%s..", s, s))) newState = input.getNewState(state, "1 1", true);
        if (state.matches(String.format(".%s..%s.._.", s, s))) newState = input.getNewState(state, "3 2", true);
        if (state.matches(String.format(".%s.._..%s.", s, s))) newState = input.getNewState(state, "2 2", true);
        if (state.matches(String.format("._..%s..%s.", s, s))) newState = input.getNewState(state, "1 2", true);
        if (state.matches(String.format("..%s..%s.._", s, s))) newState = input.getNewState(state, "3 3", true);
        if (state.matches(String.format("..%s.._..%s", s, s))) newState = input.getNewState(state, "2 3", true);
        if (state.matches(String.format(".._..%s..%s", s, s))) newState = input.getNewState(state, "1 3", true);

        if (state.matches(String.format("_%s%s......", s, s))) newState = input.getNewState(state, "1 1", true);
        if (state.matches(String.format("%s_%s......", s, s))) newState = input.getNewState(state, "1 2", true);
        if (state.matches(String.format("%s%s_......", s, s))) newState = input.getNewState(state, "1 3", true);
        if (state.matches(String.format("..._%s%s...", s, s))) newState = input.getNewState(state, "2 1", true);
        if (state.matches(String.format("...%s_%s...", s, s))) newState = input.getNewState(state, "2 2", true);
        if (state.matches(String.format("...%s%s_...", s, s))) newState = input.getNewState(state, "2 3", true);
        if (state.matches(String.format("......_%s%s", s, s))) newState = input.getNewState(state, "3 1", true);
        if (state.matches(String.format("......%s_%s", s, s))) newState = input.getNewState(state, "3 2", true);
        if (state.matches(String.format("......%s%s_", s, s))) newState = input.getNewState(state, "3 3", true);

        if (state.matches(String.format("%s...%s..._", s, s))) newState = input.getNewState(state, "3 3", true);
        if (state.matches(String.format("%s..._...%s", s, s))) newState = input.getNewState(state, "2 2", true);
        if (state.matches(String.format("_...%s...%s", s, s))) newState = input.getNewState(state, "1 1", true);
        if (state.matches(String.format(".._.%s.%s..", s, s))) newState = input.getNewState(state, "1 3", true);
        if (state.matches(String.format("..%s.%s._..", s, s))) newState = input.getNewState(state, "3 1", true);
        return newState;
    }


}
