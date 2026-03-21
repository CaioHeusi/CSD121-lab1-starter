package tictactoe.game.player; // package that this class belongs

import tictactoe.game.Board; // import board to read and copy
import tictactoe.game.Position; // import position to represent a game board position, a move pick
import tictactoe.game.Token; // import token representing each token on the board (X and O)

/**
 * One MOve Look-Ahead.
 * Omola will pick a winning move if available,
 * otherwise blocks the opponent's winning move,
 * otherwise picks the first available position.
 */
public class Omola extends Player { // Omola class that inherits Player

    /**
     * Create an Omola player with given token
     * @param token token used by Omola
     */
    public Omola(Token token) { // Omola class constructor
        super("Omola", token); // calling constructor from superclass Player, name will be always Omola
    }

    @Override // override getNextMove to implement look-ahead logic
    public Position getNextMove(Board board) {
        var emptyCells = board.getEmptyCells(); // stores the empty positions remaining on the board

        // If Omola can win now, take that move

        for (var pos : emptyCells) { // go through every empty position at table to test if Omola could win immediately
            var boardCopy = new Board(board); // create a board to simulate a move without change the real board
            boardCopy.place(pos, token()); // simulate Omola placing a token on that position

            var winner = boardCopy.getWinner(); // If it has a winner, hold in this variable
            if (winner.isPresent() && winner.get() == token()) { // If a winner is present and this winner token is Omola
                return pos; // If this move could win the game, take that position
            }
        }


        // In this block Omola will predict enemy move

        var opponent = token().opponent(); // if could not possible to win in this round, get enemy token, and stores it in a variable

        for (var pos : emptyCells) { // for loop to go through every empty position on the board
            var boardCopy = new Board(board); // create a copy of the table to simulate opponent moves, and store it to a variable
            boardCopy.place(pos, opponent); // simulating enemy placing token on that position

            var winner = boardCopy.getWinner(); // hold a winner if it exists
            if (winner.isPresent() && winner.get() == opponent) { // If a winner is present and this winner is the opponent,
                return pos; // then, return position to block opponent
            }
        }

        // otherwise, just pick the first empty cell available
        return emptyCells.get(0);
    }
}


/*
To beat Omola:
    start game
    pick first position which is token X and write your name
    do this moves: 22, 32, 13, 31
 */