import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Gamepackage.Card;
import Gamepackage.Player;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;


public class PlayerTest {

    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player();
    }

    @Test
    void addCard1() {
        player.addCard(new Card("10", "K-H"));
        assertEquals(10, player.getHandValue());
    }

    @Test
    void addCard2() {
        player.addCard(new Card("10", "K-D"));
        player.addCard(new Card("10", "Q-D"));
        assertEquals(20, player.getHandValue());
    }

    @Test
    void noMoney() {
        player.jetons = 0;
        assertTrue(player.outOfMoney());
    }

    @Test
    void aceInHand() {
        player.addCard(new Card("A", "A-D"));
        player.addCard(new Card("10", "10-D"));
        assertTrue(player.hasAce());
    }

    @Test
    void clearedHand() {
        player.addCard(new Card("10", "10-D"));
        player.addCard(new Card("2", "2-D"));
        player.clearHand();
        assertEquals(0, player.getHandValue());
    }
}
