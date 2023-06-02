/*
Author: Aidan Baker, Oleksandr Danchenko
time spent: 30 minutes
Date: 17 May 2023
version #3
changes: Added option to have back button and styling
        time spent: 20 minutes
        Date: 24 May 2023
        Author: Aidan Baker
changes: added methods overloaded addButton() methods for code refactoring, added methods that show different windows
        time spent: 15 minutes
        Date 24 May 2023
        Author: Oleksandr Danchenko
 */
package gui.panels;

import gui.ApplicationFrame;
import gui.components.CustomPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class ScreenPanel extends CustomPanel implements ActionListener {
    /**
     * A reference to the application frame.
     */
    protected ApplicationFrame applicationFrame;
    /**
     * The panel in the center of the ScreenPanel
     */
    protected CustomPanel centerPanel = new CustomPanel();


    /**
     * The constructor for the CustomPanel class.
     *
     * @param applicationFrame the reference to the application frame.
     * @author Aidan Baker
     */
    private ScreenPanel(ApplicationFrame applicationFrame) {
        this.applicationFrame = applicationFrame;
        setLayout(new BorderLayout());
        //bottom button bar

        CustomPanel backAndLogoPanel = new CustomPanel(new GridLayout(1, 2));
        backAndLogoPanel.setLayout(new GridLayout(1, 2));

        centerPanel.setPreferredSize(new Dimension(1350, 530));
        add(centerPanel, BorderLayout.CENTER);
        setVisible(false);
    }

    /**
     * A constructor that specifies the layout manager for the center panel
     *
     * @param applicationFrame   the reference to the application frame.
     * @param centerPanelManager the layout manager of the center panel.
     * @author Oleksandr Danchenko
     */
    public ScreenPanel(ApplicationFrame applicationFrame, LayoutManager centerPanelManager) {
        this(applicationFrame);
        centerPanel.setLayout(centerPanelManager);
    }

    /**
     * A constructor that specifies the axis for the box layout manager for the center panel
     *
     * @param applicationFrame the reference to the application frame.
     * @param axis             the axis of the bax layout of the center panel.
     * @author Oleksandr Danchenko
     */
    public ScreenPanel(ApplicationFrame applicationFrame, int axis) {
        this(applicationFrame);
        centerPanel.setLayout(new BoxLayout(centerPanel, axis));
    }

    /**
     * Shows an error message in a dialog window.
     *
     * @param message the message to be displayed.
     * @author Oleksandr Danchenko
     */
    protected void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Shows a success message in a dialog window.
     *
     * @param message the message to be displayed.
     * @author Oleksandr Danchenko
     */
    protected void showSuccessMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Shows a confirmation message in a dialog window.
     *
     * @param message the message to be displayed.
     * @return a boolean value representing whether the user confirmed the action.
     * @author Oleksandr Danchenko
     */
    protected boolean userConfirm(String message) {
        return JOptionPane.showConfirmDialog(null, message, "Confirmation", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }

    public abstract String getTitle();

    public void makeVisible() {
        applicationFrame.setTitle(getTitle());
        setVisible(true);
    }

    /**
     * A method that is executed when a button is pressed.
     *
     * @param e the event to be processed
     * @author Aidan Baker.
     */
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
