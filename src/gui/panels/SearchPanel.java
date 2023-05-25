/*
Author: Oleksandr Danchenko, Aidan Baker
Time Spent: 20 minutes
Date: 19 May 2023
Changes: Completely updated the screens layout and adding swap button
        Author: Aidan Baker
        Date: 24 May 2023
        Time spent: 50 minutes
Version #2
 */

package gui.panels;

import gui.components.CustomPanel;
import logic.data_record.Calendar;
import gui.ApplicationFrame;
import logic.data_record.FlightInfo;
import logic.data_record.Route;
import logic.filtering.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * A SearchPanel class, represents a panel for searching flights by departure and destination
 *
 * @see ScreenPanel
 * @author Oleksandr Danchenko
 */
public class SearchPanel extends ScreenPanel {
    private final Calendar calendar;

    /**
     * Button group for selecting departure
     */
    private final ButtonGroup departureGroup = new ButtonGroup();

    /**
     * Button group for selecting destination
     */
    private final ButtonGroup destinationGroup = new ButtonGroup();

    /**
     * Panel for departure buttons
     */
    private final CustomPanel departurePanel = new CustomPanel(new GridLayout(4, 1));

    /**
     * Panel for destination buttons
     */
    private final CustomPanel destinationPanel = new CustomPanel(new GridLayout(4, 1));


    public SearchPanel(ApplicationFrame applicationFrame, Calendar calendar) {
        super(applicationFrame, new GridLayout(1, 5));
        this.calendar = calendar;

        departurePanel.setLayout(new BoxLayout(departurePanel, BoxLayout.Y_AXIS));
        destinationPanel.setLayout(new BoxLayout(destinationPanel, BoxLayout.Y_AXIS));

        addButtons("All", true);
        addButtons(Route.TORONTO, false);
        addButtons(Route.OTTAWA, false);
        addButtons(Route.VANCOUVER, false);

        //adding content to the main panel
        centerPanel.add(new JLabel(""));
        centerPanel.add(verticalCenterAlign(departurePanel, "Departure"));
        centerPanel.add(addCenterButtons());
        centerPanel.add(verticalCenterAlign(destinationPanel, "Destination"));
        centerPanel.add(new JLabel(""));
    }

    /**
     * A method for adding buttons for each location to the departure and destination panels
     * @param message the message to be displayed on the button
     * @param selected whether the button should be selected by default
     * @author Oleksandr Danchenko
     */
    private void addButtons(String message, boolean selected) {
        JRadioButton destButton = new JRadioButton(message), depButton = new JRadioButton(message);
        destButton.setSelected(selected); depButton.setSelected(selected);
        destButton.setActionCommand(message); depButton.setActionCommand(message);
        destButton.setFont(new Font("Arial", Font.PLAIN, 32)); depButton.setFont(new Font("Arial", Font.PLAIN, 32));
        destButton.setPreferredSize(new Dimension(100, 70)); depButton.setPreferredSize(new Dimension(100, 70));
        departureGroup.add(depButton); destinationGroup.add(destButton);
        destinationPanel.add(destButton); departurePanel.add(depButton);
    }

    private List<FlightInfo> getFlights(FlightFilter filter) {
        List<FlightInfo> ans = new ArrayList<FlightInfo>();
        for (int i = 1; i <= Calendar.NUMBER_OF_DAYS; i++) {
            for (FlightInfo info : calendar.getDay(i)) {
                if (filter.predicate(info)) ans.add(info);
            }
        }
        return ans;
    }

    /**
     * A method for making a subtitle
     * @param text the text to be displayed on the subtitle
     * @return a JLabel with the text and the correct formatting
     * @author Aidan Baker
     */
    private JLabel makeSubTitle(String text) {
        JLabel label = new JLabel(text);
        label.setBackground(new Color(248, 249, 249));
        label.setFont(new Font("Arial", Font.BOLD, 40));
        return label;
    }

    /**
     * A method for aligning content containing a subtitle vertically in the center of the screen
     * @param panel the panel to be aligned
     * @param text the text to be displayed on the subtitle
     * @return a CustomPanel with the correct formatting and the panel passed in as a parameter aligned vertically in the center of the screen
     * @see CustomPanel
     * @see #makeSubTitle(String)
     * @author Aidan Baker
     */
    private CustomPanel verticalCenterAlign(JPanel panel, String text) {
        CustomPanel formattedPanel = new CustomPanel(new GridLayout(3, 1));
        formattedPanel.add(makeSubTitle(text));
        formattedPanel.add(panel);
        return formattedPanel;
    }

    /**
     * A method for adding the search and swap buttons to the center of the screen
     * @return a JPanel containing the search and swap buttons with the correct formatting
     * @see CustomPanel
     * @author Aidan Baker
     */
    private JPanel addCenterButtons() {
        CustomPanel centerButtonPanel = new CustomPanel(new GridLayout(3, 1));
        centerButtonPanel.add(new JLabel(""));

        CustomPanel innerCenterButtonPanel = new CustomPanel(BoxLayout.Y_AXIS);

        CustomPanel searchButtonPanel = new CustomPanel(new GridLayout(1, 1));
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(this);
        searchButton.setActionCommand("Search");
        searchButtonPanel.add(searchButton);
        searchButtonPanel.setPreferredSize(new Dimension(80, 70));
        innerCenterButtonPanel.add(searchButtonPanel);

        CustomPanel swapButtonPanel = new CustomPanel(new GridLayout(1, 1));
        JButton swapButton = new JButton("Swap");
        swapButton.addActionListener(this);
        swapButton.setActionCommand("Swap");
        swapButtonPanel.add(swapButton);
        swapButtonPanel.setPreferredSize(new Dimension(80, 70));
        innerCenterButtonPanel.add(swapButtonPanel);

        centerButtonPanel.add(innerCenterButtonPanel);
        return centerButtonPanel;
    }

    /**
     * A getter method for the selected departure.
     * @return the selected departure.
     */
    private String getSelectedDeparture() {
        return departureGroup.getSelection().getActionCommand();
    }

    /**
     * A getter method for the selected destination.
     * @return the selected destination.
     */
    private String getSelectedDestination() {
        return destinationGroup.getSelection().getActionCommand();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        if (e.getActionCommand().equals("Search")) {
            if (getSelectedDeparture().equals("All")) {
                if (getSelectedDestination().equals("All")) applicationFrame.switchToFlightList(getFlights(new AnyFlight()));
                else applicationFrame.switchToFlightList(getFlights(new HasDestination(getSelectedDestination())));
            } else if (getSelectedDestination().equals("All")) {
                applicationFrame.switchToFlightList(getFlights(new HasDeparture(getSelectedDeparture())));
            } else {
                applicationFrame.switchToFlightList(getFlights(new HasRoute(getSelectedDeparture(), getSelectedDestination())));
            }
        }
    }
}
