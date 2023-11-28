package Gamepackage;
import java.util.ArrayList;
import java.util.Collection;

/**
 * The WeakBot class represents a simplified dealer (bot) in the Blackjack game.
 * It extends the Player class and includes additional methods and attributes specific to the bot.
 */
public class WeakBot extends Player{
    /** The hidden card that the bot keeps faced down. */
     public Card hiddenCard;
    /** The face-up card (the first card) of the bot. */
     public Card faceduupcard;

    /**
     * Constructs a new instance of the WeakBot class with a specified deck of cards.
     * Initializes the bot's hand, hidden card, face-up card, and prints bot data.
     *
     * @param deck The deck of cards to draw from.
     */
    public WeakBot(ArrayList<Card> deck) {
        hand = new ArrayList<Card>();
        hiddenCard = deck.remove(deck.size()-1);
        faceduupcard = deck.remove(deck.size() - 1);
        handValue = hiddenCard.getValue() + faceduupcard.getValue();
        acecount = (hiddenCard.isAce() ? 1 : 0) + (faceduupcard.isAce() ? 1 : 0);
        hand.add(faceduupcard);

        printBotData();
    }

    /**
     * Retrieves the face-up card of the bot.
     *
     * @return The face-up card of the bot.
     */
    public Card getFaceUpCard() {
        if (hand.isEmpty()) {
            return null;
        }
        return hand.get(0);
    }

    /**
     * Simulates the bot's decision-making process for the next move in the game.
     * The bot will hit until the hand value is 17 or higher.
     *
     * @param deck The deck of cards to draw from.
     */
    public void decideNextMove(Deck deck) {
        while (handValue < 17) {
            Card newcard = deck.DeckRemovelast(deck.GetNewCardFromDeck());
            handValue += newcard.getValue();
            acecount += newcard.isAce()? 1:0;
            hand.add(newcard);
            if(handValue > 21 && acecount > 0) {
                handValue = AceToOne();
            }
        }
    }

    /**
     * Prints information about the bot's hidden card, hand, hand value, ace count, and jetons.
     */
    public void printBotData() {
        System.out.println("BOT:");
        System.out.println("Hidden card: " + hiddenCard);
        System.out.println("Hand:" + hand);
        System.out.println("Hand value: " + handValue);
        System.out.println("Ace count:" + acecount);
        System.out.println("Jetons: " + jetons);
    }

    /**
     * Retrieves the amount of jetons the bot possesses.
     *
     * @return The amount of jetons for the bot.
     */
    public int getJetons() {
        return jetons;
    }

    /**
     * Checks if the bot has a blackjack (an Ace and a 10-value card) with the initial two cards.
     *
     * @return true if the bot has a blackjack, false otherwise.
     */
    public boolean hasBlackjack() {
        if (hand.size() == 2 && hiddenCard.getValue() + faceduupcard.getValue() == 21) {
            for (Card card : hand) {
                if (card.isAce() || card.getValue() == 10) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Prepares the bot for the next round by resetting the hand, hidden card, face-up card, and bet.
     *
     * @param deck The deck of cards to draw from for the next round.
     */
    public void setBotForNextRound(ArrayList<Card> deck) {
        hand = new ArrayList<Card>();
        hiddenCard = deck.remove(deck.size()-1);
        faceduupcard = deck.remove(deck.size() - 1);
        handValue = hiddenCard.getValue() + faceduupcard.getValue();
        acecount = (hiddenCard.isAce() ? 1 : 0) + (faceduupcard.isAce() ? 1 : 0);
        hand.add(faceduupcard);
        bet = 0;
    }
   
}
