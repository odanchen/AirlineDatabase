/*
Author: Oleksandr Danchenko
time spent: 15 minutes
Date: 25 May 2023
version #1
 */

package gui.graphics;

import resource.DataReader;
import gui.components.CustomPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * A class to show an image of a town on the home screen.
 *
 * @author Oleksadndr Danchenko
 */
public class HomeImage extends JComponent {
    /**
     * The image to be displayed.
     */
    private final BufferedImage image;

    /**
     * A constructor of the class.
     *
     * @param filename the city to be displayed.
     * @author Oleksandr Danchenko
     */
    public HomeImage(String filename) {
        image = DataReader.readImage(filename);
        setPreferredSize(new Dimension(460, 450));
    }

    /**
     * A paint method, draws the image.
     *
     * @param g  Graphics class instance.
     * @author Oleksandr Danchenko
     */
    @Override
    public void paint(Graphics g) {
        g.setColor(CustomPanel.BACKGROUND_WHITE);
        g.drawImage(image, 0, 0, null);
    }
}
