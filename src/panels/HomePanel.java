/*
Author: Aidan Baker
time spent: 10 minutes
version #1
 */
package panels;

import frame.ApplicationFrame;
import graphics.home_background.HomeBackground;

import javax.swing.*;
import java.awt.*;

/**
 * The HomePanel class, displays the home screen of the program.
 * @see panels.CustomPanel
 * @author Aidan Baker
 */
public class HomePanel extends CustomPanel {
    /**
     * The text field that displays the screen message.
     */
    private JTextField screenMessage;

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
     * @param applicationFrame the frame that the panel is displayed on
     * @author Aidan Baker
     */
    public HomePanel(ApplicationFrame applicationFrame) {
        super(applicationFrame);
        centerPanel.setLayout(new BorderLayout());

        //screen message
        screenMessage = new JTextField(setScreenMessage());
        screenMessage.setFont(new Font("Arial", Font.BOLD, 50));
        screenMessage.setEditable(false);
        screenMessage.setBackground(new Color(238, 238, 238));
        screenMessage.setHorizontalAlignment(JTextField.CENTER);
        centerPanel.add(screenMessage, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);
    }

    /**
     * Sets the screen message to a random message from the MESSAGES array.
     * @return the random message that was selected from the MESSAGES array
     * @author Aidan Baker
     */
    public String setScreenMessage() {
        return MESSAGES[(int) (Math.random() * MESSAGES.length)];
    }
}
