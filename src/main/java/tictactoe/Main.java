package tictactoe; // this file is part of main package of program

import tictactoe.game.TicTacToeGame; // import class that controls the game
import tictactoe.game.Token; // import token to use X and O
import tictactoe.ui.Console; // import console to print messages and ask input

import static tictactoe.game.TicTacToeGame.Status.*; // import game status to use InProgress, Draw, XWins, OWins directly

class Main { // main program class
    static void main() { // main method that will run the game

        Console.println("Welcome to Tic Tac Toe!"); // welcoming message
        Console.println("Human player: Type your name..."); // explain how user pick human player
        Console.println("Compute Players: "); // show title of compute players part
        Console.println("@Linus - will pick first available position. (Basic AI)"); // explain what linus does
        Console.println("@Omola - looks one move ahead. (Advanced AI)"); // explain what omola does
        var playerX = Console.promptForPlayer(Token.X); // ask who will play with X
        var playerO = Console.promptForPlayer(Token.O); // ask who will play with O
        var game = new TicTacToeGame(playerX, playerO); // create the game with both picked players

        while (game.getStatus() == InProgress) { // while loop to continue the game while game io ngoin

            var turnData = game.doNextTurn(); // do a turn and store its data into a variable

            Console.println("%s plays %s at %s %s".formatted(turnData.whoseTurn().name(), turnData.whoseTurn().token(), turnData.positionPlayed().row(), turnData.positionPlayed().col())); // print formatted str int terminal showing who played, witch token and which position
            Console.showBoard(turnData.newBoardState()); // print in terminal new board state

            switch (game.getStatus()) { // verify game status after turn
                case Draw -> Console.println("It's a draw!"); // if it is draw, print draw message
                case XWins, OWins -> Console.println("%s wins!".formatted(turnData.whoseTurn().name())); // if X or O wins, show winner name
            }

        }
    }
}