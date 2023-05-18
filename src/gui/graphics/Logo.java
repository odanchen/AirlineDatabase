/*
Author: Aidan Baker
time spent: 40 minutes
version #1
 */
package gui.graphics;

import javax.swing.*;
import java.awt.*;

/**
 * The Logo class, creates a logo for the application.
 * @author Aidan Baker
 */
public class Logo extends JComponent {
    /**
     * The constructor of the class, initializes the object.
     * @author Aidan Baker
     */
    public Logo() {
        setPreferredSize(new Dimension(150, 150));
    }
    /**
     * @param point the point that needs to be moved to fit the scale
     * @return the scaled point
     * @author Aidan Baker
     */
    private int scale(double point) {
        int minSize = Math.min(getWidth(), getHeight());
        return (int) (point * minSize / 100);
    }

    /**
     * The paint method, draws the logo.
     * @param g the gui.graphics object to draw with
     * @author Aidan Baker
     */
    @Override
    public void paint(Graphics g) {
        //outer circle
        g.setColor(Color.blue);
        g.fillOval(10, 10, scale(75), scale(75));

        //inner circle
        g.setColor(Color.lightGray);
        g.fillOval(
                scale(50) - (scale(75) / 2) + 10,
                scale(50) - (scale(75) / 2) + 10,
                scale(50),
                scale(50)
        );

        //plane
        int[] planeCordsX = {8, 21, 185, 239, 266, 250, 206, 220, 211, 161, 112, 81, 88, 78, 52, 10, 19, 72, 89, 140};
        int[] planeCordsY = {89, 79, 93, 45, 33, 65, 113, 278, 291, 159, 205, 226, 279, 289, 249, 221, 212, 218, 188, 140};

        for (int i = 0; i < planeCordsX.length; i++) {
            planeCordsX[i] = (int) (scale(planeCordsX[i]) / 3.5 + 7);
            planeCordsY[i] = (int) (scale(planeCordsY[i]) / 3.5 + 4);
        }

        g.setColor(Color.black);
        g.fillPolygon(planeCordsX, planeCordsY, planeCordsX.length);
    }
}

