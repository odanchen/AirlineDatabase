/*
Author: Aidan Baker, Oleksandr Danchenko
time spent: 45 minutes
version #1
*/
package gui.panels;

import database.interaction.DataReader;
import gui.ApplicationFrame;
import logic.data_record.Flight;
import logic.data_record.FlightInfo;

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
    private List<FlightInfo> flightList;
    private JPanel tablePanel;

    /**
     * Constructs a FlightListPanel object with the specified ApplicationFrame reference.
     *
     * @param applicationFrame the ApplicationFrame object.
     * @author Aidan Baker
     */
    public FlightListPanel(ApplicationFrame applicationFrame) {
        super(applicationFrame);
        setTitle("Flights");

        JButton bookFlight = new JButton("Book Flight");
        bookFlight.setActionCommand("bookFlight");
        bookFlight.addActionListener(this);

        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.add(bookFlight);

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
        setVisible(true);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);

        if (e.getActionCommand().equals("bookFlight") && table.getSelectedRow() != -1) {
            FlightInfo flight = flightList.get(table.getSelectedRow());
            applicationFrame.switchToSeat(new Flight(flight, DataReader.getSeating(flight.getFileName())));
        } else if (e.getActionCommand().equals("bookFlight")) {
            JOptionPane.showMessageDialog(this, "Please select a flight to book.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

