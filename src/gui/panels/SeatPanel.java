/*
Author: Aidan Baker
time spent: 35 minutes
Date: 19 May 2023
version #5
changes: Added a method to color the seat buttons based on their availability
        Date: 28 May 2023
        time spent: 5 minutes
        Author: Aidan Baker
Changes: implemented the getTitle() and makeVisible() methods added to the ScreenPanel in the process of cleaning up the code.
    time spent: 5 minutes
    Date 1 June 2023
    Author: Oleksandr Danchenko
Changes: reordered the components for a more appealing look.
    time spent: 15 minutes
    Date 2 June 2023
    Author: Oleksandr Danchenko.
Changes: separated the seat buttons in a separate panel with a drawing of a plane on it.
    time spent: 20 minutes
    Date: 3 June 2023
    Author: Oleksandr Danchenko.
 */
package gui.panels;

import gui.ApplicationFrame;
import gui.components.CustomButton;
import gui.components.CustomPanel;
import gui.graphics.SeatButtonPanel;
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
     * The reference to the seat button panel.
     */
    private final SeatButtonPanel seatButtonPanel;

    /**
     * Constructs a SeatPanel object.
     *
     * @param applicationFrame The application frame.
     * @author Aidan Baker
     */
    public SeatPanel(ApplicationFrame applicationFrame) {
        super(applicationFrame, BoxLayout.Y_AXIS);

        CustomPanel upperSection = new CustomPanel(new GridLayout(2, 1));
        CustomPanel optionButtons = new CustomPanel(new GridLayout(1, 4));

        optionButtons.add(new CustomButton("View Manifest", "export", this));
        optionButtons.add(new CustomButton("Cancel Flight", "cancel", this));

        upperSection.add(flightInfo);
        upperSection.add(optionButtons);

        seatButtonPanel = new SeatButtonPanel(this);
        add(upperSection);
        add(seatButtonPanel);
        flightInfo.setEditable(false);
    }

    /**
     * Sets the visibility of the panel and displays the flight information.
     *
     * @param flight The flight to be displayed.
     * @author Aidan Baker
     */
    public void makeVisible(Flight flight) {
        super.makeVisible();
        this.flight = flight;
        flightInfo.setText(flight.getDeparture() + " → " + flight.getDestination() +
                ", " + flight.getDate() + ", " + flight.getUserDepartureTime());
        flightInfo.setBackground(BACKGROUND_WHITE);
        flightInfo.setForeground(CustomButton.BUTTON_BLUE);
        flightInfo.setFont(new Font("Arial", Font.BOLD, 28));
        flightInfo.setHorizontalAlignment(JTextField.CENTER);
        applicationFrame.setBackButtonVisibility(true);
        seatButtonPanel.colorSeatButtons();
    }

    /**
     * Returns the current selected flight.
     *
     * @return the current selected flight.
     * @author Aidan Baker
     */
    public Flight getFlight() {
        return flight;
    }

    /**
     * Returns the title of the screen.
     *
     * @return "Seat Selection"
     * @author Oleksandr Danchenko
     */
    @Override
    public String getTitle() {
        return "Seat Selection";
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
            applicationFrame.switchToExport(flight);
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

