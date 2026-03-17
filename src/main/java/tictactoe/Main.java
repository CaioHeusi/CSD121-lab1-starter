package tictactoe;

import tictactoe.game.TicTacToeGame;
import tictactoe.game.Position;
import tictactoe.game.Token;
import tictactoe.ui.Console;

import static tictactoe.game.TicTacToeGame.Status.*;

class Main {
    static void main() {

        Console.println("Welcome to Tic Tac Toe!");
        Console.println("Human player: Type your name...");
        Console.println("Compute Players: ");
        Console.println("@Linus - will pick first available position. (Basic AI)");
        Console.println("@Omola - looks one move ahead. (Advanced AI)");
        var playerX = Console.promptForPlayer(Token.X);
        var playerO = Console.promptForPlayer(Token.O);
        var game = new TicTacToeGame(playerX, playerO);

        while (game.getStatus() == InProgress) {

            var turnData = game.doNextTurn();

            Console.println("%s plays %s at %s %s".formatted(turnData.whoseTurn().name(), turnData.whoseTurn().token(), turnData.positionPlayed().row(), turnData.positionPlayed().col()));
            Console.showBoard(turnData.newBoardState());

            switch (game.getStatus()) {
                case Draw -> Console.println("It's a draw!");
                case XWins, OWins -> Console.println("%s wins!".formatted(turnData.whoseTurn().name()));
            }

        }
    }
}