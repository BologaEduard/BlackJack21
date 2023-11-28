package Gamepackage;
import java.awt.BorderLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * The EnterNameWindow represents the window that is pooping up when you hit the "New Game"
 * button. This window helps you the enter your name before you start a game.
 */
public class EnterNameWindow extends JFrame {
    /**
     * The field where the user can write his name.
     */
    private JTextField textField;
    /**
     * User's name.
     */
    private String name;
    /**
     * The frame of the window.
     */
    private JFrame frame;
    /**
     * The user of the game.
     */
    private User user;

    /**
     * Default constructor that initializes an empty window.
     */
    public EnterNameWindow() {
        /*
            -
        */
    }

    /**
     * Creates and displays a window for entering the user's name.
     * The window includes a label, a text field, and a submit button.
     * Upon submitting the name, it initializes a new User, sets the user's name,
     * and starts the EDU CASINO Blackjack game.
     */
    public void EnterNameWindowNew() {
        frame = new JFrame("EDU CASIONO");

        frame.setVisible(true);
        frame.setSize(400,125);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel messageLabel = new JLabel("Enter your name:");
        frame.add(messageLabel, BorderLayout.NORTH);

        textField = new JTextField();
        frame.add(textField, BorderLayout.CENTER);

        Button button = new Button();

        frame.add(button.submitButton, BorderLayout.SOUTH);

        button.submitButton.addActionListener((ActionListener) new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                name = textField.getText();

                user = new User();
                user.setName(name);

                Game BLACKJACK = new Game();
                BLACKJACK.EDUsBLACKJACK(user);

                frame.setVisible(false);
            }

        });

        frame.revalidate();
    }

    /**
     * Creates and displays a window for entering the user's name to load a saved game.
     * The window includes a label, a text field, and a submit button.
     * Upon submitting the name, it retrieves the saved game state using JetonManager,
     * initializes a new Game, and starts the game with the loaded state.
     */
    public void EnterNameWindowLoad() {
        frame = new JFrame("EDU CASIONO");

        frame.setVisible(true);
        frame.setSize(400,125);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel messageLabel = new JLabel("Enter your name:");
        frame.add(messageLabel, BorderLayout.NORTH);

        textField = new JTextField();
        frame.add(textField, BorderLayout.CENTER);

        Button button = new Button();

        frame.add(button.submitButton, BorderLayout.SOUTH);

        button.submitButton.addActionListener((ActionListener) new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                name = textField.getText();

                SavedGameState jetons = JetonManager.Get(name.trim());

                Game loadGame = new Game();
                loadGame.roundAfterLoad(name, jetons);

                frame.setVisible(false);
            }

        });

        frame.revalidate();
    }
    
}