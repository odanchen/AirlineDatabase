/*
Author: Aidan Baker, Oleksandr Danchenko
time spent: 30 minutes
Date: 17 May 2023
version #4
changes: Added option to have back button and styling
        time spent: 20 minutes
        Date: 24 May 2023
        Author: Aidan Baker
changes: added methods overloaded addButton() methods for code refactoring, added methods that show different windows
        time spent: 15 minutes
        Date 24 May 2023
        Author: Oleksandr Danchenko
Changes: removed the centerPanel field as the top and the button panels no longer belong to the ScreenPanel
        time spent: 25 minutes
        Date 6 June 2023
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
     * The constructor for the CustomPanel class.
     *
     * Citation: https://docs.oracle.com/javase/8/docs/api/javax/swing/JComponent.html#setPreferredSize-java.awt.Dimension-
     * Citation: https://docs.oracle.com/javase/8/docs/api/java/awt/Dimension.html
     *      The setPreferredSize() method is used to specify the preferred size of the component.
     *      Here, it is used to specify the approximate size of the screen to the layout manager of the frame.
     *      The Dimension class is used as a parameter and contains the preferred width and height of the component respectively.
     * @param applicationFrame the reference to the application frame.
     * @author Aidan Baker
     */
    private ScreenPanel(ApplicationFrame applicationFrame) {
        this.applicationFrame = applicationFrame;
        setLayout(new BorderLayout());

        setPreferredSize(new Dimension(1350, 530));
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
        setLayout(centerPanelManager);
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
        setLayout(new BoxLayout(this, axis));
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

    /**
     * Gets the title for the screen.
     *
     * @return the title for the screen.
     */
    public abstract String getTitle();

    /**
     * A method that is called when the screen is made visible.
     */
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
