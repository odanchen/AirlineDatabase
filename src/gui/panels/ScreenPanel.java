/*
Author: Aidan Baker
time spent: todo
version #1
 */
package gui.panels;

import gui.components.CustomButton;
import gui.ApplicationFrame;
import gui.components.CustomPanel;
import gui.graphics.Logo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;

public abstract class ScreenPanel extends CustomPanel implements ActionListener {
    protected ApplicationFrame applicationFrame;
    protected CustomPanel centerPanel = new CustomPanel();
    protected CustomPanel buttonPanel = new CustomPanel(new GridLayout(1, 5));
    protected CustomPanel topPanel = new CustomPanel(BoxLayout.X_AXIS);
    private final JButton backButton = new JButton("<");
    private final JLabel placeHolder = new JLabel("");
    private final CustomPanel backAndLogoPanel = new CustomPanel(new GridLayout(1, 2));
    private static final String homeButton = "Home";
    private static final String flightSearchButton = "Search for a Flight";
    private static final String calendarButton = "Calendar";
    private static final String manualButton = "User Manual";
    private static final String exitButton = "Exit";
    private static final String MANUAL_URL = "https://docs.google.com/document/d/1MoQYM9OzFQPjyVoqWxH3KVLu8QRYIB-3qXgkCfCr4uc/edit?usp=sharing";

    /**
     * The constructor for the CustomPanel class.
     *
     * @author Aidan Baker
     */
    public ScreenPanel(ApplicationFrame applicationFrame) {
        this.applicationFrame = applicationFrame;
        setLayout(new BorderLayout());
        //bottom button bar

        addButton(homeButton, homeButton, buttonPanel, 30);
        addButton(flightSearchButton, flightSearchButton, buttonPanel, 25);
        addButton(calendarButton, calendarButton, buttonPanel, 30);
        addButton(manualButton, manualButton, buttonPanel, 30);
        addButton(exitButton, exitButton, buttonPanel, 30);

        //add button panel to bottom of frame
        add(buttonPanel, BorderLayout.SOUTH);

        backAndLogoPanel.setLayout(new GridLayout(1, 2));

        placeHolder.setPreferredSize(new Dimension(51, 50));
        placeHolder.setVisible(true);

        backButton.setFont(new Font("Arial", Font.BOLD, 30));
        backButton.setActionCommand("back");
        backButton.addActionListener(this);
        backButton.setVisible(false);
        backButton.setPreferredSize(new Dimension(50, 50));
        topPanel.add(backButton);
        topPanel.add(placeHolder);

        topPanel.add(new Logo());
        add(topPanel, BorderLayout.NORTH);

        centerPanel.setPreferredSize(new Dimension(1000, 500));
        add(centerPanel, BorderLayout.CENTER);
        setVisible(false);
    }

    protected void setTitle(String title) {
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 80));
        topPanel.add(titleLabel);
    }

    /**
     * Adds a button with specific characteristics to a specified panel.
     *
     * @param message     the message displayed on the button.
     * @param command     the action command of the button.
     * @param buttonPanel the panel, to which the button is added.
     * @author Oleksandr Danchenko
     */
    protected void addButton(String message, String command, JPanel buttonPanel) {
        CustomButton button = new CustomButton(message);
        button.setActionCommand(command);
        button.addActionListener(this);
        buttonPanel.add(button);
    }

    private void addButton(String message, String command, JPanel buttonPanel, int fontSize) {
        CustomButton button = new CustomButton(message);
        button.setFont(new Font("Arial", Font.BOLD, fontSize));
        button.setActionCommand(message);
        button.addActionListener(this);
        buttonPanel.add(button);
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
     * Makes the back button at the top left visible or invisible.
     *
     * @param visible whether the back button should be visible or not.
     */
    public void setBackButtonVisibility(boolean visible) {
        backButton.setVisible(visible);
        placeHolder.setVisible(!visible);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        switch (actionCommand) {
            case homeButton: applicationFrame.switchToHome();
                break;
            case flightSearchButton: applicationFrame.switchToSearch();
                break;
            case calendarButton: applicationFrame.switchToCalendar();
                break;
            case manualButton:
                try {
                    Desktop.getDesktop().browse(URI.create(MANUAL_URL));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                break;
            case exitButton: if (userConfirm("Are you sure you want to exit?")) System.exit(0);
                break;
        }
    }
}
