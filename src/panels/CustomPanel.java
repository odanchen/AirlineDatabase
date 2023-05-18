/*
Author: Aidan Baker
time spent: todo
version #1
 */
package panels;

import data_record.Calendar;
import frame.ApplicationFrame;
import graphics.Logo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;

public abstract class CustomPanel extends JPanel implements ActionListener {
    protected frame.ApplicationFrame applicationFrame;
    protected JPanel centerPanel = new JPanel();
    protected JPanel buttonPanel;
    protected JPanel topPanel;
    private static final String homeButton = "Home";
    private static final String flightSearchButton = "Search for a Flight";
    private static final String calendarButton = "Calendar";
    private static final String manualButton = "User Manual";
    private static final String exitButton = "Exit";
    protected Calendar calendar;

    /**
     * The constructor for the CustomPanel class.
     * @author Aidan Baker
     */
    public CustomPanel(ApplicationFrame applicationFrame) {
        this.applicationFrame = applicationFrame;
        setLayout(new BorderLayout());
        //bottom button bar
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 5));

        addBottomButton(homeButton, 30);
        addBottomButton(flightSearchButton, 25);
        addBottomButton(calendarButton, 30);
        addBottomButton(manualButton, 30);
        addBottomButton(exitButton, 30);

        //add button panel to bottom of frame
        add(buttonPanel, BorderLayout.SOUTH);

        topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.add(new Logo());
        add(topPanel, BorderLayout.NORTH);

        centerPanel.setPreferredSize(new Dimension(1000, 600));
        add(centerPanel, BorderLayout.CENTER);
        setVisible(false);
    }

    private void addBottomButton(String text, int fontSize) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, fontSize));
        button.setActionCommand(text);
        button.addActionListener(this);
        buttonPanel.add(button);
    }

    protected void setTitle(String title) {
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 80));
        topPanel.add(titleLabel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        if (actionCommand.equals(homeButton)) {
            applicationFrame.switchToHome();
        } else if (actionCommand.equals(flightSearchButton)) {
            applicationFrame.switchToSearch();
        } else if (actionCommand.equals(calendarButton)) {
            applicationFrame.switchToCalendar();
        } else if (actionCommand.equals(manualButton)) {
            try {
                Desktop.getDesktop().browse(URI.create("https://docs.google.com/document/d/1MoQYM9OzFQPjyVoqWxH3KVLu8QRYIB-3qXgkCfCr4uc/edit?usp=sharing"));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (actionCommand.equals(exitButton)) {
            int ans = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Exit", JOptionPane.YES_NO_OPTION);
            if (ans == JOptionPane.YES_OPTION) System.exit(0);
        }
    }
}
