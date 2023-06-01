/*
Author: Aidan Baker
time spent: 35 minutes
Date: 19 May 2023
version #1.5
changes: Added a method to color the seat buttons based on their availability
        Date: 28 May 2023
        time spent: 5 minutes
        Author: Aidan Baker
 */
package gui.panels;

import gui.ApplicationFrame;
import gui.components.CustomButton;
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
     * The array of seat buttons.
     */
    private final CustomButton[] seatButtons = new CustomButton[10];

    /**
     * Constructs a SeatPanel object.
     *
     * @param applicationFrame The application frame.
     * @author Aidan Baker
     */
    public SeatPanel(ApplicationFrame applicationFrame) {
        super(applicationFrame, new GridLayout(3, 1));
        applicationFrame.setTitle("Seat Selection");

        //setBackButtonVisibility(true);

        CustomPanel upperSection = new CustomPanel(new GridLayout(2, 1));
        CustomPanel optionButtons = new CustomPanel(new GridLayout(1, 2));

        optionButtons.add(new CustomButton("View Manifest", "export", this));
        optionButtons.add(new CustomButton("Cancel Flight", "cacnel", this));

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

        colorSeatButtons();

        setVisible(true);
    }

    /**
     * Sets up the seat buttons and adds them to the seat button panel.
     *
     * @author Aidan Baker
     */
    private void setupSeatButtons() {
        for (int i = 0; i < 10; i++) {
            seatButtons[i] = new CustomButton(String.valueOf(i + 1), String.valueOf(i + 1), this);
            seatButtonPanel.add(seatButtons[i]);

            if (i != 4 && i != 9) seatButtonPanel.add(new JLabel());
        }
    }

    /**
     * Colors the seat buttons based on whether they are booked.
     * If the seat is booked, the button is colored light gray otherwise it is colored blue.
     *
     * @author Aidan Baker
     */
    private void colorSeatButtons() {
        for (int i = 0; i < 10; i++) {
            if (!((flight.getSeating())[i].isEmpty())) {
                seatButtons[i].setColor(Color.LIGHT_GRAY);
            } else {
                seatButtons[i].setColor(CustomButton.BUTTON_BLUE);
            }
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
            applicationFrame.switchToExport(flight.getSeating());
        }

        else if (isSeatButtonPressed(e)) {
            applicationFrame.switchToInput(flight, flight.getSeating()[Integer.parseInt(e.getActionCommand()) - 1]);
        }

        else if (e.getActionCommand().equals("cancel")) {
            if (JOptionPane.showConfirmDialog(this, "Are you sure you want to cancel this flight?",
                    "Cancel Flight", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                flight.getFlightInfo().cancel();
                JOptionPane.showMessageDialog(this, "Flight cancelled successfully.",
                        "Flight Cancelled", JOptionPane.INFORMATION_MESSAGE);
                applicationFrame.switchBackToList();
            }
        }
    }
}

