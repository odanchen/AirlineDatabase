/*
Author: Oleksandr Danchenko
time spent: 40 minutes
version #1
*/

package gui.panels;

import gui.ApplicationFrame;
import logic.data_checking.DataChecker;
import logic.data_checking.DateChecker;
import logic.data_checking.EmailChecker;
import logic.data_checking.PhoneNumberChecker;
import logic.data_record.Seat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class UserInputPanel extends CustomPanel {
    private Seat seat;
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


    public UserInputPanel(ApplicationFrame applicationFrame) {
        super(applicationFrame);
        centerPanel.setLayout(new GridLayout(6, 1));
        addInputSection("Enter your first name", firstNameField, firstNameErrorFiled);
        addInputSection("Enter your last name", lastNameFiled, lastNameErrorField);
        addInputSection("Enter your phone number in one of the following formats - " +
                "\"XXXXXXXXXX\", \"XXXX XXX XXX\", \"XXXX-XXX-XXX\"", phoneNumberField, phoneNumberErrorField);
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

        return ans;
    }

    private boolean checkPieceOfData(DataChecker checker, JTextField errorField) {
        if (!checker.isCorrect()) {
            errorField.setText(checker.getErrorMessage());
            return false;
        }
        return true;
    }

    public void makeVisible(Seat seat) {
        this.seat = seat;
        cancelButton.setVisible(!seat.isEmpty());
        priceField.setText(seat.getPrice() / 100 + "." + seat.getPrice() % 100 + "$");
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        if (e.getActionCommand().equals("book")) {
            System.out.println("AAA");
            if (!checkData()) {
                JOptionPane.showMessageDialog(null, "The provided input contains errors", "Input warning", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}
