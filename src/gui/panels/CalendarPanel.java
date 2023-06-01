/*
Author: Aidan Baker
time spent: 20 minutes
Date: 18 May 2023
version #1
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
    private final Calendar calendar;

    /**
     * The constructor for the CalendarPanel class.
     *
     * @param applicationFrame the frame that the panel is displayed on
     * @author Aidan Baker
     */
    public CalendarPanel(ApplicationFrame applicationFrame, Calendar calendar) {
        super(applicationFrame, new GridLayout(6, 7));
        this.calendar = calendar;

        //day labels
        JLabel[] dayLabels = new JLabel[7];
        dayLabels[0] = new JLabel("         Sunday");
        dayLabels[1] = new JLabel("         Monday");
        dayLabels[2] = new JLabel("         Tuesday");
        dayLabels[3] = new JLabel("         Wednesday");
        dayLabels[4] = new JLabel("         Thursday");
        dayLabels[5] = new JLabel("         Friday");
        dayLabels[6] = new JLabel("         Saturday");

        for (JLabel dayLabel : dayLabels) {
            dayLabel.setFont(new Font("Arial", Font.BOLD, 20));
            centerPanel.add(dayLabel);
        }
        //blank labels to fill in empty space to get calendar to start on the right day
        centerPanel.add(new JLabel(""));
        centerPanel.add(new JLabel(""));

        //day buttons
        for (int i = 1; i <= Calendar.NUMBER_OF_DAYS; i++) {
            centerPanel.add(new CustomButton(String.valueOf(i), String.valueOf(i), this));
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

    @Override
    public String getTitle() {
        return "August 2023";
    }

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
