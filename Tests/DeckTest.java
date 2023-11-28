import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import Gamepackage.Card;
import Gamepackage.Deck;
import Gamepackage.Player;

public class DeckTest {
    
    private Deck deck;

    @Before
    public void setUp() {
        deck = new Deck();
    }

    @Test
    public void buildDeck() {
        deck.buildDeck();
        assertNotNull(deck.getDeck());
    }

    @Test
    public void correctCardNumber() {
        deck.buildDeck();
        assertEquals(52, deck.getDeck().size());
    }

    @Test
    public void goodSchuffle() {
        deck.buildDeck();

        ArrayList<Card> ogdeck = new ArrayList<>(deck.getDeck());

        deck.shuffleDeck();

        assertNotEquals(ogdeck, deck.getDeck());
    }



}
