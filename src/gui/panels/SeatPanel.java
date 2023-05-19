/*
Author: Aidan Baker
time spent: 10 minutes
version #1
 */
package gui.panels;

import gui.ApplicationFrame;
import gui.graphics.PlaneImage;
import logic.data_record.FlightInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SeatPanel extends CustomPanel {
    /**
     * The flight to be booked.
     * @see logic.data_record.FlightInfo
     */
    private FlightInfo flight;

    /**
     * The button to export the manifest.
     */
    private JButton exportManifestButton;

    /**
     * the background image
     */
    private PlaneImage planeImage;

    /**
     * The button to go back to the flight list.
     */
    private JButton backButton;

    /**
     * The text field to display some basic flight information.
     */
    private JTextField flightInfo;

    /**
     * The buttons for selecting seats.
     */
    private JButton[] seats = new JButton[10];

    /**
     * The panel that contains the seat buttons.
     */
    private JPanel seatButtonPanel;

    public SeatPanel(ApplicationFrame applicationFrame) {
        super(applicationFrame);
        centerPanel.setLayout(new GridLayout(3, 1));
        setTitle("Seat Selection");

        flightInfo = new JTextField();
        flightInfo.setText("Toronto -> Ottawa\n" +
                "Date: 2021-03-20\n" +
                "Time: 12:00\n");

        centerPanel.add(flightInfo);
        setupSeatButtons();
        centerPanel.add(seatButtonPanel);
    }

    private void setupSeatButtons() {
        seatButtonPanel = new JPanel();
        seatButtonPanel.setLayout(new GridLayout(2, 10));

        for (int i = 0; i < seats.length; i++) {
            seats[i] = new JButton(Integer.toString(i + 1));
            seats[i].setActionCommand(Integer.toString(i + 1));
            seats[i].addActionListener(this);
            seatButtonPanel.add(seats[i]);
            seatButtonPanel.add(new JLabel());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);

        //todo
    }
}
