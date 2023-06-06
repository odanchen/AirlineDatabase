/*
Author: Oleksandr Danchenko
time spent: 70 minutes
Date: 22 May 2023
version #2
Changes: implemented the getTitle() and makeVisible() methods added to the ScreenPanel in the process of cleaning up the code.
    time spent: 5 minutes
    Date 1 June 2023
    Author: Oleksandr Danchenko
*/

package gui.panels;

import resource.DataWriter;
import gui.ApplicationFrame;
import gui.components.CustomButton;
import gui.components.CustomPanel;
import logic.data_checking.*;
import logic.data_record.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * The UserInputPanel class represents a panel for entering customer information and booking or updating a seat reservation.
 * It extends the ScreenPanel class.
 */
public class UserInputPanel extends ScreenPanel {
    /**
     * The calendar of flights.
     */
    private final Calendar calendar;
    /**
     * The seat to be booked.
     */
    private Seat seat;
    /**
     * The flight on which the seat is being booked
     */
    private Flight flight;
    /**
     * A text field for the first name of the user.
     */
    private final JTextField firstNameField = new JTextField();
    /**
     * A text field for the error message for the first name of the user.
     */
    private final JTextField firstNameErrorField = new JTextField();
    /**
     * A text field for the last name of the user.
     */
    private final JTextField lastNameField = new JTextField();
    /**
     * A text field for the error message for the last name of the user.
     */
    private final JTextField lastNameErrorField = new JTextField();
    /**
     * A text field for the phone number of the user.
     */
    private final JTextField phoneNumberField = new JTextField();
    /**
     * A text field for the error message for the phone number of the user.
     */
    private final JTextField phoneNumberErrorField = new JTextField();
    /**
     * A text field for the email of the user.
     */
    private final JTextField emailField = new JTextField();
    /**
     * A text field for the error message for the email of the user.
     */
    private final JTextField emailErrorField = new JTextField();
    /**
     * A text field for the date of birth of the user.
     */
    private final JTextField dateOfBirthField = new JTextField();
    /**
     * A text field for the error message for the date of birth of the user.
     */
    private final JTextField dateOfBirthErrorField = new JTextField();
    /**
     * A text field for the price of the seat.
     */
    private final JTextField priceField = new JTextField();
    /**
     * The button, pressing which the seat would be booked.
     */
    private CustomButton bookButton;
    /**
     * The button, pressing which the booking of the seat would be cancelled.
     */
    private CustomButton cancelButton;

    /**
     * Constructs a UserInputPanel object with the specified application frame and calendar.
     *
     * @param applicationFrame The application frame
     * @param calendar         The calendar
     * @author Oleksandr Danchenko
     */
    public UserInputPanel(ApplicationFrame applicationFrame, Calendar calendar) {
        super(applicationFrame, new GridLayout(6, 1));
        this.calendar = calendar;
        addInputSection("Enter your first name", firstNameField, firstNameErrorField);
        addInputSection("Enter your last name", lastNameField, lastNameErrorField);
        addInputSection("Enter your phone number in one of the following formats - " +
                "\"XXXXXXXXXX\", \"XXX XXX XXXX\", \"XXX-XXX-XXXX\"", phoneNumberField, phoneNumberErrorField);
        addInputSection("Enter your email", emailField, emailErrorField);
        addInputSection("Enter your date of birth in the following format = \"dd/mm/yyyy\"", dateOfBirthField, dateOfBirthErrorField);
        addPricePanel();
    }

    /**
     * Adds an input section to the panel with the specified title, input field, and error field.
     *
     * @param title      The title of the input section
     * @param inputField The input field
     * @param errorField The error field
     * @author Oleksandr Danchenko
     */
    private void addInputSection(String title, JTextField inputField, JTextField errorField) {
        CustomPanel holdPanel = new CustomPanel(new GridLayout(2, 1));
        CustomPanel fieldPanel = new CustomPanel(new GridLayout(1, 2));
        holdPanel.add(new JLabel(title));
        errorField.setEditable(false);
        fieldPanel.add(inputField);
        fieldPanel.add(errorField);
        holdPanel.add(fieldPanel);
        add(holdPanel);
    }

    /**
     * Adds the price panel to the panel.
     *
     * @author Oleksandr Danchenko
     */
    private void addPricePanel() {
        priceField.setEditable(false);
        CustomPanel pricePanel = new CustomPanel(new GridLayout(1, 2));

        CustomPanel infoPanel = new CustomPanel(new GridLayout(1, 2));
        infoPanel.add(new JLabel("The price of the seat: "));
        infoPanel.add(priceField);
        pricePanel.add(infoPanel);

        CustomPanel buttonPanel = new CustomPanel(new GridLayout(1, 2));
        buttonPanel.add(cancelButton = new CustomButton("Cancel booking", "cancel", this));
        buttonPanel.add(bookButton = new CustomButton("Book a Seat", "book", this));
        pricePanel.add(buttonPanel);
        add(pricePanel);
    }

    /**
     * Checks the data entered for validity.
     *
     * @return true if the data is valid, false otherwise
     * @author Oleksandr Danchenko
     */
    private boolean checkData() {
        boolean ans = checkPieceOfData(new EmailChecker(emailField.getText()), emailErrorField);
        if (!checkPieceOfData(new PhoneNumberChecker(phoneNumberField.getText()), phoneNumberErrorField)) ans = false;
        if (!checkPieceOfData(new DateChecker(dateOfBirthField.getText()), dateOfBirthErrorField)) ans = false;
        if (!checkPieceOfData(new NameChecker(firstNameField.getText()), firstNameErrorField)) ans = false;
        if (!checkPieceOfData(new NameChecker(lastNameField.getText()), lastNameErrorField)) ans = false;
        return ans;
    }

    /**
     * Checks a piece of data entered for validity using the provided data checker.
     * If the data is invalid, displays the error message in the error field.
     *
     * @param checker    The data checker
     * @param errorField The error field
     * @return true if the data is valid, false otherwise
     * @author Oleksandr Danchenko
     */
    private boolean checkPieceOfData(DataChecker checker, JTextField errorField) {
        if (!checker.isCorrect()) {
            errorField.setText(checker.getErrorMessage());
            return false;
        } else {
            errorField.setText("");
            return true;
        }
    }

    /**
     * Makes the panel visible and sets the flight and seat for booking or updating.
     *
     * @param flight The flight
     * @param seat   The seat
     * @author Oleksandr Danchenko
     */
    public void makeVisible(Flight flight, Seat seat) {
        super.makeVisible();
        this.flight = flight;
        this.seat = seat;
        loadData();
        cancelButton.setVisible(!seat.isEmpty());
        if (seat.getPrice() % 100 < 10) priceField.setText(seat.getPrice() / 100 + ".0" + seat.getPrice() % 100 + "$");
        else priceField.setText(seat.getPrice() / 100 + "." + seat.getPrice() % 100 + "$");
        applicationFrame.setBackButtonVisibility(true);

        if (flight.getFlightInfo().isCancelled()) { bookButton.setColor(Color.LIGHT_GRAY); cancelButton.setColor(Color.LIGHT_GRAY); }
        else { bookButton.setColor(CustomButton.BUTTON_BLUE); cancelButton.setColor(CustomButton.BUTTON_BLUE); }
    }

    /**
     * Loads the data of the seat and passenger into the input fields.
     *
     * @author Oleksandr Danchenko
     */
    private void loadData() {
        if (seat.isEmpty()) {
            bookButton.setText("Book the seat");

            firstNameField.setText("");
            lastNameField.setText("");
            dateOfBirthField.setText("");
            emailField.setText("");
            phoneNumberField.setText("");
        } else {
            bookButton.setText("Update Reservation");

            firstNameField.setText(seat.getPassenger().getFirstName());
            lastNameField.setText(seat.getPassenger().getLastName());
            dateOfBirthField.setText(seat.getPassenger().getDateOfBirth().data());
            emailField.setText(seat.getPassenger().getEmail());
            phoneNumberField.setText(seat.getPassenger().getPhoneNumber());
        }
        firstNameErrorField.setText("");
        lastNameErrorField.setText("");
        dateOfBirthErrorField.setText("");
        phoneNumberErrorField.setText("");
        emailErrorField.setText("");
    }

    /**
     * Retrieves the entered passenger information from the input fields.
     *
     * @return The entered passenger information.
     * @author Oleksandr Danchenko
     */
    private Person getEnteredPassengerInfo() {
        return new Person(firstNameField.getText(), lastNameField.getText(),
                new Date(dateOfBirthField.getText()), phoneNumberField.getText(), emailField.getText());
    }

    /**
     * Handles the book event when the book button is clicked.
     *
     * @author Oleksandr Danchenko
     */
    private void bookEvent() {
        if (!checkData()) {
            showErrorMessage("The provided input contains errors");
            return;
        }

        String[] messages = new String[2];
        if (seat.isEmpty()) {
            messages[0] = "Please confirm booking of the seat";
            messages[1] = "Seat booked successfully!";
        } else {
            messages[0] = "Please confirm update of the reservation";
            messages[1] = "Reservation updated successfully!";
        }

        if (userConfirm(messages[0])) {
            flight.bookSeat(seat.getNumber(), getEnteredPassengerInfo());
            DataWriter.updateSeatingInformation(flight.getSeating(), flight.getFilename());
            DataWriter.updateFlightList(calendar);
            showSuccessMessage(messages[1]);
            applicationFrame.switchToSeat(flight);
        }
    }

    /**
     * Returns the title of the screen.
     *
     * @return "Enter your information"
     * @author Oleksandr Danchenko
     */
    @Override
    public String getTitle() {
        return "Enter your Information";
    }

    /**
     * The method that is executed when a button is pressed.
     *
     * @param e the event to be processed
     * @author Oleksandr Danchenko
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        if (e.getActionCommand().equals("back")) applicationFrame.switchBackToSeat();
        if (e.getActionCommand().equals("cancel") && !flight.getFlightInfo().isCancelled()) {
            if (userConfirm("Are you sure you want to cancel the booking")) {
                flight.cancelSeat(seat.getNumber());
                DataWriter.updateSeatingInformation(flight.getSeating(), flight.getFilename());
                DataWriter.updateFlightList(calendar);
                applicationFrame.switchToSeat(flight);
            }
        }
        if (e.getActionCommand().equals("book") && !flight.getFlightInfo().isCancelled()) bookEvent();
    }
}
