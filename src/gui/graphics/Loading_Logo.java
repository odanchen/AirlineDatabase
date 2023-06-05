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
 * @author Aidan Baker
 */
public class Loading_Logo extends JComponent {
    /**
     * The imported logo of the company.
     */
    private final BufferedImage image;
    /**
     * The constructor of the class, initializes the object.
     * @author Aidan Baker
     */
    public Loading_Logo() {
        setPreferredSize(new Dimension(600, 400));
        image = DataReader.readImage("lLogo");
    }

    /**
     * The paint method, draws the logo.
     * @param g the gui.graphics object to draw with
     * @author Aidan Baker
     */
    @Override
    public void paint(Graphics g) {
        g.setColor(CustomButton.BUTTON_BLUE);
        g.drawImage(image, (getWidth()/2)-300, 0, null);
    }
}