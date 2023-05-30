/*
Author: Aidan Baker
Time Spent: 20 minutes
Date: 29 May 2023
citations: Swing documentation
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
     * @param message The message to be displayed on the button.
     * @param isSelected Whether the button is selected.
     */
    public CustomRadioButton(String message, boolean isSelected) {
        super(message);
        setBackground(new Color(0, 0, 0, 0));
        setBorderPainted(false);
        setForeground(Color.WHITE);
        setFont(new Font("Arial", Font.PLAIN, 32));
        setSelected(isSelected);
        setAlignmentX(Component.CENTER_ALIGNMENT);
        setActionCommand(message);
        setIcon(new CustomIcon());
        setPressedIcon(new CustomIcon());
        setSelectedIcon(new CustomIcon());
        repaint();
    }

    /**
     * Overriden paint method to style the button.
     * @param g  the <code>Graphics</code> context in which to paint
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
