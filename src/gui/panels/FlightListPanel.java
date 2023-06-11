/*
Author: Aidan Baker, Oleksandr Danchenko
time spent: 55 minutes
Date: 19 May 2023
version #3
Changes: Added the way to sort data in the table - buttons to sort data using different criteria and to cancel or renew flights.
        time spent: 50 minutes
        Date: 23 May 2023
Changes: implemented the getTitle() and makeVisible() methods added to the ScreenPanel in the process of cleaning up the code.
    time spent: 5 minutes
    Date 1 June 2023
    Author: Oleksandr Danchenko
*/

package gui.panels;

import gui.components.CustomButton;
import resource.DataReader;
import resource.DataWriter;
import gui.ApplicationFrame;
import gui.components.CustomPanel;
import logic.records.Calendar;
import logic.records.Flight;
import logic.records.FlightInfo;
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
 */
public class FlightListPanel extends ScreenPanel {
    /**
     * An array of columns in the table.
     */
    private final String[] columnNames = {"Origin", "Destination", "Date", "Time (EST)", "Flight Duration", "# Seats Available", "Status"};
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
    private final CustomPanel tablePanel = new CustomPanel(new GridLayout(1, 1));
    /**
     * The calendar of flights that month.
     */
    private final Calendar calendar;

    /**
     * Constructs a FlightListPanel object with the specified ApplicationFrame reference.
     *
     * @param applicationFrame the ApplicationFrame object.
     * @param calendar        the calendar of flights that month.
     * @author Aidan Baker
     */
    public FlightListPanel(ApplicationFrame applicationFrame, Calendar calendar) {
        super(applicationFrame, BoxLayout.Y_AXIS);
        this.calendar = calendar;

        CustomPanel actionButtons = new CustomPanel(new GridLayout(1, 4));
        actionButtons.add(new CustomButton("Book Flight", "bookFlight", this));
        actionButtons.add(new CustomButton("View Manifest", "viewManifest", this));
        actionButtons.add(new CustomButton("Cancel the Flight", "cancel", this));
        actionButtons.add(new CustomButton("Renew the flight", "renew", this));

        CustomPanel sortingButtons = new CustomPanel(new GridLayout(1, 5));
        sortingButtons.add(new CustomButton("Sort by departure", "sortDeparture", this));
        sortingButtons.add(new CustomButton("Sort by destination", "sortDestination", this));
        sortingButtons.add(new CustomButton("Sort by date and time", "sortDate", this));
        sortingButtons.add(new CustomButton("Sort by remaining seats", "sortSeats", this));
        sortingButtons.add(new CustomButton("Sort by status", "sortStatus", this));

        add(actionButtons);
        add(sortingButtons);

        setupTable();
        add(tablePanel);
    }

    /**
     * Displays the flight information in the panel.
     *
     * @param flightList the list of FlightInfo objects to display.
     * @author Oleksandr Danchenko
     */
    public void makeVisible(List<FlightInfo> flightList) {
        super.makeVisible();
        fillTable(flightList);
        applicationFrame.setBackButtonVisibility(false);
    }

    /**
     * Empties the table from the previously stored data and fills it with the new data.
     *
     * @citations: <a href="https://docs.oracle.com/javase/8/docs/api/javax/swing/JTable.html">JTable Documentation</a>
     *      Used for displaying the list of flights in a table.
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
            contents[6] = flightInfo.getStatus();
            model.addRow(contents);
        }
    }

    /**
     * Sets up the table for displaying flight information.
     * @citation: <a href="https://docs.oracle.com/javase/8/docs/api/javax/swing/JTable.html">JTable Documentation</a>
     *      Used for setting up the JTable being used to display the list of flights
     * @citation: <a href="https://docs.oracle.com/javase/8/docs/api/javax/swing/JTable.html#setDefaultEditor-java.lang.Class-javax.swing.table.TableCellEditor-">JTable (setDefaultEditor method) Documentation</a>
     *      Used to disable editing of the data in the table.
     * @citation: <a href="https://docs.oracle.com/javase/8/docs/api/javax/swing/JTable.html#setSelectionMode-int-">setSelectionMode method documentation</a>
     *     Used to make it so that only one row can be selected at a time.
     * @citation: <a href="https://docs.oracle.com/javase/8/docs/api/javax/swing/table/JTableHeader.html">JTableHeader Documentation</a>
     *      Used for modifying properties of the table header, such as its font and background color.
     * @citation: <a href="https://docs.oracle.com/javase/8/docs/api/javax/swing/JScrollPane.html">JScrollPane Documentation</a>
     *      Used for making it so the user can scroll through the table. This is necessary because the table may contain more rows of data than can fit on the screen.
     * @author Aidan Baker
     */
    public void setupTable() {
        table.setDefaultEditor(Object.class, null);
        table.setGridColor(Color.BLACK);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font(Font.SERIF, Font.BOLD, 25));
        header.setBackground(Color.lightGray);

        table.setFont(new Font(Font.SERIF, Font.PLAIN, 18));
        table.setRowHeight(30);

        JScrollPane scrollPane = new JScrollPane(table);

        tablePanel.add(scrollPane);
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
     * A getter method for the current flight list.
     *
     * @return the current flight list.
     * @author Aidan Baker
     */
    public List<FlightInfo> getFlightList() {
        return flightList;
    }

    /**
     * An action to be performed after a cancel or renew button was pressed.
     *
     * @param toCancel an indicator that the cancel button is the one pressed.
     * @author Oleksandr Danchenko
     */
    private void cancellationEvent(boolean toCancel) {
        if (getSelectedFlight() != null) {
            if (toCancel == getSelectedFlight().isCancelled()) showErrorMessage("The action could not be performed");
            else {
                if (userConfirm("Are you sure you want to do the action?")) {
                    if (toCancel) getSelectedFlight().cancel();
                    else flightList.get(table.getSelectedRow()).renew();
                    DataWriter.updateFlightList(calendar);
                    fillTable(flightList);
                    showSuccessMessage("Action completed successfully");
                }
            }
        }
    }

    /**
     * Gets the flight that is currently selected in the table.
     *
     * @citation: <a href="https://stackoverflow.com/questions/12546320/get-selected-row-in-jtable">get selected row in JTable</a>
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
        return command.equals("renew") || command.equals("cancel") || command.equals("bookFlight") || command.equals("viewManifest");
    }

    /**
     * Returns the title of the screen.
     *
     * @return "Flight list".
     * @author Oleksandr Danchenko
     */
    @Override
    public String getTitle() {
        return "Flight list";
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
            if(flight != null) applicationFrame.switchToSeat(new Flight(flight, DataReader.getSeating(flight.getFileName())));
        }
        if (e.getActionCommand().equals("viewManifest")) {
            FlightInfo flight = getSelectedFlight();
            if(flight != null) applicationFrame.switchToExport(new Flight(flight, DataReader.getSeating(flight.getFileName())));
        }
    }
}

