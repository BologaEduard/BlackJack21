package Gamepackage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

/**
 * The JetonManager class manages the saving and loading of game states (jetons) for users.
 * It provides methods for saving, loading, setting, and getting user-specific game states.
 */
public class JetonManager {

    /** The map to store user names and their corresponding SavedGameState objects. */
    private static HashMap<String,SavedGameState> map = new HashMap<>();

    /** Default SavedGameState values for new users. */
    private static final SavedGameState DEFAULT_VALUES = new SavedGameState(1000, 1000);

    /** The file path for saving and loading game states. */
    private static final String SAVE_PATH_FILE = "Jeton_data.jetons";

    /**
     * Saves the current user states to a file.
     */
    public static void Save() {

        try (FileOutputStream output = new FileOutputStream(SAVE_PATH_FILE)) {

            try(ObjectOutputStream oos = new ObjectOutputStream(output)) {
                oos.writeObject(map);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Loads user states from a file into the map.
     * If the file does not exist or an error occurs during loading, initializes an empty map.
     */
    public static void Load() {
        try (FileInputStream input  = new FileInputStream(SAVE_PATH_FILE)) {

            try(ObjectInputStream ois = new ObjectInputStream(input)) {
                map = (HashMap<String, SavedGameState>)ois.readObject();
            } catch (ClassNotFoundException e) {
                map = new HashMap<>();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Sets the SavedGameState for a specific user.
     *
     * @param name  The name of the user.
     * @param state The SavedGameState to be associated with the user.
     */
    public static void Set(String name, SavedGameState state) {
        map.put(name, state);
    }

    /**
     * Gets the SavedGameState associated with a specific user.
     * If no state is found for the user, returns the default values.
     *
     * @param name The name of the user.
     * @return The SavedGameState associated with the user.
     */
    public static SavedGameState Get(String name) {
        return map.getOrDefault(name, DEFAULT_VALUES);
    }
    
}
