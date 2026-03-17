package tictactoe.game;

/**
 * A data type to represent each token on the board
 */
public enum Token {
    X, O;

    /**
     * @return opponent token
     */
    public Token opponent() {
        return this == X ? O : X;
    }
}
