/*
Author: Aidan Baker
time spent: 35 minutes
Date: 19 May 2023
version #1
 */
package gui.panels;

import gui.ApplicationFrame;
import gui.components.CustomPanel;
import logic.data_record.Flight;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Represents a panel for seat selection.
 * Extends the ScreenPanel class.
 *
 * @author Aidan Baker
 */
public class SeatPanel extends ScreenPanel {
    /**
     * The flight to be booked.
     *
     * @see logic.data_record.FlightInfo
     */
    private Flight flight;

    /**
     * The text field to display some basic flight information.
     */
    private final JTextField flightInfo = new JTextField();

    /**
     * The panel that contains the seat buttons.
     */
    private final CustomPanel seatButtonPanel = new CustomPanel(new GridLayout(2, 9));

    /**
     * Constructs a SeatPanel object.
     *
     * @param applicationFrame The application frame.
     * @author Aidan Baker
     */
    public SeatPanel(ApplicationFrame applicationFrame) {
        super(applicationFrame, new GridLayout(3, 1));
        setTitle("Seat Selection");

        setBackButtonVisibility(true);

        CustomPanel upperSection = new CustomPanel(new GridLayout(2, 1));
        CustomPanel optionButtons = new CustomPanel(new GridLayout(1, 2));

        addButton("Export Manifest", "export", optionButtons);
        addButton("Cancel Flight", "cancel", optionButtons);

        upperSection.add(optionButtons);
        upperSection.add(flightInfo);

        setupSeatButtons();
        centerPanel.add(upperSection);
        centerPanel.add(seatButtonPanel);
        flightInfo.setEditable(false);
    }

    /**
     * Sets the visibility of the panel and displays the flight information.
     *
     * @param flight The flight to be displayed.
     * @author Aidan Baker
     */
    public void makeVisible(Flight flight) {
        this.flight = flight;
        flightInfo.setText(flight.getDeparture() + " -> " + flight.getDestination() +
                ", " + flight.getDate() + ", " + flight.getUserDepartureTime());

        flightInfo.setFont(new Font("Arial", Font.BOLD, 36));
        flightInfo.setHorizontalAlignment(JTextField.CENTER);

        setVisible(true);
    }

    /**
     * Sets up the seat buttons and adds them to the seat button panel.
     *
     * @author Aidan Baker
     */
    private void setupSeatButtons() {
        for (int i = 0; i < 10; i++) {
            addButton(String.valueOf(i + 1), String.valueOf(i + 1), seatButtonPanel);
            if (i != 4 && i != 9) seatButtonPanel.add(new JLabel());
        }
    }

    /**
     * Checks if the seat button was pressed.
     *
     * @param e The ActionEvent object.
     * @return True if the seat button was pressed, false otherwise.
     * @author Aidan Baker
     */
    private boolean isSeatButtonPressed(ActionEvent e) {
        try {
            return Integer.parseInt(e.getActionCommand()) >= 1 && Integer.parseInt(e.getActionCommand()) <= 10;
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * Performs actions based on the user's interaction with the buttons.
     *
     * @param e The ActionEvent object.
     * @author Aidan Baker
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);

        if (e.getActionCommand().equals("back")) {
            applicationFrame.switchBackToList();
        } else if (e.getActionCommand().equals("export")) {
            applicationFrame.switchToExport();
        }

        if (isSeatButtonPressed(e)) {
            applicationFrame.switchToInput(flight, flight.getSeating()[Integer.parseInt(e.getActionCommand()) - 1]);
        }
    }
}

