/*
Author: Aidan Baker, Oleksandr Danchenko
time spent: 55 minutes
Date: 19 May 2023
version #2
Changes: Added the way to sort data in the table - buttons to sort data using different criteria and to cancel or renew flights.
        time spent: 50 minutes
        Date: 23 May 2023
*/
package gui.panels;

import database.interaction.DataReader;
import database.interaction.DataWriter;
import gui.ApplicationFrame;
import logic.data_record.Calendar;
import logic.data_record.Flight;
import logic.data_record.FlightInfo;
import logic.sorting.flights.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

/**
 * The FlightListPanel class, displays the list of flights.
 *
 * @author Aidan Baker, Oleksandr Danchenko
 * @see gui.panels.CustomPanel
 */
public class FlightListPanel extends CustomPanel {
    /**
     * An array of columns in the table.
     */
    private final String[] columnNames = {"Origin", "Destination", "Date", "Time (est)", "Flight time", "# Seats Available", "Status"};
    /**
     * The data model of the table.
     */
    private final DefaultTableModel model = new DefaultTableModel(new Object[0][], columnNames);
    /**
     * The table of flights.
     */
    private final JTable table = new JTable(model);
    /**
     * The current list of flights.
     */
    private List<FlightInfo> flightList;
    /**
     * The panel that stores the table
     */
    private JPanel tablePanel;
    /**
     * The calendar of flights that month.
     */
    private final Calendar calendar;

    /**
     * Constructs a FlightListPanel object with the specified ApplicationFrame reference.
     *
     * @param applicationFrame the ApplicationFrame object.
     * @author Aidan Baker
     */
    public FlightListPanel(ApplicationFrame applicationFrame, Calendar calendar) {
        super(applicationFrame);
        this.calendar = calendar;
        setTitle("Flights");

        JPanel actionButtons = new JPanel();
        actionButtons.setLayout(new GridLayout(1, 3));
        addButton("Book Flight", "bookFlight", actionButtons);
        addButton("Cancel the flight", "cancel", actionButtons);
        addButton("Renew the flight", "renew", actionButtons);

        JPanel sortingButtons = new JPanel();
        sortingButtons.setLayout(new GridLayout(1, 5));

        addButton("Sort by remaining seats", "sortSeats", sortingButtons);
        addButton("Sort by status", "sortStatus", sortingButtons);
        addButton("Sort by departure", "sortDeparture", sortingButtons);
        addButton("Sort by destination", "sortDestination", sortingButtons);
        addButton("Sort by date and time", "sortDate", sortingButtons);

        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.add(actionButtons);
        centerPanel.add(sortingButtons);

        setupTable();
        centerPanel.add(tablePanel);
    }

    /**
     * Displays the flight information in the panel.
     *
     * @param flightList the list of FlightInfo objects to display.
     * @author Oleksandr Danchenko
     */
    public void showPanel(List<FlightInfo> flightList) {
        fillTable(flightList);
        setVisible(true);
    }

    /**
     * Empties the table from the previously stored data and fills it with the new.
     *
     * @param flightList the new list of data.
     * @author Oleksandr Danchenko
     */
    private void fillTable(List<FlightInfo> flightList) {
        this.flightList = flightList;
        for (int row = table.getRowCount() - 1; row >= 0; row--)
            model.removeRow(row);
        for (FlightInfo flightInfo : flightList) {
            Object[] contents = new Object[columnNames.length];
            contents[0] = flightInfo.getDeparture();
            contents[1] = flightInfo.getDestination();
            contents[2] = flightInfo.getDate();
            contents[3] = flightInfo.getUserDepartureTime();
            contents[4] = interpretFlightTime(flightInfo.getFlightTime());
            contents[5] = flightInfo.getSeatsLeft();
            contents[6] = interpretStatus(flightInfo.isCancelled());
            model.addRow(contents);
        }
    }

    /**
     * Sets up the table for displaying flight information.
     *
     * @author Aidan Baker
     */
    public void setupTable() {
        table.setDefaultEditor(Object.class, null);
        table.setGridColor(Color.BLACK);

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Times New Roman", Font.BOLD, 20));
        header.setBackground(Color.lightGray);

        JScrollPane scrollPane = new JScrollPane(table);

        tablePanel = new JPanel();
        tablePanel.setLayout(new GridLayout(1, 1));
        tablePanel.add(scrollPane);
    }

    /**
     * Interprets the flight status and returns a corresponding string representation.
     *
     * @param status the flight status.
     * @return the interpreted status string.
     * @author Oleksandr Danchenko
     */
    private String interpretStatus(boolean status) {
        if (status) return "Cancelled";
        return "Available";
    }

    /**
     * Interprets the flight time and returns a formatted string representation.
     *
     * @param time the flight time in minutes.
     * @return the interpreted flight time string.
     * @author Oleksandr Danchenko
     */
    private String interpretFlightTime(int time) {
        if (time > 60) return time / 60 + " hours " + time % 60 + " minutes";
        return time + " minutes";
    }

    /**
     * Picks the comparator corresponding to the action command.
     *
     * @param command the action command.
     * @return an instance of the comparator corresponding to the message.
     * @author Oleksandr Danchenko
     */
    private FlightComparator getComparator(String command) {
        switch (command) {
            case "sortSeats": return new SortByRemainingSeats();
            case "sortStatus": return new SortByStatus();
            case "sortDeparture": return new SortByDeparture();
            case "sortDestination": return new SortByDestination();
            default: return new SortByDateAndTime();
        }
    }

    /**
     * An action to be performed after a cancel or renew button was pressed.
     *
     * @param toCancel an indicator that the cancel button is the one pressed.
     * @author Oleksandr Danchenko
     */
    private void cancellationEvent(boolean toCancel) {
        if (toCancel == getSelectedFlight().isCancelled()) showErrorMessage("The action could not be performed");
        else {
            if (userConfirm("Are you sure you want to do the action?")) {
                if (toCancel) getSelectedFlight().cancel();
                else flightList.get(table.getSelectedRow()).renew();
                DataWriter.updateFlightList(calendar);
                fillTable(flightList);
            }
        }
    }

    /**
     * Gets the flight that is currently selected in the table.
     *
     * @return the selected instance of the FlightInfo class or null is nothing is selected.
     * @author Oleksandr Danchenko
     */
    private FlightInfo getSelectedFlight() {
        if (table.getSelectedRow() == -1) return null;
        return flightList.get(table.getSelectedRow());
    }

    /**
     * Checks if one of the action buttons is pressed - the action that requires a selected flight
     *
     * @param command the action command of the event.
     * @return true if one of the action buttons is pressed, false - otherwise.
     * @author Oleksandr Danchenko
     */
    private boolean isActionButtonPressed(String command) {
        return command.equals("renew") || command.equals("cancel") || command.equals("bookFlight");
    }

    /**
     * Action to be performed when an event is generated.
     *
     * @param e the event to be processed
     * @author Oleksandr Danchenko
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        if (e.getActionCommand().startsWith("sort")) {
            fillTable(FlightSorter.sort(flightList, getComparator(e.getActionCommand())));
        }
        if (isActionButtonPressed(e.getActionCommand()) && getSelectedFlight() == null) {
            showErrorMessage("No flight selected");
            return;
        }
        if (e.getActionCommand().equals("cancel") || e.getActionCommand().equals("renew")) {
            cancellationEvent(e.getActionCommand().equals("cancel"));
        }
        if (e.getActionCommand().equals("bookFlight")) {
            FlightInfo flight = getSelectedFlight();
            applicationFrame.switchToSeat(new Flight(flight, DataReader.getSeating(flight.getFileName())));
        }
    }
}

