/*
Author: Aidan Baker
Time Spent: 30 minutes
Date: 31 May 2023
version# 3
Changes: Added a plane that can fly across the screen.
    Time Spent: 50 minutes
    Date: 3 June 2023
    Author: Aidan Baker
Changes: fixed the error with multiple threads accessing the list of objects drawn on the top panel
        by adding the copyOf() method that is used in the CloudAnimation class to copy the list of clouds.
    Time spent: 10 minutes
    Date: 5 June 2023
    Author Oleksandr Danchenko
*/

package gui.graphics;

import java.awt.*;

/**
 * The FlyingObjects class represents a cloud or a plane that flies across the top of the screen.
 *
 * @author Aidan Baker
 */
public class FlyingObjects {
    /**
     * The x-position of the cloud.
     */
    private int x;

    /**
     * The y-position of the cloud.
     */
    private int y;

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
     * @param y the y-position of the cloud
     * @author Aidan Baker
     */
    public FlyingObjects(int y) {
        this.y = y;
        this.type = (int) (Math.random() * 7);

        if ((int) (Math.random() * 2) == 1) {
            speed = (int) (Math.random() * 4) + 2;
            x = -200;
        } else {
            speed = ((int) -(Math.random() * 4) + 2);
            x = 1600;
        }
    }

    /**
     * A constructor that creates the object with all specific parameters.
     *
     * @param x the x position of the object.
     * @param y the y position of the object.
     * @param type the type of the object.
     * @param speed the speed of the object.
     * @author Oleksandr Danchenko
     */
    private FlyingObjects(int x, int y, int type, int speed) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.speed = speed;
    }

    /**
     * The method that creates a copy of the provided instance.
     *
     * @param flyingObjects the instance to be copied.
     * @return the complete copy of the provided instance.
     * @author Oleksandr Danchenko
     */
    public static FlyingObjects copyOf(FlyingObjects flyingObjects) {
        return new FlyingObjects(flyingObjects.x, flyingObjects.y, flyingObjects.type, flyingObjects.speed);
    }

    /**
     * Draws the cloud of type 1.
     *
     * @param g the Graphics object used for drawing
     * @author Aidan Baker
     */
    private void drawType1(Graphics g) {
        g.setColor(WHITE);

        g.fillOval(x + 20, y, 65, 35);
        g.fillOval(x, y + 22, 75, 45);
        g.fillOval(x + 45, y + 25, 70, 50);
    }

    /**
     * Draws the cloud of type 2.
     *
     * @param g the Graphics object used for drawing
     * @author Aidan Baker
     */
    private void drawType2(Graphics g) {
        g.setColor(WHITE);

        g.fillOval(x, y + 10, 50, 50);
        g.fillOval(x + 30, y, 50, 50);
        g.fillOval(x + 60, y + 10, 50, 50);

        g.fillOval(x + 20, y + 30, 50, 50);
        g.fillOval(x + 50, y + 30, 50, 50);
    }

    /**
     * Draws the cloud of type 3.
     *
     * @param g the Graphics object used for drawing
     * @author Aidan Baker
     */
    private void drawType3(Graphics g) {
        g.setColor(WHITE);

        g.fillOval(x, y + 10, 40, 40);
        g.fillOval(x + 30, y, 60, 60);
        g.fillOval(x + 70, y + 10, 40, 40);

        g.fillOval(x + 10, y + 30, 30, 30);
        g.fillOval(x + 60, y + 30, 30, 30);
        g.fillOval(x + 30, y + 40, 40, 40);
    }

    /**
     * Draws a plane on the screen.
     *
     * @param g the Graphics object used for drawing
     * @author Aidan Baker
     */
    private void drawPlane(Graphics g) {
        final Color BLACK = Color.BLACK;
        final Color GRAY = Color.LIGHT_GRAY;

        if (speed < 0) {
            //wings
            int[] topWingX = {x + 117, x + 128, x + 139, x + 133};
            int[] topWingY = {63 - y, 36 - y, 35 - y, 61 - y};
            int[] bottomWingX = {x + 117, x + 132, x + 143, x + 133};
            int[] bottomWingY = {79 - y, 106 - y, 107 - y, 81 - y};

            //tail fins
            int[] mainTailFinX = {x + 180, x + 203, x + 213, x + 190};
            int[] mainTailFinY = {65 - y, 48 - y, 50 - y, 73 - y};
            int[] bottomTailFinX = {x + 182, x + 199, x + 204, x + 190};
            int[] bottomTailFinY = {79 - y, 88 - y, 85 - y, 73 - y};
            int[] topTailFinX = {x + 178, x + 186, x + 190, x + 189};
            int[] topTailFinY = {64 - y, 51 - y, 51 - y, 67 - y};

            g.setColor(GRAY);
            g.fillPolygon(topWingX, topWingY, 4);
            g.setColor(BLACK);
            g.drawPolygon(topWingX, topWingY, 4);

            //Body of plane
            g.setColor(GRAY);
            g.fillOval(x + 90, 60 - y, 100, 25);
            g.setColor(BLACK);
            g.drawOval(x + 90, 60 - y, 100, 25);

            //top tail fin
            g.setColor(GRAY);
            g.fillPolygon(topTailFinX, topTailFinY, 4);
            g.setColor(BLACK);
            g.drawPolygon(topTailFinX, topTailFinY, 4);

            //main and bottom tail fin
            g.setColor(GRAY);
            g.fillPolygon(mainTailFinX, mainTailFinY, 4);
            g.fillPolygon(bottomTailFinX, bottomTailFinY, 4);
            g.setColor(BLACK);
            g.drawPolygon(mainTailFinX, mainTailFinY, 4);
            g.drawPolygon(bottomTailFinX, bottomTailFinY, 4);

            //cockpit
            g.setColor(Color.cyan);
            g.fillArc(x + 90, 65 - y, 33, 15, 90, 90);
            g.setColor(BLACK);
            g.drawArc(x + 90, 65 - y, 33, 15, 90, 90);
            g.drawLine(x + 106, 66 - y, x + 106, 73 - y);
            g.drawLine(x + 91, 73 - y, x + 106, 73 - y);

            //bottom wing
            g.setColor(GRAY);
            g.fillPolygon(bottomWingX, bottomWingY, 4);
            g.setColor(BLACK);
            g.drawPolygon(bottomWingX, bottomWingY, 4);

            //windows
            g.setColor(Color.cyan);
            g.fillRoundRect(x + 120, 67 - y, 5, 7, 4, 4);
            g.fillRoundRect(x + 130, 67 - y, 5, 7, 4, 4);
            g.fillRoundRect(x + 140, 67 - y, 5, 7, 4, 4);
            g.fillRoundRect(x + 150, 67 - y, 5, 7, 4, 4);
            g.fillRoundRect(x + 160, 67 - y, 5, 7, 4, 4);

            g.setColor(BLACK);
            g.drawRoundRect(x + 120, 67 - y, 5, 7, 4, 4);
            g.drawRoundRect(x + 130, 67 - y, 5, 7, 4, 4);
            g.drawRoundRect(x + 140, 67 - y, 5, 7, 4, 4);
            g.drawRoundRect(x + 150, 67 - y, 5, 7, 4, 4);
            g.drawRoundRect(x + 160, 67 - y, 5, 7, 4, 4);
        } else {
            // Wings
            int[] topWingX = {x + 183, x + 172, x + 161, x + 167};
            int[] topWingY = {63 - y, 36 - y, 35 - y, 61 - y};
            int[] bottomWingX = {x + 183, x + 168, x + 157, x + 167};
            int[] bottomWingY = {79 - y, 106 - y, 107 - y, 81 - y};

            // Tail fins
            int[] mainTailFinX = {x + 120, x + 97, x + 87, x + 110};
            int[] mainTailFinY = {65 - y, 48 - y, 50 - y, 73 - y};
            int[] bottomTailFinX = {x + 118, x + 101, x + 96, x + 110};
            int[] bottomTailFinY = {79 - y, 88 - y, 85 - y, 73 - y};
            int[] topTailFinX = {x + 122, x + 114, x + 110, x + 111};
            int[] topTailFinY = {64 - y, 51 - y, 51 - y, 67 - y};

            g.setColor(GRAY);
            g.fillPolygon(topWingX, topWingY, 4);
            g.setColor(BLACK);
            g.drawPolygon(topWingX, topWingY, 4);

            // Body of plane
            g.setColor(GRAY);
            g.fillOval(x + 110, 60 - y, 100, 25);
            g.setColor(BLACK);
            g.drawOval(x + 110, 60 - y, 100, 25);

            // Top tail fin
            g.setColor(GRAY);
            g.fillPolygon(topTailFinX, topTailFinY, 4);
            g.setColor(BLACK);
            g.drawPolygon(topTailFinX, topTailFinY, 4);

            // Main and bottom tail fin
            g.setColor(GRAY);
            g.fillPolygon(mainTailFinX, mainTailFinY, 4);
            g.fillPolygon(bottomTailFinX, bottomTailFinY, 4);
            g.setColor(BLACK);
            g.drawPolygon(mainTailFinX, mainTailFinY, 4);
            g.drawPolygon(bottomTailFinX, bottomTailFinY, 4);

            // Cockpit
            g.setColor(Color.CYAN);
            g.fillArc(x + 177, 65 - y, 33, 15, 0, 90);
            g.setColor(BLACK);
            g.drawArc(x + 177, 65 - y, 33, 15, 0, 90);
            g.drawLine(x + 193, 66 - y, x + 193, 73 - y);
            g.drawLine(x + 193, 73 - y, x + 208, 73 - y);

            // Bottom wing
            g.setColor(GRAY);
            g.fillPolygon(bottomWingX, bottomWingY, 4);
            g.setColor(BLACK);
            g.drawPolygon(bottomWingX, bottomWingY, 4);

            // Windows
            g.setColor(Color.CYAN);
            g.fillRoundRect(x + 180, 67 - y, 5, 7, 4, 4);
            g.fillRoundRect(x + 170, 67 - y, 5, 7, 4, 4);
            g.fillRoundRect(x + 160, 67 - y, 5, 7, 4, 4);
            g.fillRoundRect(x + 150, 67 - y, 5, 7, 4, 4);
            g.fillRoundRect(x + 140, 67 - y, 5, 7, 4, 4);

            g.setColor(BLACK);
            g.drawRoundRect(x + 180, 67 - y, 5, 7, 4, 4);
            g.drawRoundRect(x + 170, 67 - y, 5, 7, 4, 4);
            g.drawRoundRect(x + 160, 67 - y, 5, 7, 4, 4);
            g.drawRoundRect(x + 150, 67 - y, 5, 7, 4, 4);
            g.drawRoundRect(x + 140, 67 - y, 5, 7, 4, 4);
        }
    }

    /**
     * Moves the cloud horizontally based on its speed.
     *
     * @author Aidan Baker
     */
    public void move() {
        x += speed;

        if (type == 7 || type == 0) {
            y = (int) (Math.sin(x / 50.0) * 15) + 6;
        }
    }

    /**
     * Checks if the cloud has moved completely off the screen.
     *
     * @return true if the cloud is no longer visible on the screen, false otherwise
     * @author Aidan Baker
     */
    public boolean isOverScreen() {
        if (speed > 0) return x > 1400;
        else return x < -200;
    }

    /**
     * Paints the cloud on the screen based on its type.
     *
     * @param g the Graphics object used for drawing
     * @author Aidan Baker
     */
    public void paint(Graphics g) {
        if (type == 1 || type == 2) drawType1(g);
        else if (type == 3 || type == 4) drawType2(g);
        else if (type == 5 || type == 6) drawType3(g);
        else drawPlane(g);
    }
}
