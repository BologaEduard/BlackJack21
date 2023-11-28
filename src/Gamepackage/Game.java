package Gamepackage;

/**
 * The Game class represents the core logic of the Blackjack game.
 * It initializes and controls the game flow, including starting new rounds
 * and handling game states after loading.
 */
public class Game {
    /** The bot used in the game. */
    public WeakBot bot;

    /**
     * Starts a new Blackjack game.
     *
     * @param oguser The original user object with a name.
     */
    public void EDUsBLACKJACK(User oguser) {
        Deck deck1 = new Deck();
        deck1.buildDeck();
        deck1.shuffleDeck();

        bot = new WeakBot(deck1.getDeck());
        User user = new User(deck1.getDeck());

        user.name = oguser.name;

        user.printName();

        deck1.printDeck();

        GameWindow window1 = new GameWindow(bot, user, deck1);
        
    }

    /**
     * Starts the next round of the Blackjack game.
     *
     * @param oguser The original user object from the previous round.
     * @param ogbot  The original bot object from the previous round.
     */
    public void nextRound(User oguser, WeakBot ogbot) {

        System.out.println("You have just started a new round my friend!");

        Deck deck2 = new Deck();
        deck2.buildDeck();
        deck2.shuffleDeck();

        oguser.setUserForNextRound(deck2.getDeck());
        ogbot.setBotForNextRound(deck2.getDeck());

        GameWindow window1 = new GameWindow(ogbot, oguser, deck2);

    }

    /**
     * Resumes the Blackjack game after loading a saved state.
     *
     * @param name  The user's name.
     * @param state The saved game state containing jetons information.
     */
    public void roundAfterLoad(String name, SavedGameState state) {
        Deck deck1 = new Deck();
        deck1.buildDeck();
        deck1.shuffleDeck();

        bot = new WeakBot(deck1.getDeck());
        User user = new User(deck1.getDeck());

        user.name = name;
        user.jetons = state.getuserJetons();

        bot.jetons = state.getbotJetons();

        user.printName();

        deck1.printDeck();

        GameWindow window1 = new GameWindow(bot, user, deck1);
    }
}
