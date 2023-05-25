/*
Author: Aidan Baker
time spent: 10 minutes
Date: 17 May 2023
version #1
 */
package gui.panels;

import gui.ApplicationFrame;
import gui.graphics.home_background.HomeBackground;

import javax.swing.*;
import java.awt.*;

/**
 * The HomePanel class, displays the home screen of the program.
 *
 * @author Aidan Baker
 * @see ScreenPanel
 */
public class HomePanel extends ScreenPanel {
    /**
     * The text field that displays the screen message.
     */
    private final JTextField screenMessage = new JTextField();

    /**
     * The array of messages that can be displayed on the screen.
     */
    private static final String[] MESSAGES = {
            "Welcome to Fly-Away Airlines!",
            "Welcome Back!",
            "Thank you for your service!",
            "Welcome to the Flight Reservation System!",
            "The cookie monster is greeting you!",
    };

    /**
     * The background image for the home screen.
     */
    private HomeBackground homeBackground;

    /**
     * The constructor for the HomePanel class.
     *
     * @param applicationFrame the frame that the panel is displayed on.
     * @author Aidan Baker
     */
    public HomePanel(ApplicationFrame applicationFrame) {
        super(applicationFrame, new BorderLayout());

        //screen message
        screenMessage.setFont(new Font("Arial", Font.BOLD, 50));
        screenMessage.setEditable(false);
        screenMessage.setBackground(new Color(238, 238, 238));
        screenMessage.setHorizontalAlignment(JTextField.CENTER);
        updateScreenMessage();

        centerPanel.add(screenMessage, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);
    }

    /**
     * Gets a random message from the MESSAGES array.
     *
     * @return the random message that was selected from the MESSAGES array.
     * @author Aidan Baker.
     */
    public String getRandomizesMessage() {
        return MESSAGES[(int) (Math.random() * MESSAGES.length)];
    }

    /**
     * Updates the message displayed on the panel.
     *
     * @author Aidan Baker.
     */
    public void updateScreenMessage() {
        screenMessage.setText(getRandomizesMessage());
    }
}
