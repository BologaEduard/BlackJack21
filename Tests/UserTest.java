import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeFalse;
import static org.junit.Assume.assumeTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import Gamepackage.Card;
import Gamepackage.Deck;
import Gamepackage.User;

public class UserTest {
    
    private User user;

    @Before
    public void setUp() {
        user = new User();
    }

    @Test
    public void nameCheck() {
        user.setName("Fekete Pako");
        assertEquals("Fekete Pako", user.getName());
    }

    @Test
    public void hasBlackjackTest() {
        Deck deck = new Deck();
        deck.buildDeck();

        user = new User(deck.getDeck());

        Card card1 = new Card("10", "K-H");
        Card card2 = new Card("11", "A-D");

        user.setCard1(card1);
        user.setCard2(card2);

        user.handValue = user.startcard1.getValue() + user.startcard2.getValue();

        assertTrue(user.hasBlackjack());
    }
 
}
