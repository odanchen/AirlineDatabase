/*
Author: Oleksandr Danchenko
time spent: 25 minutes
version #1
 */

package gui;

import javax.swing.*;

import logic.data_record.Calendar;
import database.interaction.DataReader;
import gui.panels.*;
import logic.data_record.FlightInfo;

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
    private final CustomPanel loadingPanel = new LoadingPanel(this);

    /**
     * A reference to the home panel of the application.
     */
    private final CustomPanel homePanel = new HomePanel(this);

    /**
     * A reference to the calendar panel of the application.
     */
    private final CustomPanel calendarPanel;

    /**
     * A reference to the search panel of the application.
     */
    private final CustomPanel searchPanel;

    /**
     * A reference to the flight list panel of the application.
     */
    private final CustomPanel flightListPanel = new FlightListPanel(this);

    /**
     * A reference to the seat panel of the application.
     */
    private final CustomPanel seatPanel = new SeatPanel(this);

    /**
     * A reference to the user input panel of the application.
     */
    private final CustomPanel userInputPanel = new UserInputPanel(this);

    /**
     * A reference to the export panel of the application.
     */
    private final CustomPanel exportPanel = new ExportPanel(this);

    /**
     * A reference to the currently active panel in the application.
     */
    private CustomPanel currentPanel = loadingPanel;

    /**
     * Creates a new instance of ApplicationFrame.
     * Initializes the frame and sets the loading panel as the initial visible panel.
     * Displays the splash screen and performs the initialization process.
     *
     * @author Oleksandr Dacnehnko
     */
    public ApplicationFrame() {
        super();
        getContentPane().setLayout(new BorderLayout());
        JPanel centerPanel = new JPanel();
        Calendar calendar = DataReader.getCalendar();
        calendarPanel = new CalendarPanel(this, calendar);
        searchPanel = new SearchPanel(this, calendar);
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
        setLocationRelativeTo(null);
        loadingPanel.setVisible(true);
        setVisible(true);
        ((LoadingPanel) loadingPanel).showSplashScreen();
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
     * @author Oleksandr Dacnehnko
     */
    public void switchToFlight(List<FlightInfo> flightList) {
        currentPanel.setVisible(false);
        ((FlightListPanel)flightListPanel).showPanel(flightList);
        currentPanel = flightListPanel;
    }

    /**
     * Switches the current panel to the seat panel.
     * Hides the current panel and shows the seat panel.
     *
     * @author Oleksandr Dacnehnko
     */
    public void switchToSeat() {
        currentPanel.setVisible(false);
        seatPanel.setVisible(true);
        currentPanel = seatPanel;
    }

    /**
     * Switches the current panel to the user input panel.
     * Hides the current panel and shows the user input panel.
     *
     * @author Oleksandr Dacnehnko
     */
    public void switchToInput() {
        currentPanel.setVisible(false);
        userInputPanel.setVisible(true);
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
