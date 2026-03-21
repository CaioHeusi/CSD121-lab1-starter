package tictactoe.game; // this file is part of package .game

/**
 * A data type to represent each token on the board
 */
public enum Token { // Enum which represent two possible tokens in game
    X, // token X
    O; // token O

    /**
     * @return opponent token
     */
    public Token opponent() { // return opponent token to be used in Omola
        return this == X ? O : X; // if this token is X, return O else return X
    }
}
