package Gamepackage;

public class Game {

    public int bet = -1;
    public WeakBot bot;

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

    public void nextRound(User oguser, WeakBot ogbot) {

        System.out.println("You have just started a new round my friend!");

        Deck deck2 = new Deck();
        deck2.buildDeck();
        deck2.shuffleDeck();

        oguser.setUserForNextRound(deck2.getDeck());
        ogbot.setBotForNextRound(deck2.getDeck());

        GameWindow window1 = new GameWindow(ogbot, oguser, deck2);

    }

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
