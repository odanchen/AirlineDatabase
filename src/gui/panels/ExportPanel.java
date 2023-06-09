/*
Author: Aidan Baker
time spent: 5 minutes
Date: 23 May 2023
version #4
Changes: Added the functionality to the panel, so you can actually see the manifest and sort it.
        Date: 26 May 2023
        Time: 30 minutes
        Author: Aidan Baker
Changes: Implemented the getTitle() and makeVisible() methods added to the ScreenPanel in the process of cleaning up the code.
        time spent: 5 minutes
        Date: 1 June 2023
        Author: Oleksandr Danchenko
Changes: Implemented the ability to print and save the manifest.
        time spent: 20 minutes
        Date: 8 June 2023
        Author: Aidan Baker
 */

package gui.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.awt.print.Printable;
import java.awt.print.PrinterJob;

import gui.ApplicationFrame;
import gui.components.CustomButton;
import gui.components.CustomPanel;
import logic.records.Flight;
import logic.records.Person;
import logic.records.Seat;
import logic.sorting.seats.SeatSorter;
import logic.sorting.seats.SortByName;
import logic.sorting.seats.SortByNumber;
import resource.DataReader;

/**
 * The ExportPanel screen that shows the complete information about a flight, including the seating and all the passengers of that flight.
 *
 * @author Aidan Baker
 */
public class ExportPanel extends ScreenPanel {
    /**
     * The information for each customer on the flight.
     */
    private Seat[] seats = new Seat[10];

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
    private final JTextField flightInfo = new JTextField();

    /**
     * Constructs an ExportPanel object.
     *
     * @citation: <a href="https://docs.oracle.com/javase/8/docs/api/javax/swing/JComponent.html#setPreferredSize-java.awt.Dimension-">setPreferredSize method Documentation</a>
     * @Citation: <a href="https://docs.oracle.com/javase/8/docs/api/java/awt/Dimension.html">Dimension class documentation</a>
     *      The setPreferredSize() method is used to specify the preferred size of the component.
     *      Here, it is used to specify the approximate size of the text field components to the layout manager of the container that stores the objects.
     *      The Dimension class is used as a parameter and contains the preferred width and height of the component respectively.
     * @citation: <a href="https://docs.oracle.com/javase/8/docs/api/javax/swing/JComponent.html#setBorder-javax.swing.border.Border-">setBorder method documentations</a>
     *      The setBorder() method is used to get rid of the border painted by the text field for a more appealing look.
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
        optionButtons.add(new CustomButton("Print", "print", this));
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
    private void loadManifest() {
        for (int i = 0; i < customerInfoFields.length; i++) {
            customerInfoFields[i].setText(seats[i].toString());
        }
    }

    /**
     * Makes the panel visible.
     *
     * @param flight The flight you want to display the manifest for.
     * @param previousPanel The panel that you want to return to when you press the back button.
     * @author Aidan Baker, Oleksandr Danchenko
     */
    public void makeVisible(Flight flight, ScreenPanel previousPanel) {
        super.makeVisible();
        for (int i = 0; i < flight.getSeating().length; i++) seats[i] = flight.getSeating()[i];
        this.previousPanel = previousPanel;
        flightInfo.setText(flight.getDeparture() + " → " + flight.getDestination() +
                ", " + flight.getDate() + ", " + flight.getUserDepartureTime());
        applicationFrame.setBackButtonVisibility(true);
        loadManifest();
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
     * @citation: <a href=https://docs.oracle.com/javase/8/docs/api/java/awt/print/PrinterJob.html">PrinterJob documentation</a>
     *     The PrinterJob class is used to print the flight manifest to a printer or save it as a pdf
     *     <br>setPrintable() invokes the painter (an object of Printable) to render the pages for printing.
     *     <br>printDialog() method is used to display a dialog box that allows the user to change the print settings, including the printer they would like it to be printed to, if they would like to save it as a pdf instead of printing, amount of copies to print, etc.
     *     <br>print() method is used to execute the printing based on the options the user chose in the printDialog.
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
        }

        //sorting buttons
        else if (e.getActionCommand().equals("sortName")) {
            seats = SeatSorter.sort(seats, new SortByName());
            loadManifest();
        }
        else if (e.getActionCommand().equals("sortSeat")) {
            seats = SeatSorter.sort(seats, new SortByNumber());
            loadManifest();
        }

        //print button
        else if (e.getActionCommand().equals("print")) {
            PrinterJob job = PrinterJob.getPrinterJob();
            job.setPrintable((graphics, pageFormat, pageIndex) -> {
                if (pageIndex > 0) {
                    return Printable.NO_SUCH_PAGE;
                }

                BufferedImage logo = DataReader.readImage("logo");
                graphics.drawImage(logo, 100, 5, null);

                graphics.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
                graphics.drawString(flightInfo.getText(), 100, 140);

                graphics.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 9));

                int offsetY = 0;

                for (int i = 0; i < customerInfoFields.length; i++) {
                    if (seats[i].isEmpty()) {
                        graphics.drawString(seats[i].toString(), 20, 200 + i * 50 + offsetY);
                        if (i != 0) {
                            offsetY -= 10;
                        }
                    } else {
                        graphics.drawString("Seat #" + seats[i].getNumber() + ", Price: " + seats[i].fixPrice(), 20, 200 + i * 50 + offsetY);
                        Person customer = seats[i].getPassenger();
                        graphics.drawString(customer.getFirstName() + " " + customer.getLastName() + ", DOB: " + customer.getDateOfBirth(), 20, 215 + i * 50 + offsetY);
                        graphics.drawString("Email: " + customer.getEmail() + ", Phone: " + customer.getPhoneNumber(), 20, 230 + i * 50 + offsetY);
                    }

                }
                return Printable.PAGE_EXISTS;
            });

            //make the dialog appear in the center of the screen
            if (job.printDialog()) {
                try {
                    job.print();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
