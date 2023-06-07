/*
Author: Aidan Baker
time spent: 40 minutes
Date: 16 May 2023
Changes: Redesigned the logo to be more appealing
        Author: Oleksandr Danchenko & Aidan Baker
        Date: 25 May 2023
        Time: 85 minutes
version #2
 */
package gui.graphics;

import resource.DataReader;
import gui.components.CustomButton;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * The Loading_Logo class, creates a logo for the application.
 *
 * @author Aidan Baker
 */
public class LoadingLogo extends JComponent {
    /**
     * The imported logo of the company.
     *
     * Citation: https://docs.oracle.com/javase/8/docs/api/java/awt/image/BufferedImage.html
     *      The BufferedImage class is used to access and use the image in code.
     */
    private final BufferedImage image;

    /**
     * The constructor of the class, initializes the object.
     *
     * Citation: https://docs.oracle.com/javase/8/docs/api/javax/swing/JComponent.html#setPreferredSize-java.awt.Dimension-
     * Citation: https://docs.oracle.com/javase/8/docs/api/java/awt/Dimension.html
     *      The setPreferredSize() method is used to specify the preferred size of the component.
     *      Here, it is used to specify the approximate size of the logo for its container's layout manager.
     *      The Dimension class is used as a parameter and contains the preferred width and height of the component respectively.
     * @author Aidan Baker
     */
    public LoadingLogo() {
        setPreferredSize(new Dimension(600, 400));
        image = DataReader.readImage("lLogo");
    }

    /**
     * The paint method, draws the logo.
     *
     * Citation: https://docs.oracle.com/javase/8/docs/api/java/awt/Graphics.html#drawImage-java.awt.Image-int-int-java.awt.Color-java.awt.image.ImageObserver-
     *      The drawImage() method is used to draw the imported image on the component.
     * @param g the gui.graphics object to draw with
     * @author Aidan Baker
     */
    @Override
    public void paint(Graphics g) {
        g.setColor(CustomButton.BUTTON_BLUE);
        g.drawImage(image, (getWidth() / 2) - 300, 0, null);
    }
}