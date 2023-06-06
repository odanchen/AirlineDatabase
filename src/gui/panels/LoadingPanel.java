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
        int x1 = 0, y1 = 0, x2 = 60, y2 = 0, x = getWidth() / 2, y = getHeight() / 2, step = 60;
        g.setColor(PATTERN_COLOR);
        while (x2 < getWidth()) {
            g.fillPolygon(new int[]{x, x1, x2}, new int[]{y, y1, y2}, 3);
            x1 += step * 2;
            x2 += step * 2;
        }
        y2 = step;
        while (y2 < getHeight()) {
            g.fillPolygon(new int[]{x, x1, x2}, new int[]{y, y1, y2}, 3);
            y1 += step * 2;
            y2 += step * 2;
        }
        y1 = getHeight(); y2 = getHeight();
        x1 = getWidth() - 60; x2 = getWidth();
        while (x1 > 0) {
            g.fillPolygon(new int[]{x, x1, x2}, new int[]{y, y1, y2}, 3);
            x1 -= step * 2;
            x2 -= step * 2;
        }
        x1 = 0;x2 = 0;
        y1 = getHeight() - step;
        while (y1 > 0) {
            g.fillPolygon(new int[]{x, x1, x2}, new int[]{y, y1, y2}, 3);
            y1 -= step * 2;
            y2 -= step * 2;
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

