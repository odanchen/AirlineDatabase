/*
Author: Oleksandr Danchenko
time spent: 40 minutes
Date: 22 May 2023
version #1
*/

package gui.panels;

import database.interaction.DataWriter;
import gui.ApplicationFrame;
import logic.data_checking.*;
import logic.data_record.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class UserInputPanel extends CustomPanel {
    private final Calendar calendar;
    private Seat seat;
    private Flight flight;
    private final JTextField firstNameField = new JTextField();
    private final JTextField firstNameErrorFiled = new JTextField();
    private final JTextField lastNameFiled = new JTextField();
    private final JTextField lastNameErrorField = new JTextField();
    private final JTextField phoneNumberField = new JTextField();
    private final JTextField phoneNumberErrorField = new JTextField();
    private final JTextField emailField = new JTextField();
    private final JTextField emailErrorField = new JTextField();
    private final JTextField dateOfBirthField = new JTextField();
    private final JTextField dateOfBirthErrorField = new JTextField();
    private final JTextField priceField = new JTextField();
    private final JButton bookButton = new JButton("Book the seat");
    private final JButton cancelButton = new JButton("Cancel booking");


    public UserInputPanel(ApplicationFrame applicationFrame, Calendar calendar) {
        super(applicationFrame);
        this.calendar = calendar;
        centerPanel.setLayout(new GridLayout(6, 1));
        addInputSection("Enter your first name", firstNameField, firstNameErrorFiled);
        addInputSection("Enter your last name", lastNameFiled, lastNameErrorField);
        addInputSection("Enter your phone number in one of the following formats - " +
                "\"XXXXXXXXXX\", \"XXX XXX XXXX\", \"XXX-XXX-XXXX\"", phoneNumberField, phoneNumberErrorField);
        addInputSection("Enter your email", emailField, emailErrorField);
        addInputSection("Enter your date of birth in the following format = \"dd/mm/yyyy\"", dateOfBirthField, dateOfBirthErrorField);
        addPricePanel();
    }

    private void addInputSection(String title, JTextField inputField, JTextField errorField) {
        JPanel holdPanel = new JPanel(), fieldPanel = new JPanel();
        holdPanel.setLayout(new GridLayout(2, 1));
        fieldPanel.setLayout(new GridLayout(1, 2));
        holdPanel.add(new JLabel(title));
        errorField.setEditable(false);
        fieldPanel.add(inputField);
        fieldPanel.add(errorField);
        holdPanel.add(fieldPanel);
        centerPanel.add(holdPanel);
    }

    private void addPricePanel() {
        priceField.setEditable(false);
        JPanel pricePanel = new JPanel();
        pricePanel.setLayout(new GridLayout(1, 2));

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(1, 2));
        infoPanel.add(new JLabel("The price of the seat: "));
        infoPanel.add(priceField);
        pricePanel.add(infoPanel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2));
        cancelButton.setActionCommand("cancel");
        cancelButton.addActionListener(this);
        bookButton.setActionCommand("book");
        bookButton.addActionListener(this);
        buttonPanel.add(cancelButton);
        buttonPanel.add(bookButton);
        pricePanel.add(buttonPanel);
        centerPanel.add(pricePanel);
    }

    private boolean checkData() {
        boolean ans = checkPieceOfData(new EmailChecker(emailField.getText()), emailErrorField);
        if (!checkPieceOfData(new PhoneNumberChecker(phoneNumberField.getText()), phoneNumberErrorField)) ans = false;
        if (!checkPieceOfData(new DateChecker(dateOfBirthField.getText()), dateOfBirthErrorField)) ans = false;
        if (!checkPieceOfData(new NameChecker(firstNameField.getText()), firstNameErrorFiled)) ans = false;
        if (!checkPieceOfData(new NameChecker(lastNameFiled.getText()), lastNameErrorField)) ans = false;
        return ans;
    }

    private boolean checkPieceOfData(DataChecker checker, JTextField errorField) {
        if (!checker.isCorrect()) {
            errorField.setText(checker.getErrorMessage());
            return false;
        } else {
            errorField.setText("");
            return true;
        }
    }

    public void makeVisible(Flight flight, Seat seat) {
        this.flight = flight;
        this.seat = seat;
        loadData();
        cancelButton.setVisible(!seat.isEmpty());
        priceField.setText(seat.getPrice() / 100 + "." + seat.getPrice() % 100 + "$");
        setVisible(true);
    }

    private void loadData() {
        if (seat.isEmpty()) {
            firstNameField.setText("");
            lastNameFiled.setText("");
            dateOfBirthField.setText("");
            emailField.setText("");
            phoneNumberField.setText("");
        } else {
            firstNameField.setText(seat.getPassenger().getFirstName());
            lastNameFiled.setText(seat.getPassenger().getLastName());
            dateOfBirthField.setText(seat.getPassenger().getDateOfBirth().data());
            emailField.setText(seat.getPassenger().getEmail());
            phoneNumberField.setText(seat.getPassenger().getPhoneNumber());
        }
        firstNameErrorFiled.setText("");
        lastNameErrorField.setText("");
        dateOfBirthErrorField.setText("");
        phoneNumberErrorField.setText("");
        emailErrorField.setText("");
    }

    private Person getEnteredPassengerInfo() {
        return new Person(firstNameField.getText(), lastNameFiled.getText(),
                new Date(dateOfBirthField.getText()), phoneNumberField.getText(), emailField.getText());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        if (e.getActionCommand().equals("cancel")) {
            if (JOptionPane.showConfirmDialog(null, "Are you sure you want to cancel the booking?", "Confirmation", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                flight.cancelSeat(seat.getNumber());
                DataWriter.updateSeatingInformation(flight.getSeating(), flight.getFilename());
                DataWriter.updateFlightList(calendar);
                applicationFrame.switchToHome();
            }
        }
        if (e.getActionCommand().equals("book")) {
            if (!checkData()) {
                JOptionPane.showMessageDialog(null, "The provided input contains errors", "Input warning", JOptionPane.WARNING_MESSAGE);
            } else {
                if (JOptionPane.showConfirmDialog(null, "Please, confirm booking of the seat", "Confirmation", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    flight.bookSeat(seat.getNumber(), getEnteredPassengerInfo());
                    DataWriter.updateSeatingInformation(flight.getSeating(), flight.getFilename());
                    DataWriter.updateFlightList(calendar);
                    JOptionPane.showMessageDialog(null, "Seat booked successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    applicationFrame.switchToHome();
                }
            }
        }
    }
}
