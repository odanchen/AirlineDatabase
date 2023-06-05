/*
Author: Oleksandr Danchenko
time spent: 25 minutes
Date: 17 May 2023
version #1
 */

package gui.graphics;

import gui.components.CustomButton;

import javax.swing.*;
import java.awt.*;

/**
 * The LoadingBar class represents a custom Swing component that displays a loading bar.
 * It extends the JComponent class to provide custom painting functionality.
 *
 * @author Oleksandr Dacnehnko
 */
public class LoadingBar extends JComponent {
    /**
     * A field that stores the current size of the bar.
     * It is created to be modifiable by other classes through the update method and be accessible inside the paint() method.
     */
    private int pixelCnt = 0;

    /**
     * Creates a new instance of LoadingBar.
     *
     * @author Oleksandr Dacnehnko
     */
    public LoadingBar() {
        setBackground(new Color(0, 0, 0, 0));
        setPreferredSize(new Dimension(334, 40));
    }

    /**
     * Updates the loading bar with a new pixel count and triggers a repaint of the component.
     *
     * @param pixelCnt The new pixel count to update the loading bar.
     * @author Oleksandr Dacnehnko
     */
    public void update(int pixelCnt) {
        this.pixelCnt = pixelCnt;
        repaint();
    }

    /**
     * Overrides the paint method to draw the loading bar on the component.
     *
     * @param g The Graphics context used for painting.
     * @author Oleksandr Dacnehnko
     */
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawRect((getWidth()/2)-167, getHeight() / 2, 334, 40);
        g.setColor(CustomButton.BUTTON_BLUE);
        g.fillRect((getWidth()/2)-167, getHeight() / 2, pixelCnt, 40);
    }
}

