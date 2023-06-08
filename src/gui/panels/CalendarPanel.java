/*
Author: Aidan Baker
time spent: 35 minutes
Date: 18 May 2023
version #2
Changes: implemented the getTitle() and makeVisible() methods added to the ScreenPanel in the process of cleaning up the code.
    time spent: 5 minutes
    Date 1 June 2023
    Author: Oleksandr Danchenko
*/

package gui.panels;

import gui.components.CustomButton;
import logic.data_record.Calendar;
import gui.ApplicationFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * The CalendarPanel class, displays the calendar for the month of August 2023.
 *
 * @author Aidan Baker
 * @see ScreenPanel
 */
public class CalendarPanel extends ScreenPanel {
    /**
     * The reference to the instance of the calendar.
     */
    private final Calendar calendar;

    /**
     * The constructor for the CalendarPanel class.
     *
     * Citation: https://docs.oracle.com/javase/8/docs/api/javax/swing/JComponent.html#setForeground-java.awt.Color-
     *      The setForeground() method is used to specify the color of the text on the text field.
     * Citation: https://docs.oracle.com/javase/8/docs/api/javax/swing/JLabel.html#setHorizontalAlignment-int-
     *      The setHorizontalAlignment() method is used to center the text inside the JLabel
     * @param applicationFrame the frame that the panel is displayed on
     * @author Aidan Baker
     */
    public CalendarPanel(ApplicationFrame applicationFrame, Calendar calendar) {
        super(applicationFrame, new GridLayout(6, 7));
        this.calendar = calendar;

        //day labels
        String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        for (String day : days) {
            JLabel dayLabel = new JLabel(day);
            dayLabel.setForeground(CustomButton.BUTTON_BLUE);
            dayLabel.setHorizontalAlignment(JLabel.CENTER);
            dayLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
            add(dayLabel);
        }
        //blank labels to fill in empty space to get calendar to start on the right day
        add(new JLabel(""));
        add(new JLabel(""));

        //day buttons
        for (int i = 1; i <= Calendar.NUMBER_OF_DAYS; i++) {
            add(new CustomButton(String.valueOf(i), String.valueOf(i), this, 24));
        }
    }

    /**
     * A helper method to check if a day button was pressed.
     *
     * @param e the action event
     * @return true if a day button was pressed, false otherwise
     */
    private boolean isDayButtonPressed(ActionEvent e) {
        try {
            return Integer.parseInt(e.getActionCommand()) >= 1 && Integer.parseInt(e.getActionCommand()) <= Calendar.NUMBER_OF_DAYS;
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * Returns the title of the calendar panel.
     *
     * @return "August 2023".
     * @author Oleksandr Danchenko
     */
    @Override
    public String getTitle() {
        return "August 2023";
    }

    /**
     * Makes the panel visible.
     *
     * @author Oleksandr Danchenko
     */
    @Override
    public void makeVisible() {
        super.makeVisible();
        applicationFrame.setBackButtonVisibility(false);
    }

    /**
     * The method that is called when an action is performed.
     *
     * @param e the action event
     * @author Aidan Baker
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        if (isDayButtonPressed(e)) {
            applicationFrame.switchToFlightList(calendar.getDay(Integer.parseInt(e.getActionCommand())));
        }
    }
}
