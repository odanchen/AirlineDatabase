/*
Author: Aidan Baker
time spent: 20 minutes
version #1
*/
package gui.panels;

import logic.data_record.Calendar;
import gui.ApplicationFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * The CalendarPanel class, displays the calendar for the month of August 2023.
 *
 * @author Aidan Baker
 * @see gui.panels.CustomPanel
 */
public class CalendarPanel extends CustomPanel {
    private final Calendar calendar;

    /**
     * The constructor for the CalendarPanel class.
     *
     * @param applicationFrame the frame that the panel is displayed on
     * @author Aidan Baker
     */
    public CalendarPanel(ApplicationFrame applicationFrame, Calendar calendar) {
        super(applicationFrame);
        this.calendar = calendar;
        setTitle("August 2023");

        centerPanel.setLayout(new GridLayout(6, 7));

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
        for (int i = 0; i < Calendar.NUMBER_OF_DAYS; i++) {
            JButton dayButton = new JButton(String.valueOf(i + 1));
            dayButton.setActionCommand(String.valueOf(i));
            dayButton.addActionListener(this);
            centerPanel.add(dayButton);
        }
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
        int dayNumber;
        try {
            dayNumber = Integer.parseInt(e.getActionCommand());
        } catch (NumberFormatException exception) {
            return;
        }
        //calendar.getDay(dayNumber);
        applicationFrame.switchToFlight();
    }
}
