/*
Author: Aidan Baker
time spent: 10 minutes
version #1
 */
package gui.panels;

import gui.ApplicationFrame;
import gui.graphics.PlaneImage;
import logic.data_record.Flight;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SeatPanel extends CustomPanel {
    /**
     * The flight to be booked.
     *
     * @see logic.data_record.FlightInfo
     */
    private Flight flight;

    /**
     * The button to export the manifest.
     */
    private JButton exportManifestButton;

    /**
     * The button to cancel the flight.
     */
    private JButton cancelFlightButton;

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
    private final JTextField flightInfo = new JTextField();
    /**
     * The panel that contains the seat buttons.
     */
    private JPanel seatButtonPanel;

    public SeatPanel(ApplicationFrame applicationFrame) {
        super(applicationFrame);
        centerPanel.setLayout(new GridLayout(3, 1));
        setTitle("Seat Selection");

        setBackButtonVisibility(true);

        JPanel upperSection = new JPanel();
        JPanel optionButtons = new JPanel();
        optionButtons.setLayout(new GridLayout(1, 2));

        addButton("Export Manifest", "export", optionButtons);
        addButton("Cancel Flight", "cancel", optionButtons);


        optionButtons.add(exportManifestButton);
        optionButtons.add(cancelFlightButton);

        upperSection.setLayout(new GridLayout(2, 1));

        upperSection.add(optionButtons);
        upperSection.add(flightInfo);

        setupSeatButtons();
        centerPanel.add(upperSection);
        centerPanel.add(seatButtonPanel);
        flightInfo.setEditable(false);
    }

    public void makeVisible(Flight flight) {
        this.flight = flight;
        flightInfo.setText(flight.getDeparture() + " -> " + flight.getDestination() +
                ", " + flight.getDate() + ", " + flight.getUserDepartureTime());

        flightInfo.setFont(new Font("Arial", Font.BOLD, 36));
        flightInfo.setHorizontalAlignment(JTextField.CENTER);

        setVisible(true);
    }

    private void setupSeatButtons() {
        seatButtonPanel = new JPanel();
        seatButtonPanel.setLayout(new GridLayout(2, 9));

        for (int i = 0; i < 10; i++) {
            JButton button = new JButton(Integer.toString(i + 1));
            button.setActionCommand(Integer.toString(i + 1));
            button.addActionListener(this);
            seatButtonPanel.add(button);

            if (i != 4 && i != 9)
                seatButtonPanel.add(new JLabel());
        }
    }

    private boolean isSeatButtonPressed(ActionEvent e) {
        try {
            return Integer.parseInt(e.getActionCommand()) >= 1 && Integer.parseInt(e.getActionCommand()) <= 10;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);

        if(e.getActionCommand().equals("back")) {
            applicationFrame.switchBackToList();
        } else if (e.getActionCommand().equals("export")) {
            applicationFrame.switchToExport();
        }

        if (isSeatButtonPressed(e)) {
            applicationFrame.switchToInput(flight, flight.getSeating()[Integer.parseInt(e.getActionCommand()) - 1]);
        }
    }
}
