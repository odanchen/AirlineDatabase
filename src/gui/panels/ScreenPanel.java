/*
Author: Aidan Baker, Oleksandr Danchenko
time spent: 30 minutes
Date: 17 May 2023
changes: Added option to have back button and styling
        time: 20 minutes
        Date: 24 May 2023
version #2
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
    private ScreenPanel(ApplicationFrame applicationFrame) {
        this.applicationFrame = applicationFrame;
        setLayout(new BorderLayout());
        //bottom button bar

        addButton(homeButton, homeButton, buttonPanel, 25);
        addButton(flightSearchButton, flightSearchButton, buttonPanel, 25);
        addButton(calendarButton, calendarButton, buttonPanel, 25);
        addButton(manualButton, manualButton, buttonPanel, 25);
        addButton(exitButton, exitButton, buttonPanel, 25);

        //add button panel to bottom of frame
        add(buttonPanel, BorderLayout.SOUTH);

        CustomPanel backAndLogoPanel = new CustomPanel(new GridLayout(1, 2));
        backAndLogoPanel.setLayout(new GridLayout(1, 2));

        placeHolder.setPreferredSize(new Dimension(51, 50));
        placeHolder.setVisible(true);

        backButton.setFont(new Font("Arial", Font.BOLD, 30));
        backButton.setVisible(false);
        backButton.setPreferredSize(new Dimension(50, 50));
        addButton(backButton, "back", topPanel);
        topPanel.add(placeHolder);

        topPanel.add(new Logo());
        add(topPanel, BorderLayout.NORTH);

        centerPanel.setPreferredSize(new Dimension(1000, 530));
        add(centerPanel, BorderLayout.CENTER);
        setVisible(false);
    }

    public ScreenPanel(ApplicationFrame applicationFrame, LayoutManager centerPanelManager) {
        this(applicationFrame);
        centerPanel.setLayout(centerPanelManager);
    }

    public ScreenPanel(ApplicationFrame applicationFrame, int axis) {
        this(applicationFrame);
        centerPanel.setLayout(new BoxLayout(centerPanel, axis));
    }

    protected void setTitle(String title) {
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 65));
        titleLabel.setForeground(CustomButton.BUTTON_BLUE);
        topPanel.add(titleLabel);
    }

    private void addButton(String message, String command, JPanel buttonPanel, int fontSize) {
        addButton(new CustomButton(message, fontSize),command, buttonPanel);
    }

    protected void addButton(JButton button, String command, JPanel buttonPanel) {
        button.setActionCommand(command);
        button.addActionListener(this);
        buttonPanel.add(button);
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
        addButton(message, command, buttonPanel, CustomButton.DEFAULT_FONT_SIZE);
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
