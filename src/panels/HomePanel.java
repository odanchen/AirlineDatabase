/*
Author: Aidan Baker
time spent: 5 minutes
version #1
 */

package panels;

import frame.ApplicationFrame;
import graphics.home_background.HomeBackground;

import javax.swing.*;
import java.awt.*;

public class HomePanel extends CustomPanel {
    private JTextField screenMessage;
    private static final String[] MESSAGES = {
            "Welcome to Fly-Away Airlines!",
            "Welcome Back!",
            "Thank you for your service!",
            "Welcome to the Flight Reservation System!",
            "The cookie monster is greeting you!",
    };
    private HomeBackground homeBackground;

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

    public String setScreenMessage() {
        return MESSAGES[(int) (Math.random() * MESSAGES.length)];
    }
}
