/*
Author: Oleksandr Danchenko
time spent: 20 minutes
Date: 17 May 2023
version #3
Changes: implemented the getTitle() and makeVisible() methods added to the ScreenPanel in the process of cleaning up the code.
    time spent: 5 minutes
    Date 1 June 2023
    Author: Oleksandr Danchenko
Changes: added a circular pattern drawing on the screen.
    time spent: 30 minutes
    Date 6 June 2023
    Author Oleksandr Danchenko
 */

package gui.panels;

import gui.ApplicationFrame;
import gui.components.CustomPanel;
import gui.graphics.LoadingBar;
import gui.graphics.Loading_Logo;

import javax.swing.*;
import java.awt.*;

/**
 * The LoadingPanel class represents a custom panel that displays a splash screen with a loading bar.
 * It extends the CustomPanel class and contains a LoadingBar component.
 *
 * @author Oleksandr Dacnehnko
 */
public class LoadingPanel extends ScreenPanel {
    /**
     * The animated loading bar component.
     * Fills up while the splash screen is visible.
     */
    private final LoadingBar loadingBar = new LoadingBar();

    /**
     * The color of the circular patter drawing on the screen.
     */
    private final Color PATTERN_COLOR = new Color(190, 200, 230);

    /**
     * Creates a new instance of LoadingPanel.
     *
     * @param applicationFrame The ApplicationFrame instance to which this panel belongs.
     * @author Oleksandr Dacnehnko
     */
    public LoadingPanel(ApplicationFrame applicationFrame) {
        super(applicationFrame, BoxLayout.Y_AXIS);
        centerPanel.setPreferredSize(new Dimension(1400, 800));
        centerPanel.setBackground(new Color(0, 0, 0, 0));
        centerPanel.add(new Loading_Logo());
        centerPanel.add(loadingBar);
    }

    /**
     * Returns the title of the screen.
     *
     * @return "Loading"
     * @author Oleksandr Danchenko
     */
    @Override
    public String getTitle() {
        return "Loading";
    }

    /**
     * Makes the screen visible and runs the splash screen.
     *
     * @author Oleksandr Danchenko
     */
    @Override
    public void makeVisible() {
        super.makeVisible();
        applicationFrame.setBackButtonVisibility(false);
        applicationFrame.setHudVisibility(false);
        showSplashScreen();
    }

    /**
     * Displays the splash screen with the loading bar for a couple seconds.
     * The loading bar updates its progress during the time the splash screen exists.
     * After the loading bar fills up, the application frame switches to the home screen.
     *
     * @author Oleksandr Dacnehnko
     */
    public void showSplashScreen() {
        for (int i = 0; i < 2000000000; i++) {
            if (i % 800000 == 0) {
                loadingBar.update(i / 6000000);
                repaint();
            }
        }
        applicationFrame.setHudVisibility(true);
        applicationFrame.switchToHome();
    }

    /**
     * Draws the circular pattern on the screen.
     *
     * @param g the instance of the Graphics class.
     * @author Oleksandr Danchenko
     */
    private void drawPattern(Graphics g) {
        int x0 = getWidth() / 2, y0 = getHeight() / 2, len = 830;
        double angle = 0;
        g.setColor(PATTERN_COLOR);
        while (angle < Math.PI * 2) {
            int[] xs = new int[]{x0, x0 + (int) (len * Math.cos(angle)), x0 + (int) (len * Math.cos(angle + Math.PI / 16))};
            int[] ys = new int[]{y0, y0 + (int) (len * Math.sin(angle)), y0 + (int) (len * Math.sin(angle + Math.PI / 16))};
            g.fillPolygon(xs, ys, 3);
            angle += Math.PI / 8;
        }
    }

    /**
     * The paint method, paints the component and the pattern on the screen.
     *
     * @param g the instance of the Graphics class
     * @author Oleksandr Danchenko
     */
    @Override
    public void paint(Graphics g) {
        g.setColor(CustomPanel.BACKGROUND_WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        drawPattern(g);
        super.paint(g);
    }
}

