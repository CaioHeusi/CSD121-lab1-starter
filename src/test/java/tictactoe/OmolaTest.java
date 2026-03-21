package tictactoe; // package that this class belongs

import org.junit.jupiter.api.Test; // import junit test annotation
import tictactoe.game.*; // import game pack like board, col, row, position, token
import tictactoe.game.player.Omola; // import Omola class that will be tested

import static org.junit.jupiter.api.Assertions.assertEquals; // assertEquals to compare expected results in tests

class OmolaTest { // Omola test class

    @Test
    void choseWinningMoveWhenAvailable() { // test if omola pick the winning move, when it is available
        var omola = new Omola(Token.X); // create omola with X
        var board = new Board("XX.O....."); // create a board where X could win if pick row top col right cell

        assertEquals(new Position(Row.Top, Col.Right), omola.getNextMove(board)); // verify if Omola really pick top right
    }

    @Test
    void blockOpponentWinningMove() { // test if Omola blocks opponent winning move
        var omola = new Omola(Token.X); // create Omola with X
        var board = new Board("OO.X....."); // create a board where O could win if pick rt cr

        assertEquals(new Position(Row.Top, Col.Right), omola.getNextMove(board)); // verify if Omola blocks picking rt cr
    }

    @Test
    void choseFirstAvailableCellWhenNotWinOrBlock() { // test if Omola will pick next empty cell when don't have winner move or block to do
        var omola = new Omola(Token.O); // create Omola with O this time
        var board = new Board("X....O..."); // create a board where don't have winner and don't need to block

        assertEquals(new Position(Row.Top, Col.Middle), omola.getNextMove(board)); // Check if Omola will pick the next move in reading order ( rt cm)
    }

    @Test
    void constructorSetsNameAndToken() { // test if constructor defined name and token correctly
        var omola = new Omola(Token.X); // create Omola with X

        assertEquals("Omola", omola.name()); // check if name is Omola
        assertEquals(Token.X, omola.token()); // check if token was set X
    }
}