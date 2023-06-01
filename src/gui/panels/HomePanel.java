/*
Author: Aidan Baker
time spent: 25 minutes
Date: 17 May 2023
version #3
Changes: redesigned the panel. Added images of the destinations to it, added more greeting messages.
    Date: 25 May 2023
    time spent: 40 minutes
    Author: Oleksandr Danchenko
Changes: implemented the getTitle() and makeVisible() methods added to the ScreenPanel in the process of cleaning up the code.
    time spent: 5 minutes
    Date 1 June 2023
    Author: Oleksandr Danchenko
 */
package gui.panels;

import gui.ApplicationFrame;
import gui.components.CustomButton;
import gui.components.CustomPanel;
import gui.graphics.HomeImage;
import logic.data_record.Route;

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
            "Welcome! Explore and book flights effortlessly with our app.",
            "Hello! Let our app be your travel companion for seamless flight bookings.",
            "Greetings! Discover new horizons with our user-friendly flight booking app.",
            "Welcome aboard! Book flights with ease using our app.",
            "Hello, traveler! Start your journey by booking flights on our app.",
            "Welcome! Plan your next adventure with our convenient flight booking app.",
            "Greetings! Fly high with our intuitive flight booking app.",
            "Hello and happy travels! Begin your trip by booking flights on our app.",
            "Welcome to a world of possibilities! Explore destinations with our flight booking app.",
            "Hello! Unlock travel opportunities with our efficient flight booking app."
    };

    /**
     * The constructor for the HomePanel class.
     *
     * @param applicationFrame the frame that the panel is displayed on.
     * @author Aidan Baker, Oleksandr Danchenko
     */
    public HomePanel(ApplicationFrame applicationFrame) {
        super(applicationFrame, BoxLayout.Y_AXIS);
        //screen message
        CustomPanel messagePanel = new CustomPanel();
        screenMessage.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
        screenMessage.setEditable(false);
        screenMessage.setBackground(BACKGROUND_WHITE);
        screenMessage.setForeground(CustomButton.BUTTON_BLUE);
        screenMessage.setHorizontalAlignment(JTextField.CENTER);
        messagePanel.add(screenMessage);
        centerPanel.add(messagePanel);

        CustomPanel imagePanel = new CustomPanel(new GridLayout(1, 3));
        imagePanel.add(new HomeImage(Route.TORONTO));
        imagePanel.add(new HomeImage(Route.VANCOUVER));
        imagePanel.add(new HomeImage(Route.OTTAWA));
        centerPanel.add(imagePanel);
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
     * Makes the screen visible.
     *
     * @author Oleksandr Danchenko
     */
    @Override
    public void makeVisible() {
        super.makeVisible();
        updateScreenMessage();
        applicationFrame.setBackButtonVisibility(false);
    }

    /**
     * Returns the title for the screen.
     *
     * @return "Home"
     * @author Oleksandr Danchenko
     */
    @Override
    public String getTitle() {
        return "Home";
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
