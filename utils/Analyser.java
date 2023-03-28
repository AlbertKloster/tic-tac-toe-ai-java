package tictactoe.utils;

public class Analyser {

    private final String[] X = {
            "XXX......",
            "...XXX...",
            "......XXX",
            "X..X..X..",
            ".X..X..X.",
            "..X..X..X",
            "X...X...X",
            "..X.X.X..",
    };

    public boolean isX(String state) {
        for (String x : X) {
            if (state.matches(x)) return true;
        }
        return false;
    }

    public boolean isO(String state) {
        for (String x : X)
            if (state.matches(x.replaceAll("X", "O"))) return true;
        return false;
    }

    public boolean isDraw(String state) {
        return state.matches("[XO]{9}") && !isO(state) && !isX(state);
    }

    public long countX(String state) {
        return state.chars().filter(ch -> ch == 'X').count();
    }

    public long countO(String state) {
        return state.chars().filter(ch -> ch == 'O').count();
    }

    public String nextPlayer(String state) {
        return countO(state) < countX(state) ? "O" : "X";
    }

    public boolean isGameOver(String state) {
        if (isDraw(state)) {
            System.out.println("Draw");
            return true;
        }

        if (isX(state)) {
            System.out.println("X wins");
            return true;
        }

        if (isO(state)) {
            System.out.println("O wins");
            return true;
        }
        return false;
    }

}
