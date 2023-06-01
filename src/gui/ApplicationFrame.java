/*
Author: Oleksandr Danchenko
time spent: 40 minutes
Date: 17 May 2023
version #2
changes: added methods to switch back to certain screens to be used when a back button is pressed
    Author: Aidan baker
    time spent: 10 minutes
    Date: 23 May 2023
 */

package gui;

import javax.swing.*;

import gui.components.CustomButton;
import gui.components.CustomPanel;
import gui.components.CustomRadioButton;
import gui.components.TopPanel;
import logic.data_record.Calendar;
import resource.DataReader;
import gui.panels.*;
import logic.data_record.Flight;
import logic.data_record.FlightInfo;
import logic.data_record.Seat;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.util.List;

/**
 * The ApplicationFrame class represents the main frame of the application.
 * It extends the JFrame class and manages the different gui.panels of the application.
 *
 * @author Oleksandr Dacnehnko, Aidan Baker
 */
public class ApplicationFrame extends JFrame implements ActionListener {
    /**
     * A reference to the loading panel of the application.
     */
    private final ScreenPanel loadingPanel;

    /**
     * A reference to the home panel of the application.
     */
    private final ScreenPanel homePanel;

    /**
     * A reference to the calendar panel of the application.
     */
    private final ScreenPanel calendarPanel;

    /**
     * A reference to the search panel of the application.
     */
    private final ScreenPanel searchPanel;

    /**
     * A reference to the flight list panel of the application.
     */
    private final ScreenPanel flightListPanel;

    /**
     * A reference to the seat panel of the application.
     */
    private final ScreenPanel seatPanel;

    /**
     * A reference to the user input panel of the application.
     */
    private final ScreenPanel userInputPanel;

    /**
     * A reference to the export panel of the application.
     */
    private final ScreenPanel exportPanel;

    private final TopPanel topPanel;
    /**
     * A reference to the currently active panel in the application.
     */
    private ScreenPanel currentPanel;

    /**
     * The panel at the bottom of a ScreenPanel, used for navigation buttons.
     */
    protected CustomPanel buttonPanel = new CustomPanel(new GridLayout(1, 5));

    public CustomButton homeButton;
    public CustomButton searchButton;
    public CustomButton calendarButton;
    /**
     * The link to the user manual.
     */
    private static final String MANUAL_URL = "https://docs.google.com/document/d/1MoQYM9OzFQPjyVoqWxH3KVLu8QRYIB-3qXgkCfCr4uc/edit?usp=sharing";


    /**
     * Creates a new instance of ApplicationFrame.
     * Initializes the frame and sets the loading panel as the initial visible panel.
     * Displays the splash screen and performs the initialization process.
     *
     * @author Oleksandr Dacnehnko
     */
    public ApplicationFrame() {
        super();
        getContentPane().setBackground(CustomPanel.BACKGROUND_WHITE);
        getContentPane().setLayout(new BorderLayout());
        CustomPanel centerPanel = new CustomPanel();
        Calendar calendar = DataReader.getCalendar();

        add(topPanel = new TopPanel(this), BorderLayout.NORTH);
        buttonPanel.add(homeButton = new CustomButton("Home", "home", this, 25, new Dimension(150, 55)));
        buttonPanel.add(searchButton = new CustomButton("Search for a Flight", "search", this, 25, new Dimension(150, 55)));
        buttonPanel.add(calendarButton = new CustomButton("Calendar", "calendar", this, 25, new Dimension(150, 55)));
        buttonPanel.add(new CustomButton("User Manual", "manual", this, 25, new Dimension(150, 55)));
        buttonPanel.add(new CustomButton("Exit", "exit", this, 25, new Dimension(150, 55)));
        //add button panel to bottom of frame
        add(buttonPanel, BorderLayout.SOUTH);

        centerPanel.add(loadingPanel = new LoadingPanel(this));
        centerPanel.add(homePanel = new HomePanel(this));
        centerPanel.add(calendarPanel = new CalendarPanel(this, calendar));
        centerPanel.add(searchPanel = new SearchPanel(this, calendar));
        centerPanel.add(flightListPanel = new FlightListPanel(this, calendar));
        centerPanel.add(seatPanel = new SeatPanel(this));
        centerPanel.add(userInputPanel = new UserInputPanel(this, calendar));
        centerPanel.add(exportPanel = new ExportPanel(this));
        currentPanel = loadingPanel;
        add(centerPanel, BorderLayout.CENTER);
        setSize(1400, 750);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

        repaint();
        loadingPanel.makeVisible();
    }

    private void switchScreen(ScreenPanel newScreen) {
        deSelectButtons();
        currentPanel.setVisible(false);
        currentPanel = newScreen;
    }

    /**
     * Switches the current panel to the home panel.
     * Hides the current panel and shows the home panel.
     * Updates the screen message of the home panel.
     *
     * @author Oleksandr Dacnehnko
     */
    public void switchToHome() {
        switchScreen(homePanel);
        homePanel.makeVisible();
        homeButton.setColor(CustomRadioButton.SELECTED_COLOR);
    }

    /**
     * Switches the current panel to the calendar panel.
     * Hides the current panel and shows the calendar panel.
     *
     * @author Oleksandr Dacnehnko
     */
    public void switchToCalendar() {
        switchScreen(calendarPanel);
        calendarPanel.makeVisible();
        calendarButton.setColor(CustomRadioButton.SELECTED_COLOR);
    }

    /**
     * Switches the current panel to the search panel.
     * Hides the current panel and shows the search panel.
     *
     * @author Oleksandr Dacnehnko
     */
    public void switchToSearch() {
        switchScreen(searchPanel);
        searchPanel.makeVisible();
        searchButton.setColor(CustomRadioButton.SELECTED_COLOR);
    }

    /**
     * Switches the current panel to the flight list panel.
     * Hides the current panel and shows the flight list panel.
     *
     * @param flightList the list of flights to chose from in the panel.
     * @author Oleksandr Dacnehnko
     */
    public void switchToFlightList(List<FlightInfo> flightList) {
        switchScreen(flightListPanel);
        ((FlightListPanel) flightListPanel).makeVisible(flightList);
    }

    /**
     * Switches the current panel to the seat panel.
     * Hides the current panel and shows the seat panel.
     *
     * @param flight the flight for the user to book a seat on.
     * @author Oleksandr Dacnehnko
     */
    public void switchToSeat(Flight flight) {
        switchScreen(seatPanel);
        ((SeatPanel) seatPanel).makeVisible(flight);
    }

    /**
     * Returns to the seat selector panel with its previous state.
     *
     * @author Aidan Baker
     */
    public void switchBackToSeat() {
        switchScreen(seatPanel);
        seatPanel.makeVisible();
    }

    /**
     * Returns to the flight list panel with its previous state.
     *
     * @author Aidan Baker
     */
    public void switchBackToList() {
        switchScreen(flightListPanel);
        ((FlightListPanel) flightListPanel).makeVisible(((FlightListPanel)flightListPanel).getFlightList());
    }

    /**
     * Returns to the panel that was passed in with it previous state.
     * @param panel the panel to return to.
     * @author Aidan Baker
     */
    public void switchBackTo(JPanel panel) {
        deSelectButtons();
        currentPanel.setVisible(false);
        panel.setVisible(true);
        currentPanel = (ScreenPanel) panel;
    }

    /**
     * Switches the current panel to the user input panel.
     * Hides the current panel and shows the user input panel.
     *
     * @author Oleksandr Dacnehnko
     */
    public void switchToInput(Flight flight, Seat seat) {
        deSelectButtons();
        currentPanel.setVisible(false);
        ((UserInputPanel) userInputPanel).makeVisible(flight, seat);
        currentPanel = userInputPanel;
    }

    /**
     * Switches the current panel to the export panel.
     * Hides the current panel and shows the export panel.
     *
     * @author Oleksandr Dacnehnko
     */
    public void switchToExport(Seat[] seats) {
        ((ExportPanel) exportPanel).makeVisible(seats, currentPanel);
        switchScreen(exportPanel);
    }

    private void deSelectButtons() {
        homeButton.setColor(CustomButton.BUTTON_BLUE);
        searchButton.setColor(CustomButton.BUTTON_BLUE);
        calendarButton.setColor(CustomButton.BUTTON_BLUE);
    }

    public void setTitle(String text) {
        topPanel.setTitle(text);
    }

    public void setBackButtonVisibility(boolean visibility) {
        topPanel.setBackButtonVisibility(visibility);
    }

    public void setHoodVisibility(boolean visibility) {
        topPanel.setVisible(visibility);
        buttonPanel.setVisible(visibility);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "home": switchToHome();
                break;
            case "search": switchToSearch();
                break;
            case "calendar": switchToCalendar();
                break;
            case "manual" :
                try {
                    Desktop.getDesktop().browse(URI.create(MANUAL_URL));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                break;
            case "exit" : if (JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Confirmation", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) System.exit(0);
                break;
            case "back" : currentPanel.actionPerformed(e);
        }
    }
}
