package Gamepackage;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The MenuWindow class represents the main menu window of the Blackjack game.
 * It includes buttons for starting a new game and loading a saved game.
 */
public class MenuWindow{

    /** The width of the menu window. */
    private int menuWidth;
    /** The height of the menu window. */
    private int menuheight;
    /** The main JFrame for the menu window. */
    private JFrame frame;

    /**
     * Constructs a new instance of the MenuWindow class.
     * Initializes the menu dimensions, buttons, and sets up the menu window.
     */
    public MenuWindow() {
        menuWidth = 1300;
        menuheight = 740;

        Button button  = new Button();

        JetonManager.Load();

        frame = new JFrame("EDU CASIONO");

        frame.setVisible(true);
        frame.setSize(menuWidth,menuheight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setMenu();

        button.buttonPanel.setBounds(0, menuheight - 50, menuWidth, 75);
        button.starButton.setFocusable(false);
        button.buttonPanel.add(button.starButton);

        button.loadButton.setFocusable(false);
        button.buttonPanel.add(button.loadButton);

        frame.add(button.buttonPanel, BorderLayout.SOUTH);

        // Add action listeners for the New Game and Load buttons.
        button.starButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EnterNameWindow enterName = new EnterNameWindow();
                enterName.EnterNameWindowNew();
                frame.setVisible(false);
            }
        
        });

        button.loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EnterNameWindow namewindow = new EnterNameWindow();
                namewindow.EnterNameWindowLoad();

                namewindow.setVisible(false);

                frame.setVisible(false);
            }   
        });

        frame.revalidate();

    }

    /**
     * Sets the background image for the menu window.
     */
    public void setMenu() {
        ImageIcon bgIcon = new ImageIcon("educasino.jpg");
        JLabel bgLabel = new JLabel(bgIcon);
        frame.getContentPane().add(bgLabel, BorderLayout.CENTER);
    }
}
