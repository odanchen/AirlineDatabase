package panels;

import data_record.Calendar;
import frame.ApplicationFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CalendarPanel extends CustomPanel {
    private final JLabel[] dayLabels = new JLabel[7];
    private JButton[] dayButtons = new JButton[Calendar.NUMBER_OF_DAYS];
    private JPanel calendarPanel;

    public CalendarPanel(ApplicationFrame applicationFrame) {
        super(applicationFrame);

        //calendarPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(6, 7));

        //day labels
        dayLabels[0] = new JLabel("     Sunday");
        dayLabels[1] = new JLabel("     Monday");
        dayLabels[2] = new JLabel("     Tuesday");
        dayLabels[3] = new JLabel("     Wednesday");
        dayLabels[4] = new JLabel("     Thursday");
        dayLabels[5] = new JLabel("     Friday");
        dayLabels[6] = new JLabel("     Saturday");

        for (JLabel dayLabel :
                dayLabels) {
            centerPanel.add(dayLabel);
        }

        centerPanel.add(new JLabel(""));
        centerPanel.add(new JLabel(""));

        for (int i = 0; i < dayButtons.length; i++) {
            dayButtons[i] = new JButton(Integer.toString(i + 1));
            dayButtons[i].setActionCommand(Integer.toString(i));
            dayButtons[i].addActionListener(this);
            centerPanel.add(dayButtons[i]);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);

        try {
            int dayNumber = Integer.parseInt(e.getActionCommand());
        } catch (NumberFormatException exception) {
            return;
        }
        //calendar.getDay(dayNumber);
        applicationFrame.switchToFlight();
    }
}
