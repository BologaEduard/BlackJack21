package Gamepackage;
import java.io.Serializable;

/**
 * The SavedGameState class represents the state of a Blackjack game that can be saved and loaded.
 * It includes information about the jetons (game currency) for both the user and the bot.
 */
public class SavedGameState implements Serializable{
    /** The amount of jetons for the user. */
    private int userJetons;
    /** The amount of jetons for the bot. */
    private int botJetons;

    /**
     * Constructs a new instance of the SavedGameState class with specified jeton amounts.
     *
     * @param ujetons The amount of jetons for the user.
     * @param bjetons The amount of jetons for the bot.
     */
    public SavedGameState(int ujetons, int bjetons) {
        userJetons = ujetons;
        botJetons = bjetons;
    }

    /**
     * Retrieves the amount of jetons for the user.
     *
     * @return The amount of jetons for the user.
     */
    public int getuserJetons() {
        return userJetons;
    }

    /**
     * Retrieves the amount of jetons for the bot.
     *
     * @return The amount of jetons for the bot.
     */
    public int getbotJetons() {
        return botJetons;
    }

    /**
     * Sets the amount of jetons for the user.
     *
     * @param jetons The new amount of jetons for the user.
     */
    public void setuserJetons(int jetons) {
        userJetons  = jetons;
    }

    /**
     * Sets the amount of jetons for the bot.
     *
     * @param jetons The new amount of jetons for the bot.
     */
    public void setbotJetons(int jetons) {
        botJetons  = jetons;
    }
}
