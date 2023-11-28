package Gamepackage;
import java.util.ArrayList;

/**
 * The User class represents the user in the Blackjack game.
 * It extends the Player class and includes additional methods and attributes specific to the user.
 */
public class User extends Player {
    /** The first card dealt to the user at the start of the game. */
    public Card startcard1;
    /** The second card dealt to the user at the start of the game. */
    public Card startcard2;
    /** The name of the user. */
    public String name;

    /**
     * Constructs a new instance of the User class with default values.
     * Used when creating a User object without specific parameters.
     */
    public User() {
        startcard1 = new Card();
        startcard2 = new Card();
        name = " ";
    }

    /**
     * Constructs a new instance of the User class with a specified deck of cards.
     * Initializes the user's hand, name, and prints user data.
     *
     * @param deck The deck of cards to draw from.
     */
    public User(ArrayList<Card> deck) {
        hand = new ArrayList<Card>();
        startcard1 = deck.remove(deck.size() - 1);
        startcard2 = deck.remove(deck.size() - 1);
        handValue = startcard1.getValue() + startcard2.getValue();
        acecount = (startcard1.isAce() ? 1 : 0) + (startcard2.isAce() ? 1 : 0);
        hand.add(startcard1);
        hand.add(startcard2);

        printUserData();

    }

    /**
     * Prints information about the user's hand, hand value, ace count, and jetons.
     */
    public void printUserData() {
        System.out.println("USER:");
        System.out.println("User's hand:" + hand);
        System.out.println("Hand value: " + handValue);
        System.out.println("Ace count: " + acecount);
        System.out.println("Jetons: " + jetons);
    }

    /**
     * Checks if the user has a blackjack (an Ace and a 10-value card) with the initial two cards.
     *
     * @return true if the user has a blackjack, false otherwise.
     */
    public boolean hasBlackjack() {
        if (hand.size() == 2 && handValue == 21) {
            for (Card card : hand) {
                if (card.isAce() || card.getValue() == 10) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Retrieves the amount of jetons the user possesses.
     *
     * @return The amount of jetons for the user.
     */
    public int getJetons() {
        return jetons;
    }

    /**
     * Prepares the user for the next round by resetting the hand and bet.
     *
     * @param deck The deck of cards to draw from for the next round.
     */
    public void setUserForNextRound(ArrayList<Card> deck) {
        hand = new ArrayList<Card>();
        startcard1 = deck.remove(deck.size() - 1);
        startcard2 = deck.remove(deck.size() - 1);
        handValue = startcard1.getValue() + startcard2.getValue();
        acecount = (startcard1.isAce() ? 1 : 0) + (startcard2.isAce() ? 1 : 0);
        hand.add(startcard1);
        hand.add(startcard2);
        bet = 0;
    }

    /**
     * Sets the name of the user.
     *
     * @param name The name to set for the user.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the name of the user.
     *
     * @return The name of the user.
     */
    public String getName() {
        return name;
    }

    /**
     * Prints the name of the user for debugging.
     */
    public void printName() {
        System.out.println("USER NAME: "+ name);
    }

    /**
     * Sets the first card dealt to the user.
     *
     * @param card The card to set as the first card.
     */
    public void setCard1(Card card) {
        this.startcard1 = card;
    }

    /**
     * Sets the second card dealt to the user.
     *
     * @param card The card to set as the second card.
     */
    public void setCard2(Card card) {
        this.startcard1 = card;
    }
    
    
}
