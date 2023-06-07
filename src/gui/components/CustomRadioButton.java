/*
Author: Aidan Baker
Time Spent: 25 minutes
Date: 29 May 2023
Version: 1
 */
package gui.components;

import javax.swing.*;
import java.awt.*;

/**
 * Represents a custom radio button.
 * @see JRadioButton
 * @author Aidan Baker
 */
public class CustomRadioButton extends JRadioButton {
    /**
     * The color for selected buttons.
     */
    public static final Color SELECTED_COLOR = new Color(50, 102, 203);

    /**
     * Constructs a CustomRadioButton object.
     * @citations: <a href="https://docs.oracle.com/javase/8/docs/api/javax/swing/JRadioButton.html">JRadioButton Documentation</a>
     *      setAlignmentX() – used to align the button on the center of the panel horizontally.
     *      setIcon() – used to remove the icon from the button.
     * @param message The message to be displayed on the button.
     * @param isSelected Whether the button is selected.
     */
    public CustomRadioButton(String message, boolean isSelected) {
        super(message);
        setBackground(new Color(0, 0, 0, 0));
        setForeground(Color.WHITE);
        setFont(new Font("Arial", Font.PLAIN, 32));
        setSelected(isSelected);
        setAlignmentX(Component.CENTER_ALIGNMENT);
        setActionCommand(message);
        setIcon(new CustomIcon());
        repaint();
    }

    /**
     * Overriden paint method to style the button.
     *
     * @param g  the <code>Graphics</code> context in which to paint
     * @author Aidan Baker
     */
    @Override
    public void paint(Graphics g) {
        int w = getWidth(), h = getHeight();
        if (isSelected()) g.setColor(SELECTED_COLOR);
        else g.setColor(CustomButton.BUTTON_BLUE);
        g.fillRoundRect((int) (w * 0.02), (int) (h * 0.07), (int) (w * 0.98), (int) (h * 0.93), 7, 7);
        super.paint(g);
    }
}
