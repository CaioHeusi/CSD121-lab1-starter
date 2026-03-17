package tictactoe;

import org.junit.jupiter.api.Test;
import tictactoe.game.*;
import tictactoe.game.player.Omola;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OmolaTest {

    @Test
    void choseWinningMoveWhenAvailable() {
        var omola = new Omola(Token.X);
        var board = new Board("XX.O.....");

        assertEquals(new Position(Row.Top, Col.Right), omola.getNextMove(board));
    }

    @Test
    void blockOpponentWinningMove() {
        var omola = new Omola(Token.X);
        var board = new Board("OO.X.....");

        assertEquals(new Position(Row.Top, Col.Right), omola.getNextMove(board));
    }

    @Test
    void choseFirstAvailableCellWhenNotWinOrBlock() {
        var omola = new Omola(Token.O);
        var board = new Board("X....O...");

        assertEquals(new Position(Row.Top, Col.Middle), omola.getNextMove(board));
    }

    @Test
    void constructorSetsNameAndToken() {
        var omola = new Omola(Token.X);

        assertEquals("Omola", omola.name());
        assertEquals(Token.X, omola.token());
    }
}