package Gamepackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.KeyStroke;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;

/**
 * The GameWindow class represents the main game window for the Blackjack game.
 * It displays the game board, cards, buttons, and handles user interactions.
 */
public class GameWindow {
    private int boardWidth;
    private int boardheight; 
    private JFrame frame;
    private JPanel gamePanel;
    private int cardWidth;
    private int cardHeight;
    private int total;

    /**
     * Constructs a new instance of the GameWindow class.
     *
     * @param bot  The bot (dealer) in the game.
     * @param user The user playing the game.
     * @param deck The deck of cards used in the game.
     */
    public GameWindow (WeakBot bot, User user,Deck deck) {
        boardWidth = 1000;
        boardheight = 750;

        cardWidth = 140;
        cardHeight = 200;

        frame = new JFrame("BLACKJACK - EDU's CASIONO");
        frame.setVisible(true);
        frame.setSize(boardWidth, boardheight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Button button = new Button();

        gamePanel = new JPanel(new BorderLayout()) {
            @Override
            public void paintComponent(Graphics grph) {
                super.paintComponent(grph);

                drawIMG(grph, "gametable.jpg", 0, 0);
                drawHiddenIMG(grph, "./cards/BACK.png", 70, 35, button, bot);

                showHandBot(grph, button, bot);
                showHandUser(grph, button, user);

                showJetonsBot(grph, bot);
                showJetonsUser(grph, user);

                drawDealerHand(grph, bot);
                drawUserHand(grph, user);

                WhoIsTheWinner(grph, button, user, bot);

                showTotalBet(grph, user, bot, button);

                gamePanel.repaint();
            
             }
            
        };
        
        gamePanel.setOpaque(true);

        frame.add(gamePanel);

        // Set up buttons and their listeners.
        button.buttonPanel.setBounds(0, boardheight - 50, boardWidth, 50);
        button.hitButton.setFocusable(false);
        button.buttonPanel.add(button.hitButton);
        button.stayButton.setFocusable(false);
        button.buttonPanel.add(button.stayButton);
        button.nextButton.setFocusable(false);
        button.buttonPanel.add(button.nextButton);
        button.saveButton.setFocusable(false);
        button.buttonPanel.add(button.saveButton);
        button.exitButton.setFocusable(false);
        button.buttonPanel.add(button.exitButton);

        frame.add(button.buttonPanel, BorderLayout.SOUTH);
        frame.revalidate();

        button.hitButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                    if(user.handValue < 21) {
                        Card newcard = deck.DeckRemovelast(deck.GetNewCardFromDeck());
                        user.handValue += newcard.getValue();
                        user.acecount += newcard.isAce()? 1:0;
                        user.hand.add(newcard);
                        deck.shuffleDeck();
                        gamePanel.repaint();
                    } else if(user.hasAce()) {
                        user.handValue = user.AceToOne();
                    } else {
                        button.hitButton.setEnabled(false);
                    }
                 
        }
            
        });

        SpinnerModel spinnerModel = new SpinnerNumberModel(0, 0, 1000, 200);
        JSpinner spinner = new JSpinner(spinnerModel);

        Button spinButton = new Button();

        button.stayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                Object value = spinner.getValue();

                if((int)value != 0){

                    button.hitButton.setEnabled(false);
                    button.stayButton.setEnabled(false);

                    bot.decideNextMove(deck);

                    updateJetonsAfterStay((int)value, user, bot);
                }

                gamePanel.repaint();
            }
        });


        spinButton.betButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object value = spinner.getValue();
                updateBets((int)value, user, bot);
                updateJetons((int)value, user, bot);
                gamePanel.repaint();
            }
        });

        spinButton.betButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
            KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Enter pressed");

        spinButton.betButton.getActionMap().put("Enter pressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                spinButton.betButton.doClick();
            }
        });

        spinButton.buttonPanel.setBounds(480,900,40,25);
        spinButton.betButton.setFocusable(false);
        spinButton.buttonPanel.add(spinButton.betButton);
        gamePanel.add(spinButton.betButton, BorderLayout.NORTH);

        spinner.getBounds();

        frame.add(spinner,BorderLayout.NORTH);
        frame.revalidate();

        button.nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if(!button.stayButton.isEnabled() && !button.hitButton.isEnabled()){
                    nextRound(user, bot);
                    total = 0;
                    ((JFrame) SwingUtilities.getWindowAncestor(button.nextButton)).dispose();
                } else if(button.stayButton.isEnabled() && button.hitButton.isEnabled()) {
                    button.nextButton.setEnabled(false);
                }
                button.nextButton.setEnabled(true);
                gamePanel.repaint();
            }
        });

        button.saveButton.addActionListener(event -> {
            if(user.handValue != 0 || bot.handValue != 0){
                JetonManager.Set(user.name, new SavedGameState(user.jetons, bot.jetons));
                JetonManager.Save();
            }
        });
        
        button.exitButton.addActionListener(event -> {
            frame.setVisible(false);
            MenuWindow game = new MenuWindow();
        }); 
        
    }

    /**
     * Draws the cards in the dealer's hand.
     *
     * @param grph The graphics context used for drawing.
     * @param bot  The WeakBot representing the dealer.
     */
    public void drawDealerHand(Graphics grph, WeakBot bot) {
        for(int i=0; i < bot.getHand().size(); i++ ) {
            Card card = bot.getHand().get(i);
            Image cardImg = new ImageIcon(getClass().getResource(card.getImgPath())).getImage();
            grph.drawImage(cardImg, cardWidth + 80 + (cardWidth + 5)*i, 35, cardWidth, cardHeight, null);
        }
    }

    /**
     * Draws the cards in the user's hand.
     *
     * @param grph The graphics context used for drawing.
     * @param user The User representing the player.
     */
    public void drawUserHand(Graphics grph, User user) {
        for(int i=0; i < user.getHand().size(); i++) {
            Card card = user.getHand().get(i);
            Image cardImg = new ImageIcon(getClass().getResource(card.getImgPath())).getImage();
            grph.drawImage(cardImg, cardWidth + 50 + (cardWidth + 5)*i, 340, cardWidth, cardHeight, null);
        }
    }

    /**
     * Draws an image on the game board.
     *
     * @param grph   The graphics context used for drawing.
     * @param source The file path of the image to be drawn.
     * @param x      The x-coordinate for the top-left corner of the image.
     * @param y      The y-coordinate for the top-left corner of the image.
     */
    public void drawIMG(Graphics grph, String source, int x, int y) {
        Image bgImage = new ImageIcon(source).getImage();
        grph.drawImage(bgImage, x, y, boardWidth, boardheight, null);
    }

    /**
     * Draws the hidden or revealed card image.
     *
     * @param grph   The graphics context used for drawing.
     * @param source The file path of the image to be drawn.
     * @param x      The x-coordinate for the top-left corner of the image.
     * @param y      The y-coordinate for the top-left corner of the image.
     * @param button The Button instance containing game buttons.
     * @param bot    The WeakBot representing the dealer.
     */
    public void drawHiddenIMG(Graphics grph, String source, int x, int y, Button button, WeakBot bot) {
        Image hiddenimg = new ImageIcon(getClass().getResource(source)).getImage();
        if(!button.stayButton.isEnabled()) {
            hiddenimg = new ImageIcon(getClass().getResource(bot.hiddenCard.getImgPath())).getImage();     
        }
        grph.drawImage(hiddenimg, x, y, cardWidth, cardHeight, null);
    }

    /**
     * Determinates who is the winner of the round, after hittng stay button.
     * @param grph The graphics context used for drawing.
     * @param button The Button instance containing game buttons.
     * @param user The User representing the user of the game.
     * @param bot The WeakBot representing the dealer.
     */
    public void WhoIsTheWinner(Graphics grph, Button button, User user, WeakBot bot) {
        if(!button.stayButton.isEnabled()) {

            if(user.handValue > 21 && user.hasAce()) {
                user.handValue = user.AceToOne();
            }
            if(bot.handValue > 21 && bot.hasAce()) {
                bot.handValue = bot.AceToOne();
            }
            
            String message = "";

            if(gameOver(user, bot)) {

                message = "GAME OVER";
                button.nextButton.setEnabled(false);

            } else if(!gameOver(user, bot)){

                if(user.handValue > 21 && bot.handValue > 21) {
                    message = "TIE GAME!";
                }else if(user.handValue > 21 && bot.handValue <= 21){
                    message = "YOU LOST!";
                }else if(bot.handValue > 21 && user.handValue <= 21) {
                    message = "YOU WIN!";
                }else if(user.handValue == bot.handValue){
                    message = "TIE GAME!";
                } else if(user.hasBlackjack()) {
                    message = "BLACKJACK!";
                } else if(bot.hasBlackjack()) {
                    message = "BOT HAS BLACKJACK!";
                } else if(user.handValue > bot.handValue) {
                    message = "YOU WIN!";
                } else if(user.handValue < bot.handValue) {
                    message = "YOU LOST!";
                }

            }
            
            grph.setFont(new Font("Times New Roman", Font.BOLD, 32));
            grph.setColor(Color.YELLOW);
            grph. drawString(message, 400, 300);


        }
    }

    /**
     * Draws the hand of the bot
     * @param grph The graphics context used for drawing.
     * @param button The Button instance containing game buttons.
     * @param bot The WeakBot representing the dealer.
     */
    public void showHandBot(Graphics grph, Button button, WeakBot bot) {

        String bothand = valueOf(bot.faceduupcard.getValue());

        if(grph != null){
            if(button.stayButton.isEnabled()){
                grph.setFont(new Font("Times New Roman", Font.ITALIC, 30));
                grph.setColor(Color.WHITE);
                grph. drawString("Opponent's hand  |", 580, 600);
                if(bot.faceduupcard.isAce()/*bothand.equals("11") || bothand.equals("1")*/){
                    grph. drawString("A", 650, 635);
                }else{
                    grph. drawString(bothand + " + ? ", 640, 635);
                }
            }else if(!button.stayButton.isEnabled()){
                String bothandvalue = valueOf(bot.handValue);

                grph.setFont(new Font("Times New Roman", Font.ITALIC, 30));
                grph.setColor(Color.WHITE);
                grph. drawString("Opponent's hand  |", 580, 600);

                grph. drawString(bothandvalue, 670, 635);
            }
        }
    }

    /**
     * Draws the hand of the user.
     * @param grph The graphics context used for drawing.
     * @param button The Button instance containing game buttons.
     * @param user The User representing the user of the game.
     */
    public void showHandUser(Graphics grph, Button button, User user) {

        String userhand = valueOf(user.handValue);

        if(grph != null){
            grph.setFont(new Font("Times New Roman", Font.ITALIC, 30));
            grph.setColor(Color.WHITE);
            grph. drawString("Your hand", 830, 600);
            grph. drawString(userhand, 880, 635);
        }
    }

    /**
     * Draws the jetons of the bot.
     * @param grph The graphics context used for drawing.
     * @param bot The WeakBot representing the dealer.
     */
    public void showJetonsBot(Graphics grph, WeakBot bot) {
        
        String botjetons = valueOf(bot.getJetons());
        if(grph != null) {
            grph.setFont(new Font("Times New Roman", Font.ITALIC, 30));
            grph.setColor(Color.white);
            grph.drawString("Opponent's jetons  |", 20, 600);
            grph.drawString(botjetons, 80, 635);
        }
    }

    /**
     * Draws the jetons of the user.
     * @param grph The graphics context used for drawing.
     * @param user The User representing the user of the game.
     */
    public void showJetonsUser(Graphics grph, User user) {
        
        String userjetons = valueOf(user.getJetons());
        if(grph != null) {
            grph.setFont(new Font("Times New Roman", Font.ITALIC, 30));
            grph.setColor(Color.white);
            grph.drawString("Your jetons", 280, 600);
            grph.drawString(userjetons, 310, 635);
        }
    }

    /**
     *  Draws the total bet of the actual round.
     * @param grph The graphics context used for drawing.
     * @param user The User representing the user of the game.
     * @param bot The WeakBot representing the dealer.
     * @param button The Button instance containing game buttons.
     */
    public void showTotalBet(Graphics grph, User user, WeakBot bot, Button button) {
        
        total = 0;
        total = user.bet + bot.bet;
        String value = valueOf(total);

        grph.setFont(new Font("Times New Roman", Font.ITALIC, 30));
        grph.setColor(Color.yellow);
        grph.drawString("TOTAL", 455, 600);
        if(button.stayButton.isEnabled()){
            grph.drawString(value, 480, 635);
        }
    }

    /**
     * Returns the string representation of the specified integer.
     *
     * @param x The integer to be converted to a string.
     * @return The string representation of the integer.
     */
    public String valueOf(int x) {
        return String.valueOf(x);
    }

    /**
     * Updates the jetons (chips) for both the user and the bot based on the specified value.
     *
     * @param value The value to be subtracted from both user and bot jetons.
     * @param user  The User instance representing the player.
     * @param bot   The WeakBot instance representing the dealer.
     */
    public void updateJetons(int value, User user, WeakBot bot) {
        if((user.jetons - value) >= 0  && (bot.jetons - value) >= 0) {
            user.jetons -= value;
            bot.jetons -= value;
        }
        
    }

    /**
     * Updates the bets for both the user and the bot based on the specified value.
     *
     * @param value The value to be added to both user and bot bets.
     * @param user  The User instance representing the player.
     * @param bot   The WeakBot instance representing the dealer.
     */
    public void updateBets(int value, User user, WeakBot bot) {
            
        if(user.jetons - value >= 0 && bot.jetons - value >= 0) {
            user.bet += value;
            bot.bet += value;
            System.out.println("User's bet:" + user.bet);
            System.out.println("Opponent's bet:" + bot.bet);
        }

        
    }

    /**
     * Updates jetons for both the user and the bot after the stay action.
     *
     * @param value The value used in the bet.
     * @param user  The User instance representing the player.
     * @param bot   The WeakBot instance representing the dealer.
     */
    public void updateJetonsAfterStay(int value, User user, WeakBot bot) {
        if(user.handValue > 21 && bot.handValue > 21 || user.handValue == bot.handValue) {
                user.jetons += total/2;
                bot.jetons += total/2;
            }else if((user.handValue > 21 && bot.handValue <= 21) || bot.hasBlackjack() || user.handValue < bot.handValue && bot.handValue <= 21){
                bot.jetons += total;
            }else if((bot.handValue > 21 && user.handValue <= 21) || user.hasBlackjack() || user.handValue > bot.handValue && user.handValue <= 21) {
                user.jetons += total;
            }
    }

    /**
     * Starts the next round of the game.
     *
     * @param user The User instance representing the player.
     * @param bot  The WeakBot instance representing the dealer.
     */
    public void nextRound(User user, WeakBot bot){
        Game nextround = new Game();
        nextround.nextRound(user, bot);
    }

    /**
     * Checks if the game is over based on the jetons of the user and the bot.
     *
     * @param user The User instance representing the player.
     * @param bot  The WeakBot instance representing the dealer.
     * @return True if the game is over, otherwise false.
     */
    public boolean gameOver(User user, WeakBot bot) {
        return (user.jetons == 0 || bot.jetons == 0);
    }


}
