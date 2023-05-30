/*
Author: Aidan Baker
time spent: 5 minutes
Date: 23 May 2023
Changes: Added the functionality to the panel, so you can actually see the manifest and sort it.
        Date: 26 May 2023
        Time: 30 minutes
        Author: Aidan Baker
version #2
 */

package gui.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import gui.ApplicationFrame;
import gui.components.CustomPanel;
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

    public JPanel previousPanel = new JPanel();

    /**
     * Constructs an ExportPanel object.
     *
     * @param applicationFrame The application frame.
     * @author Aidan Baker
     */
    public ExportPanel(ApplicationFrame applicationFrame) {
        super(applicationFrame, BoxLayout.Y_AXIS);
        setTitle("Flight Manifest");

        setBackButtonVisibility(true);

        CustomPanel optionButtons = new CustomPanel(BoxLayout.X_AXIS);
        addButton("Sort by Name", "sortName", optionButtons);
        addButton("Sort by Seat #", "sortSeat", optionButtons);
        centerPanel.add(optionButtons);

        for (int i = 0; i < customerInfoFields.length; i++) {
            customerInfoFields[i] = new JTextField();
            customerInfoFields[i].setEditable(false);
            customerInfoFields[i].setPreferredSize(new Dimension(1000, 40));
            centerPanel.add(customerInfoFields[i]);
        }
    }

    /**
     * Loads the flight manifest information into the text areas.
     *
     * @author Aidan Baker
     */
    private void loadManifest(Seat[] seats) {
        for (int i = 0; i < customerInfoFields.length; i++) {
            if (seats[i].getPassenger() != null)
                customerInfoFields[i].setText(seats[i].toString());
            else
                customerInfoFields[i].setText("Seat #" + seats[i].getNumber() + ", Empty");
        }
    }

    /**
     * Makes the panel visible.
     *
     * @param seats The seats on the flight of the manifest to be displayed.
     * @author Aidan Baker
     */
    public void makeVisible(Seat[] seats) {
        this.seats = seats;
        loadManifest(seats);
        setVisible(true);
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
