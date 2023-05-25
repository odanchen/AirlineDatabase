/*
Author: Oleksandr Danchenko
time spent: 40 minutes
Date: 17 May 2023
version #1
 */

package gui;

import javax.swing.*;

import gui.components.CustomPanel;
import logic.data_record.Calendar;
import database.interaction.DataReader;
import gui.panels.*;
import logic.data_record.Flight;
import logic.data_record.FlightInfo;
import logic.data_record.Seat;

import java.awt.*;
import java.util.List;

/**
 * The ApplicationFrame class represents the main frame of the application.
 * It extends the JFrame class and manages the different gui.panels of the application.
 *
 * @author Oleksandr Dacnehnko
 */
public class ApplicationFrame extends JFrame {
    /**
     * A reference to the loading panel of the application.
     */
    private final ScreenPanel loadingPanel = new LoadingPanel(this);

    /**
     * A reference to the home panel of the application.
     */
    private final ScreenPanel homePanel = new HomePanel(this);

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
    private final ScreenPanel seatPanel = new SeatPanel(this);

    /**
     * A reference to the user input panel of the application.
     */
    private final ScreenPanel userInputPanel;

    /**
     * A reference to the export panel of the application.
     */
    private final ScreenPanel exportPanel = new ExportPanel(this);

    /**
     * A reference to the currently active panel in the application.
     */
    private ScreenPanel currentPanel = loadingPanel;

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
        calendarPanel = new CalendarPanel(this, calendar);
        searchPanel = new SearchPanel(this, calendar);
        userInputPanel = new UserInputPanel(this, calendar);
        flightListPanel = new FlightListPanel(this, calendar);
        centerPanel.add(loadingPanel);
        centerPanel.add(homePanel);
        centerPanel.add(calendarPanel);
        centerPanel.add(searchPanel);
        centerPanel.add(flightListPanel);
        centerPanel.add(seatPanel);
        centerPanel.add(userInputPanel);
        centerPanel.add(exportPanel);
        add(centerPanel, BorderLayout.CENTER);
        setSize(1400, 750);
        setResizable(false);
        setLocationRelativeTo(null);
        loadingPanel.setVisible(true);
        setVisible(true);
        ((LoadingPanel) loadingPanel).showSplashScreen();
        repaint();
    }

    /**
     * Switches the current panel to the home panel.
     * Hides the current panel and shows the home panel.
     * Updates the screen message of the home panel.
     *
     * @author Oleksandr Dacnehnko
     */
    public void switchToHome() {
        currentPanel.setVisible(false);
        homePanel.setVisible(true);
        currentPanel = homePanel;
        ((HomePanel) homePanel).updateScreenMessage();
    }

    /**
     * Switches the current panel to the calendar panel.
     * Hides the current panel and shows the calendar panel.
     *
     * @author Oleksandr Dacnehnko
     */
    public void switchToCalendar() {
        currentPanel.setVisible(false);
        calendarPanel.setVisible(true);
        currentPanel = calendarPanel;
    }

    /**
     * Switches the current panel to the search panel.
     * Hides the current panel and shows the search panel.
     *
     * @author Oleksandr Dacnehnko
     */
    public void switchToSearch() {
        currentPanel.setVisible(false);
        searchPanel.setVisible(true);
        currentPanel = searchPanel;
    }

    /**
     * Switches the current panel to the flight list panel.
     * Hides the current panel and shows the flight list panel.
     *
     * @param flightList the list of flights to chose from in the panel.
     * @author Oleksandr Dacnehnko
     */
    public void switchToFlightList(List<FlightInfo> flightList) {
        currentPanel.setVisible(false);
        ((FlightListPanel)flightListPanel).showPanel(flightList);
        currentPanel = flightListPanel;
    }

    /**
     * Switches the current panel to the seat panel.
     * Hides the current panel and shows the seat panel.
     *
     * @param flight the flight for the user to book a seat on.
     * @author Oleksandr Dacnehnko
     */
    public void switchToSeat(Flight flight) {
        currentPanel.setVisible(false);
        ((SeatPanel) seatPanel).makeVisible(flight);
        currentPanel = seatPanel;
    }

    /**
     * Returns to the seat selector panel with its previous state.
     * @author Aidan Baker
     */
    public void switchBackToSeat() {
        currentPanel.setVisible(false);
        seatPanel.setVisible(true);
        currentPanel = seatPanel;
    }

    /**
     * Returns to the flight list panel with its previous state.
     * @author Aidan Baker
     */
    public void switchBackToList() {
        currentPanel.setVisible(false);
        flightListPanel.setVisible(true);
        currentPanel = flightListPanel;
    }

    /**
     * Switches the current panel to the user input panel.
     * Hides the current panel and shows the user input panel.
     *
     * @author Oleksandr Dacnehnko
     */
    public void switchToInput(Flight flight, Seat seat) {
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
    public void switchToExport() {
        currentPanel.setVisible(false);
        exportPanel.setVisible(true);
        currentPanel = exportPanel;
    }
}
