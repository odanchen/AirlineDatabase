/*
Author: Oleksandr Danchenko
Time Spent: 25 minutes
Date: 1 June 2023
version# 2
Changes: fixed the error with multiple threads accessing the list of objects drawn on the top panel
        by adding the updateDrawing() method that copies the list ob objects and forces the top panel to repaint.
    Time spent: 7 minutes
    Date: 5 June 2023
    Author Oleksandr Danchenko
*/

package gui.graphics;

import gui.components.TopPanel;

import java.util.ArrayList;
import java.util.List;

/**
 * Runs the cloud animation on the top panel
 *
 * @author Oleksandr Danchneko
 */
public class CloudAnimation extends Thread {
    /**
     * The reference to the top panel.
     */
    private final TopPanel topPanel;
    /**
     * The list of active clouds.
     */
    private final List<FlyingObject> flyingObjects;

    /**
     * Constructs a CloudAnimation object with the specified TopPanel and list of clouds.
     *
     * @param topPanel the TopPanel object to repaint
     * @author Oleksandr Danchneko
     */
    public CloudAnimation(TopPanel topPanel) {
        this.topPanel = topPanel;
        this.flyingObjects = new ArrayList<FlyingObject>();
    }

    /**
     * Updates the drawing of the clouds on the top panel.
     *
     * @author Oleksandr Danchenko
     */
    private void updateDrawing() {
        List<FlyingObject> listCopy = new ArrayList<>(flyingObjects.size());
        for (FlyingObject flyingObject : this.flyingObjects) listCopy.add(FlyingObject.copyOf(flyingObject));
        topPanel.updateAnimation(listCopy);
    }

    /**
     * Runs the cloud animation thread.
     *
     * @author Oleksandr Danchneko
     */
    public void run() {
        while (true) {
            for (int i = 0; i < 45; i++) {
                for (FlyingObject flyingObject : this.flyingObjects) flyingObject.move();
                for (int j = 0; j < flyingObjects.size(); j++) if (flyingObjects.get(j).isOverScreen()) flyingObjects.remove(j--);
                try {
                    Thread.sleep(45);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                updateDrawing();
            }
            if (Math.random() * 10 > 3) flyingObjects.add(new FlyingObject((int) (Math.random() * 30)));
        }
    }
}
