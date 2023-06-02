/*
Author: Aidan Baker
Time Spent: 40 minutes
Date: 31 May 2023
version# 1
*/

package gui.graphics;

import java.awt.*;

/**
 * The Cloud class represents a cloud object in a graphical user interface.
 * Clouds have various types and positions and can move across the screen.
 *
 * @author Aidan Baker
 */
public class Cloud {
    /**
     * The x-position of the cloud.
     */
    private int xPos;

    /**
     * The y-position of the cloud.
     */
    private final int yPos;

    /**
     * The type of the cloud.
     */
    private final int type;

    /**
     * The speed at which the cloud moves.
     */
    private final int speed;

    /**
     * The color of the cloud.
     */
    private final Color WHITE = Color.WHITE;

    /**
     * Constructs a Cloud object with the specified y-position.
     *
     * @param yPos the y-position of the cloud
     * @author Aidan Baker
     */
    public Cloud(int yPos) {
        this.yPos = yPos;
        if ((int) (Math.random() * 2) == 1) {
            speed = (int) (Math.random() * 4) + 2;
            xPos = -200;
        } else {
            speed = ((int) -(Math.random() * 4) + 2);
            xPos = 1600;
        }
        type = (int) (Math.random() * 3);
    }

    /**
     * Draws the cloud of type 1.
     *
     * @param g the Graphics object used for drawing
     * @author Aidan Baker
     */
    private void drawType1(Graphics g) {
        g.setColor(WHITE);

        g.fillOval(xPos + 20, yPos, 65, 35);
        g.fillOval(xPos, yPos + 22, 75, 45);
        g.fillOval(xPos + 45, yPos + 25, 70, 50);
    }

    /**
     * Draws the cloud of type 2.
     *
     * @param g the Graphics object used for drawing
     * @author Aidan Baker
     */
    private void drawType2(Graphics g) {
        g.setColor(WHITE);

        g.fillOval(xPos, yPos + 10, 50, 50);
        g.fillOval(xPos + 30, yPos, 50, 50);
        g.fillOval(xPos + 60, yPos + 10, 50, 50);

        g.fillOval(xPos + 20, yPos + 30, 50, 50);
        g.fillOval(xPos + 50, yPos + 30, 50, 50);
    }

    /**
     * Draws the cloud of type 3.
     *
     * @param g the Graphics object used for drawing
     * @author Aidan Baker
     */
    private void drawType3(Graphics g) {
        g.setColor(WHITE);

        g.fillOval(xPos, yPos + 10, 40, 40);
        g.fillOval(xPos + 30, yPos, 60, 60);
        g.fillOval(xPos + 70, yPos + 10, 40, 40);

        g.fillOval(xPos + 10, yPos + 30, 30, 30);
        g.fillOval(xPos + 60, yPos + 30, 30, 30);
        g.fillOval(xPos + 30, yPos + 40, 40, 40);
    }

    /**
     * A helper method to scale numbers to fit the screen properly.
     *
     * @param n The number to scale to fit the screen properly
     * @return The scaled number
     */
    private int scale(int n) {
        return n/10;
    }

    /**
     * Draws a plane on the screen.
     * @param g the Graphics object used for drawing
     * @author Aidan Baker
     */
    private void drawPlane(Graphics g) {
        Boolean isFacingRight = speed > 0;

        g.setColor(new Color(48, 77, 173));
        g.fillOval(xPos+scale(16), yPos+scale(368), scale(254), scale(285));
    }

    /**
     * Moves the cloud horizontally based on its speed.
     *
     * @author Aidan Baker
     */
    public void move() {
        xPos += speed;
    }

    /**
     * Checks if the cloud has moved completely off the screen.
     *
     * @return true if the cloud is no longer visible on the screen, false otherwise
     * @author Aidan Baker
     */
    public boolean isOverScreen() {
        if (speed > 0) return xPos > 1400;
        else return xPos < -200;
    }

    /**
     * Paints the cloud on the screen based on its type.
     *
     * @param g the Graphics object used for drawing
     * @author Aidan Baker
     */
    public void paint(Graphics g) {
        switch (type) {
            case 1:
                drawType1(g);
                break;
            case 2:
                drawType2(g);
                break;
            default:
                drawType3(g);
                break;
        }
    }
}
