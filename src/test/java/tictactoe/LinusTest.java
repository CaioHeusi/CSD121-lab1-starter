package tictactoe; // this file belongs tictactoe package

import org.junit.jupiter.api.Test; // import test junit annotation
import tictactoe.game.Board; // import board to test different scenarios
import tictactoe.game.Col; // import column to build expected positions
import tictactoe.game.Position; // import position to compare expected move
import tictactoe.game.Row; // import row to build expected positions
import tictactoe.game.Token; // import token to create player with X or O
import tictactoe.game.player.Linus; // import linus class which will be tested

import static org.junit.jupiter.api.Assertions.assertEquals; // import asserEquals to compare expected board with real

class LinusTest { // linus test class

    @Test
    void choseTopLeftOnEmptyBoard() { // test if linus pick rt cl position in an empty board
        var linus = new Linus(Token.X); // create linus with token X
        var board = new Board(); // create an empty board

        assertEquals(new Position(Row.Top, Col.Left), linus.getNextMove(board)); // test if it got the first cell in reading order (rt, cl), because the board is empty
    }

    @Test
    void choseNextEmptyAvailableCellInReadingOrder() { // test if linus pick the next empty cell available in reading order
        var linus = new Linus(Token.O); // create linus with token O
        var board = new Board("XX.O....."); // create a board with some positions already taken

        assertEquals(new Position(Row.Top, Col.Right), linus.getNextMove(board)); // test if linus will pick the next available in reading order
    }

    @Test
    void skipFilledCellAndKeepInReadingOrder() { // test if linus skip positions that is already occupied, and continues it in reading order
        var linus = new Linus(Token.X); // create linus with token X
        var board = new Board("XOXOXO..."); // create a board where top row and middle row are already has been taken

        assertEquals(new Position(Row.Bottom, Col.Left), linus.getNextMove(board)); // verify if linus skip filled cells in reading order until find an empty space
    }

    @Test
    void constructorSetNameAndToken() { // test if constructor defined name and token correctly
        var linus = new Linus(Token.O); // create linus with token O

        assertEquals("Linus", linus.name()); // verify if name is linus
        assertEquals(Token.O, linus.token()); // check if token is O
    }
}
