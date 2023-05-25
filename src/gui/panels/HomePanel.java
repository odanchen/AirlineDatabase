/*
Author: Aidan Baker
time spent: 10 minutes
version #1
 */
package gui.panels;

import gui.ApplicationFrame;
import gui.components.CustomButton;
import gui.components.CustomPanel;
import gui.graphics.HomeImage;
import gui.graphics.home_background.HomeBackground;
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
            "Welcome to Fly-Away Airlines!",
            "Welcome Back!",
            "Thank you for your service!",
            "Welcome to the Flight Reservation System!",
            "The cookie monster is greeting you!",
    };

    /**
     * The constructor for the HomePanel class.
     *
     * @param applicationFrame the frame that the panel is displayed on.
     * @author Aidan Baker
     */
    public HomePanel(ApplicationFrame applicationFrame) {
        super(applicationFrame, BoxLayout.Y_AXIS);

        System.out.println(centerPanel.getLayout());
        //screen message
        CustomPanel messagePanel = new CustomPanel();
        screenMessage.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 23));
        screenMessage.setEditable(false);
        screenMessage.setBackground(backgroundWhite);
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
     * Updates the message displayed on the panel.
     *
     * @author Aidan Baker.
     */
    public void updateScreenMessage() {
        screenMessage.setText(getRandomizesMessage());
    }
}
