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

public class EnterNameWindow extends JFrame {
    private JTextField textField;
    private String name;
    private JFrame frame;
    private User user;

    public EnterNameWindow() {
        /*
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
        */
    }

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