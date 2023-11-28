package Gamepackage;
import java.util.ArrayList;
import java.util.Random;

/**
 * The Deck class represents a deck of playing cards.
 */
public class Deck {
    /** The list of cards in the deck. */
    private ArrayList<Card> deck;

    /** Default constructor that initializes an empty deck. */
    public Deck() {

        deck = new ArrayList<>();
    }

    /**
     * Builds a standard deck of 52 playing cards.
     * The deck is initially empty and is populated with cards of various values and types.
     */
    public void buildDeck() {
        String[] values = new String[13];

        for(int i=2; i<11; i++)
            values[i-1] = Integer.toString(i);

        values[0] = "A";
        values[10] = "J";
        values[11] = "Q";
        values[12] = "K";

        String[] types = {"C", "D", "H", "S"};

        for(int i=0; i< types.length; i++) {
            for(int j=0; j< values.length; j++) {
                Card card = new Card(values[j], types[i] );
                deck.add(card);
            }
        }

        System.out.println("DECK:");
        System.out.println(deck);

    }

    /**
     * Shuffles the deck by randomly swapping cards.
     */
    public  void  shuffleDeck() {
        for(int i=0; i< deck.size(); i++) {
            Random random = new Random();
            int r = random.nextInt(deck.size());

            Card current = deck.get(i);
            Card randomcard  = deck.get(r);

            deck.set(i, randomcard);
            deck.set(r, current);
        }

        System.out.println("SCHUFFELED DECK:");
        System.out.println(deck);

    }

    /**
     * Returns the list of cards in the deck.
     *
     * @return The list of cards in the deck.
     */
    public ArrayList<Card> getDeck() {
        return deck;
    }

    /**
     * Gets the index of the last card in the deck.
     *
     * @return The index of the last card in the deck.
     */
    public int GetNewCardFromDeck() {
        return deck.size() - 1;
    }

    /**
     * Removes and returns the card at the specified index in the deck.
     *
     * @param last The index of the card to be removed.
     * @return The removed card.
     */
    public Card DeckRemovelast(int last) {
        return deck.remove(last);
    }

    /**
     * Prints the remaining cards in the deck.
     */
    public void printDeck() {
        System.out.print("REMAINED DECK: ");
        for (int i = 0; i < deck.size(); i++) {
            System.out.print(deck.get(i));
            if (i < deck.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }

}
