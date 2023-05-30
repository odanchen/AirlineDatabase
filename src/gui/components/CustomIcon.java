/*
Author: Aidan Baker
Time Spent: 2 minutes
Date: 29 May 2023
Citations: Swing documentation
 */

package gui.components;

import javax.swing.*;
import java.awt.*;

/**
 * A CustomIcon class, represents a custom icon for radio buttons
 * @see Icon
 * @author Aidan Baker
 */
public class CustomIcon implements Icon {
    /**
     * Overridden empty paintIcon method to get rid of the icon
     * @param c  a {@code Component} to get properties useful for painting
     * @param g  the graphics context
     * @param x  the X coordinate of the icon's top-left corner
     * @param y  the Y coordinate of the icon's top-left corner
     */
    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {

    }

    /**
     * Overridden getIconWidth method because it is required by interface
     * @return 0
     */
    @Override
    public int getIconWidth() {
        return 0;
    }

    /**
     * Overridden getIconHeight method because it is required by interface
     * @return 0
     */
    @Override
    public int getIconHeight() {
        return 0;
    }
}
