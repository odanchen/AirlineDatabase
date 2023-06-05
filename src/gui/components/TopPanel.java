/*
Author: Oleksandr Danchenko
time spent: 30 minutes
Date: 31 May 2023
version #2
Changes: added the cloud animation.
    time spent: 30 minutes
    Date 1 June 2023
    author Oleksandr Danchenko
Changes: fixed the error with multiple threads accessing the list of objects drawn on the top panel
        by adding the updateAnimation() method that is used in the CloudAnimation class to copy the list of clouds.
    Time spent: 7 minutes
    Date: 5 June 2023
    Author Oleksandr Danchenko
*/

package gui.components;

import gui.ApplicationFrame;
import gui.graphics.Cloud;
import gui.graphics.CloudAnimation;
import gui.graphics.Logo;

import javax.swing.*;
import java.awt.*;
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
    private List<Cloud> clouds;
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
        setBackground(new Color(0, 0, 0, 0));
        backButton = new CustomButton("<", "back", applicationFrame, 20);
        backButton.setVisible(false);
        backButton.setPreferredSize(new Dimension(75, 50));
        placeholder.setPreferredSize(new Dimension(76, 50));
        add(placeholder);
        add(backButton);
        add(new Logo());
        title.setBackground(CustomPanel.BACKGROUND_WHITE);
        title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 60));
        title.setForeground(CustomButton.BUTTON_BLUE);
        title.setEditable(false);
        title.setHorizontalAlignment(JTextField.CENTER);
        title.setBackground(new Color(0, 0, 0, 0));
        title.setBorder(null);
        add(title);
        CloudAnimation animation = new CloudAnimation(this);
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
     * Updates the drawing of the animation.
     *
     * @param clouds the current list of objects in the animation.
     * @author Oleksandr Danchenko
     */
    public void updateAnimation(List<Cloud> clouds) {
        this.clouds = clouds;
        repaint();
    }

    /**
     * Paints the content on the panel.
     * Citation: https://docs.oracle.com/javase/tutorial/essential/concurrency/syncmeth.html
     *      The method is made synchronized to not produce an error when the Cloud object
     *      is modified and accessed by different threads simultaneously
     *
     * @param g the instance of the Graphics class.
     * @author Oleksandr Danchenko
     */
    @Override
    public synchronized void paint(Graphics g) {
        g.setColor(CustomPanel.BACKGROUND_WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        for (Cloud cloud : clouds) {
            cloud.paint(g);
        }
        super.paint(g);
    }
}
