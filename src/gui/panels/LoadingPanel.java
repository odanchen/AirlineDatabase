/*
Author: Oleksandr Danchenko
time spent: 20 minutes
Date: 17 May 2023
version #1
 */

package gui.panels;

import gui.ApplicationFrame;
import gui.graphics.LoadingBar;

import java.awt.BorderLayout;

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
     * Creates a new instance of LoadingPanel.
     *
     * @param applicationFrame The ApplicationFrame instance to which this panel belongs.
     * @author Oleksandr Dacnehnko
     */
    public LoadingPanel(ApplicationFrame applicationFrame) {
        super(applicationFrame, new BorderLayout());
        //buttonPanel.setVisible(false);
        centerPanel.add(loadingBar, BorderLayout.CENTER);
    }

    @Override
    public String getTitle() {
        return "Loading";
    }

    @Override
    public void makeVisible() {
        super.makeVisible();
        applicationFrame.setBackButtonVisibility(false);
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
            if (i % 800000 == 0) loadingBar.update(i / 6000000);
        }
        applicationFrame.switchToHome();
    }
}

