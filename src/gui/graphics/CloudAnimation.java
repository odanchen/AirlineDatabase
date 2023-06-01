/*
Author: Aidan Baker
Time Spent: 25 minutes
Date: 1 June 2023
version# 1
*/

package gui.graphics;

import gui.components.TopPanel;

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
    private final List<Cloud> clouds;

    /**
     * Constructs a CloudAnimation object with the specified TopPanel and list of clouds.
     *
     * @param topPanel the TopPanel object to repaint
     * @param clouds   the list of clouds to animate
     * @author Oleksandr Danchneko
     */
    public CloudAnimation(TopPanel topPanel, List<Cloud> clouds) {
        this.topPanel = topPanel;
        this.clouds = clouds;
    }

    /**
     * Runs the cloud animation thread.
     *
     * @author Oleksandr Danchneko
     */
    public void run() {
        while (true) {
            for (int i = 0; i < 50; i++) {
                for (Cloud cloud : clouds) cloud.move();
                for (int j = 0; j < clouds.size(); j++) if (clouds.get(j).isOverScreen()) clouds.remove(j--);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                topPanel.repaint();
            }
            if (Math.random() * 10 > 6) clouds.add(new Cloud((int) (Math.random() * 45)));
        }
    }
}
