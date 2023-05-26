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

import database.interaction.DataReader;
import gui.components.CustomButton;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * The Logo class, creates a logo for the application.
 * @author Aidan Baker
 */
public class Logo extends JComponent {
    /**
     * The imported logo of the company.
     */
    private final BufferedImage image;
    /**
     * The constructor of the class, initializes the object.
     * @author Aidan Baker
     */
    public Logo() {
        setPreferredSize(new Dimension(600, 120));
        image = DataReader.readImage("logo");
    }

    /**
     * The paint method, draws the logo.
     * @param g the gui.graphics object to draw with
     * @author Aidan Baker
     */
    @Override
    public void paint(Graphics g) {
        g.setColor(CustomButton.BUTTON_BLUE);
        g.fillRoundRect(305, 20, 85, 100, 45, 45);
        //g.fillOval(305, 20, 85, 100);
        g.drawImage(image, 0, 0, null);
    }
}

