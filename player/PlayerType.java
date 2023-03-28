package tictactoe.player;

public enum PlayerType {
    USER("user"), EASY("easy"), MEDIUM("medium"), HARD("hard");

    public final String playerType;

    PlayerType(String playerType) {
        this.playerType = playerType;
    }

    public static PlayerType getPlayerType(String input) {
        for (PlayerType playerType : PlayerType.values()) {
            if (playerType.playerType.equals(input.toLowerCase())) {
                return playerType;
            }
        }
        return null;
    }
}
