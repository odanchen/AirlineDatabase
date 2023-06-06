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
     *
     * Citation: https://docs.oracle.com/javase/8/docs/api/java/awt/image/BufferedImage.html
     *      The BufferedImage class is used to access and use the image in code.
     */
    private final BufferedImage image;

    /**
     * A constructor of the class.
     *
     * Citation: https://docs.oracle.com/javase/8/docs/api/javax/swing/JComponent.html#setPreferredSize-java.awt.Dimension-
     * Citation: https://docs.oracle.com/javase/8/docs/api/java/awt/Dimension.html
     *      The setPreferredSize() method is used to specify the preferred size of the component.
     *      Here, it is used to specify the approximate size of the image component to the layout manager of the container that stores the object.
     *      The Dimension class is used as a parameter and contains the preferred width and height of the component respectively.
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
     * Citation: https://docs.oracle.com/javase/8/docs/api/java/awt/Graphics.html#drawImage-java.awt.Image-int-int-java.awt.Color-java.awt.image.ImageObserver-
     *      The drawImage() method is used to draw the imported image on the component.
     * @param g  Graphics class instance.
     * @author Oleksandr Danchenko
     */
    @Override
    public void paint(Graphics g) {
        g.setColor(CustomPanel.BACKGROUND_WHITE);
        g.drawImage(image, 0, 0, null);
    }
}
