/*
    NOTE:

    Add to this type any variables and methods required
    to represent and manipulate the overall state of the high card game.

    You MAY change this to a record/enum as you see fit.
 */
package core;

/**
 * Engine of a high card game with state and rules
 * Deck is shuffled and distributed equally between players, and each round both players draw one card
 */
public class HighCardGame {

    public record RoundResult(Card c1, Card c2, Player winner) { // record to store the round results
    }

    private final Player p1; // players stored in the game
    private final Player p2;

    public HighCardGame(String p1Name, String p2Name) { // constructor to create the game with names
        this.p1 = new Player(p1Name); // initialize the game with players names
        this.p2 = new Player(p2Name);

        CardStack deck = new CardStack(); // create a deck
        deck.createFullDeck(); // fill with 52 cards
        deck.shuffle(); // shuffle using collections

        for (int i = 0; i < 26; i += 1) { // it's a loop for repeats for 26 times
            p1.hand().add(deck.draw()); // give a card to each player
            p2.hand().add(deck.draw());
        }
    }

    public Player player1() { // method to access players
        return p1;
    }

    public Player player2() {
        return p2;
    }

    public boolean canPlayRound() { // it's to verify if still can play
        return p1.hand().hasCards() && p2.hand().hasCards(); // boolean to check if has cards for both
    }

    public RoundResult playRound() { // play a round
        Card c1 = p1.hand().draw(); // each one draw a card from the top of the deck
        Card c2 = p2.hand().draw();

        int cmp = c1.rank().ordinal() - c2.rank().ordinal(); // this will compare by ordinal of enum
        Player winner = (cmp > 0) ? p1 : (cmp < 0) ? p2 : null; // decide the winner player

        return new RoundResult(c1, c2, winner); // return round result
    }
}
