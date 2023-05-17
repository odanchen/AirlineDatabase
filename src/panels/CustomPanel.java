/*
Author: Aidan Baker
time spent: todo
version #1
 */
package panels;

import frame.ApplicationFrame;
import graphics.Logo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;

public abstract class CustomPanel extends JPanel implements ActionListener {
    protected frame.ApplicationFrame applicationFrame;
    protected JPanel centerPanel;
    private Logo logo;
    protected JPanel buttonPanel;
    private JButton homeButton;
    private JButton flightSearchButton;
    private JButton calendarButton;
    private JButton manualButton;
    private JButton exitButton;
    //protected Calendar calendar;

    /**
     * The constructor for the CustomPanel class.
     * @author Aidan Baker
     */
    public CustomPanel(ApplicationFrame applicationFrame) {
        setLayout(new BorderLayout());

        {
            //bottom button bar
            buttonPanel = new JPanel();
            buttonPanel.setLayout(new GridLayout(1, 5));

            //home button
            homeButton = new JButton("Home");
            homeButton.setFont(new Font("Arial", Font.BOLD, 30));
            homeButton.addActionListener(this);
            buttonPanel.add(homeButton);

            //flight search button
            flightSearchButton = new JButton("Search for a Flight");
            flightSearchButton.setFont(new Font("Arial", Font.BOLD, 25));
            flightSearchButton.addActionListener(this);
            buttonPanel.add(flightSearchButton);

            //calendar button
            calendarButton = new JButton("Calendar");
            calendarButton.setFont(new Font("Arial", Font.BOLD, 30));
            calendarButton.addActionListener(this);
            buttonPanel.add(calendarButton);

            //user manual button
            manualButton = new JButton("User Manual");
            manualButton.setFont(new Font("Arial", Font.BOLD, 30));
            manualButton.addActionListener(this);
            buttonPanel.add(manualButton);

            //exit button
            exitButton = new JButton("Exit");
            exitButton.setFont(new Font("Arial", Font.BOLD, 30));
            exitButton.addActionListener(this);
            buttonPanel.add(exitButton);

            //add button panel to bottom of frame
            buttonPanel.setPreferredSize(new Dimension(1200, 75));
            add(buttonPanel, BorderLayout.SOUTH);
        } //button panel

        {
            logo = new Logo();
            logo.setPreferredSize(new Dimension(150, 150));
            add(logo, BorderLayout.NORTH);
        } //logo
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source.equals(homeButton)) {
            //applicationFrame.switchToHome();
        } else if (source.equals(flightSearchButton)) {
            //applicationFrame.switchToSearch();
        } else if (source.equals(calendarButton)) {
            //applicationFrame.switchToCalendar();
        } else if (source.equals(manualButton)) {
            try {
                Desktop.getDesktop().browse(URI.create("https://docs.google.com/document/d/1MoQYM9OzFQPjyVoqWxH3KVLu8QRYIB-3qXgkCfCr4uc/edit?usp=sharing"));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (source.equals(exitButton)) {
            System.exit(0);
        }
    }
}
