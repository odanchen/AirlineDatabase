/*
Author: Oleksandr Danchenko
time spent: 30 minutes
Date: 31 May 2023
version #2
Changes: added the cloud animation.
    time spent: 30 minutes
    Date 1 June 2023
    author Oleksandr Danchenko
*/

package gui.components;

import gui.ApplicationFrame;
import gui.graphics.Cloud;
import gui.graphics.CloudAnimation;
import gui.graphics.Logo;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The TopPanel class represents a custom panel that is displayed at the top of the application frame.
 * It contains a back button, a logo, a title, and animated clouds.
 *
 * @author Oleksandr Danchenko
 */
public class TopPanel extends CustomPanel {
    /**
     * The back button.
     */
    private final CustomButton backButton;
    /**
     * The list of clouds.
     */
    private final List<Cloud> clouds = new ArrayList<>();
    /**
     * A placeholder label to take space when the back button is invisible.
     */
    private final JLabel placeholder = new JLabel("");
    /**
     * The text field for the screen title.
     */
    private final JTextField title = new JTextField();

    /**
     * Constructs a TopPanel object.
     *
     * @param applicationFrame the application frame to which this panel belongs
     * @author Oleksandr Danchenko
     */
    public TopPanel(ApplicationFrame applicationFrame) {
        super(BoxLayout.X_AXIS);
        setPreferredSize(new Dimension(1400, 120));
        backButton = new CustomButton("<", "back", applicationFrame, 20);
        backButton.setVisible(false);
        backButton.setPreferredSize(new Dimension(75, 50));
        placeholder.setPreferredSize(new Dimension(76, 50));

        add(placeholder);
        add(backButton);
        add(new Logo());
        title.setBackground(CustomPanel.BACKGROUND_WHITE);
        title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 55));
        title.setForeground(CustomButton.BUTTON_BLUE);
        title.setEditable(false);
        title.setHorizontalAlignment(JTextField.CENTER);
        add(title);
        CloudAnimation animation = new CloudAnimation(this, clouds);
        animation.start();
    }

    /**
     * Sets the title of the screen to be a specified text.
     *
     * @param text the text in the title
     * @author Oleksandr Danchenko
     */
    public void setTitle(String text) {
        title.setText(text);
        title.repaint();
    }

    /**
     * Makes the back button at the top left visible or invisible.
     *
     * @param visible whether the back button should be visible or not
     * @author Oleksandr Danchenko
     */
    public void setBackButtonVisibility(boolean visible) {
        backButton.setVisible(visible);
        placeholder.setVisible(!visible);
    }

    /**
     * Paints the content on the panel.
     *
     * @param g the instance of the Graphics class.
     * @author Oleksandr Danchenko
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (Cloud cloud : clouds) {
            cloud.paint(g);
        }
    }
}
