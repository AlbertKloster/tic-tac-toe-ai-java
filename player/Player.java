package tictactoe.player;

import tictactoe.utils.Symbol;

public class Player {
    private final PlayerType playerType;
    private final Symbol symbol;

    public Player(PlayerType playerType, Symbol symbol) {
        this.playerType = playerType;
        this.symbol = symbol;
    }


    public PlayerType getPlayerType() {
        return playerType;
    }

    public Symbol getSymbol() {
        return symbol;
    }

}
