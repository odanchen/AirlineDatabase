/*
Author: Aidan Baker
time spent: 5 minutes
Date: 23 May 2023
version #3
Changes: Added the functionality to the panel, so you can actually see the manifest and sort it.
        Date: 26 May 2023
        Time: 30 minutes
        Author: Aidan Baker
Changes: implemented the getTitle() and makeVisible() methods added to the ScreenPanel in the process of cleaning up the code.
    time spent: 5 minutes
    Date 1 June 2023
    Author: Oleksandr Danchenko
 */

package gui.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import gui.ApplicationFrame;
import gui.components.CustomButton;
import gui.components.CustomPanel;
import logic.data_record.Flight;
import logic.data_record.Seat;
import logic.sorting.seats.SeatSorter;
import logic.sorting.seats.SortByName;
import logic.sorting.seats.SortByNumber;

public class ExportPanel extends ScreenPanel {
    /**
     * The information for each customer on the flight.
     */
    private Seat[] seats;

    /**
     * The text area to display the flight manifest.
     */
    private final JTextField[] customerInfoFields = new JTextField[10];

    /**
     * the panel that store the previous panel, so you can return to it
     */
    public JPanel previousPanel = new JPanel();

    /**
     * `The text area to display the flight information.
     */
    JTextField flightInfo = new JTextField();

    /**
     * Constructs an ExportPanel object.
     *
     * @param applicationFrame The application frame.
     * @author Aidan Baker
     */
    public ExportPanel(ApplicationFrame applicationFrame) {
        super(applicationFrame, BoxLayout.Y_AXIS);

        flightInfo.setEditable(false);
        flightInfo.setPreferredSize(new Dimension(1000, 60));
        flightInfo.setHorizontalAlignment(JTextField.CENTER);
        flightInfo.setFont(new Font("Arial", Font.BOLD, 24));
        flightInfo.setBackground(CustomPanel.BACKGROUND_WHITE);
        flightInfo.setBorder(null);
        add(flightInfo);

        CustomPanel optionButtons = new CustomPanel(BoxLayout.X_AXIS);
        optionButtons.add(new CustomButton("Sort by Name", "sortName", this));
        optionButtons.add(new CustomButton("Sort by seat #", "sortSeat", this));
        add(optionButtons);

        for (int i = 0; i < customerInfoFields.length; i++) {
            customerInfoFields[i] = new JTextField();
            customerInfoFields[i].setEditable(false);
            customerInfoFields[i].setPreferredSize(new Dimension(1000, 40));
            customerInfoFields[i].setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
            add(customerInfoFields[i]);
        }
    }

    /**
     * Loads the flight manifest information into the text areas.
     *
     * @author Aidan Baker
     */
    private void loadManifest(Seat[] seats) {
        for (int i = 0; i < customerInfoFields.length; i++) {
            customerInfoFields[i].setText(seats[i].toString());
        }
    }

    /**
     * Makes the panel visible.
     *
     * @param flight The flight you want to display the manifest for.
     * @author Aidan Baker, Oleksandr Danchenko
     */
    public void makeVisible(Flight flight, ScreenPanel previousPanel) {
        super.makeVisible();
        this.seats = flight.getSeating();
        this.previousPanel = previousPanel;
        flightInfo.setText(flight.getDeparture() + " → " + flight.getDestination() +
                ", " + flight.getDate() + ", " + flight.getUserDepartureTime());
        applicationFrame.setBackButtonVisibility(true);
        loadManifest(seats);
    }

    /**
     * Returns the title of the screen.
     *
     * @return "Flight Manifest".
     * @author Oleksandr Danchenko
     */
    @Override
    public String getTitle() {
        return "Flight Manifest";
    }

    /**
     * Processes the action events.
     *
     * @param e the event to be processed
     * @author Aidan Baker
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);

        //back button
        if (e.getActionCommand().equals("back")) {
            applicationFrame.switchBackTo(previousPanel);
        } else

        //sorting buttons
        if (e.getActionCommand().equals("sortName")) loadManifest(SeatSorter.sort(seats, new SortByName()));
        else if (e.getActionCommand().equals("sortSeat")) loadManifest(SeatSorter.sort(seats, new SortByNumber()));
    }
}
