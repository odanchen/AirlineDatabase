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
    /**
     * A reference to the application frame.
     */
    protected ApplicationFrame applicationFrame;
    /**
     * The panel in the center of the ScreenPanel
     */
    protected CustomPanel centerPanel = new CustomPanel();
    /**
     * The panel at the bottom of a ScreenPanel, used for navigation buttons.
     */
    protected CustomPanel buttonPanel = new CustomPanel(new GridLayout(1, 5));
    /**
     * The panel at the top of the Screen panel, used for a title and a logo.
     */
    protected CustomPanel topPanel = new CustomPanel(BoxLayout.X_AXIS);
    /**
     * The back button, visible on some screens.
     */
    private final JButton backButton = new JButton("<");
    /**
     * A placeholder to hold the logo in the same place when the back button is invisible.
     */
    private final JLabel placeHolder = new JLabel("");
    /**
     * The home button, switches the current screen to home screen when pressed.
     */
    protected final CustomButton homeButton = new CustomButton("Home", 25);
    /**
     * The flight search button, switches the current screen to flight search screen when pressed.
     */
    protected final CustomButton flightSearchButton = new CustomButton("Search for a Flight", 25);
    /**
     * The calendar button, switches the current screen to calendar screen when pressed.
     */
    protected final CustomButton calendarButton = new CustomButton("Calendar", 25);
    /**
     * The link to the user manual.
     */
    private static final String MANUAL_URL = "https://docs.google.com/document/d/1MoQYM9OzFQPjyVoqWxH3KVLu8QRYIB-3qXgkCfCr4uc/edit?usp=sharing";

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

        addButton(homeButton, homeButton.getText(), buttonPanel);
        addButton(flightSearchButton, flightSearchButton.getText(), buttonPanel);
        addButton(calendarButton, calendarButton.getText(), buttonPanel);
        addButton("User Manual", "User Manual", buttonPanel, 25);
        addButton("Exit", "Exit", buttonPanel, 25);

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
     * Sets the title of the screen to be a specified text.
     *
     * @param title the text in the title.
     * @author Aidan Baker
     */
    protected void setTitle(String title) {
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 65));
        titleLabel.setForeground(CustomButton.BUTTON_BLUE);
        topPanel.add(titleLabel);
    }

    /**
     * A method that adds a button with specified parameters.
     *
     * @param message     the message on the button.
     * @param command     the action command of the button.
     * @param buttonPanel the panel where the button should be added.
     * @param fontSize    the font size on the button.
     * @author Oleksandr Danchenko
     */
    private void addButton(String message, String command, JPanel buttonPanel, int fontSize) {
        addButton(new CustomButton(message, fontSize), command, buttonPanel);
    }

    /**
     * A method that adds a button with specified parameters.
     *
     * @param button      the button to be added.
     * @param command     the action command of the button.
     * @param buttonPanel the panel where the button should be added.
     * @author Oleksandr Danchenko
     */
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

    /**
     * A method that is executed when a button is pressed.
     *
     * @param e the event to be processed
     * @author Aidan Baker.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        switch (actionCommand) {
            case "Home": applicationFrame.switchToHome();
                break;
            case "Search for a Flight": applicationFrame.switchToSearch();
                break;
            case "Calendar": applicationFrame.switchToCalendar();
                break;
            case "User Manual" :
                try {
                    Desktop.getDesktop().browse(URI.create(MANUAL_URL));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                break;
            case "Exit" : if (userConfirm("Are you sure you want to exit?")) System.exit(0);
                break;
        }
    }
}
