/*
Author: Aidan Baker
Time Spent: 10 minutes
Date: 29 May 2023
Citations: https://docs.oracle.com/javase/8/docs/api/javax/swing/Icon.html
        A class that implements an Icon for the CustomRadioButton class to get rid of the default selection icon on the JRadioButton
 */

package gui.components;

import javax.swing.*;
import java.awt.*;

/**
 * A CustomIcon class, represents a custom icon for radio buttons
 *
 * @see Icon
 * @author Aidan Baker
 */
public class CustomIcon implements Icon {
    /**
     * Overridden empty paintIcon method to get rid of the icon
     *
     * @param c  a {@code Component} to get properties useful for painting
     * @param g  the graphics context
     * @param x  the X coordinate of the icon's top-left corner
     * @param y  the Y coordinate of the icon's top-left corner
     * @author Aidan Baker
     */
    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {

    }

    /**
     * Overridden getIconWidth method because it is required by interface
     *
     * @return 0
     * @author Aidan Baker
     */
    @Override
    public int getIconWidth() {
        return 0;
    }

    /**
     * Overridden getIconHeight method because it is required by interface
     *
     * @return 0
     * @author Aidan Baker
     */
    @Override
    public int getIconHeight() {
        return 0;
    }
}
