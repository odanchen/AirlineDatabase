/*
Author: Oleksandr Danchenko, Aidan Baker
Time Spent: 30 minutes
Date: 19 May 2023
Changes: Completely updated the screens layout and added swap button
        Author: Aidan Baker
        Date: 24 May 2023
        Time spent: 50 minutes
Changes: Implemented the swap button function, slight refactoring
        Author: Oleksandr Danchenko
        time spent: 15 minutes.
        Date: 29 May 2023.
Version #3
 */

package gui.panels;

import gui.components.CustomButton;
import gui.components.CustomPanel;
import gui.components.CustomRadioButton;
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
 * @author Oleksandr Danchenko
 * @see ScreenPanel
 */
public class SearchPanel extends ScreenPanel {
    /**
     * The calendar of flights.
     */
    private final Calendar calendar;

    /**
     * Button group for selecting departure
     */
    private final ButtonGroup departureGroup = new ButtonGroup();

    /**
     * Button group for selecting destination
     */
    private final List<CustomRadioButton> departureButtons = new ArrayList<CustomRadioButton>(4);

    /**
     * Button group for selecting destination
     */
    private final ButtonGroup destinationGroup = new ButtonGroup();

    /**
     * Button group for selecting destination
     */
    private final List<CustomRadioButton> destinationButtons = new ArrayList<CustomRadioButton>(4);

    /**
     * Panel for departure buttons
     */
    private final CustomPanel departurePanel = new CustomPanel(new GridLayout(4, 1));

    /**
     * Panel for destination buttons
     */
    private final CustomPanel destinationPanel = new CustomPanel(new GridLayout(4, 1));

    /**
     * The constructor of the class.
     *
     * @param applicationFrame the reference to the application frame object.
     * @param calendar         the calendar of flights.
     * @author Aidan Baker
     */
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
     *
     * @param message  the message to be displayed on the button
     * @param selected whether the button should be selected by default
     * @author Oleksandr Danchenko
     */
    private void addButtons(String message, boolean selected) {
        destinationButtons.add(new CustomRadioButton(message, selected));
        departureButtons.add(new CustomRadioButton(message, selected));

        CustomPanel destPanel = new CustomPanel(new GridLayout(1, 1));
        destPanel.add(destinationButtons.get(destinationButtons.size() - 1));

        CustomPanel depPanel = new CustomPanel(new GridLayout(1, 1));
        depPanel.add(departureButtons.get(departureButtons.size() - 1));

        destinationGroup.add(destinationButtons.get(destinationButtons.size() - 1));
        departureGroup.add(departureButtons.get(departureButtons.size() - 1));
        destinationPanel.add(destPanel);
        departurePanel.add(depPanel);
    }

    /**
     * Gets all available flights matching a specific criteria.
     *
     * @param filter the filter for flights.
     * @return all flights that pass the filter.
     * @author Oleksandr Danchenko
     */
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
     *
     * @param text the text to be displayed on the subtitle
     * @return a JLabel with the text and the correct formatting
     * @author Aidan Baker
     */
    private JLabel makeSubTitle(String text) {
        JLabel label = new JLabel(text);
        label.setBackground(new Color(248, 249, 249));
        label.setFont(new Font("Arial", Font.BOLD, 40));
        label.setHorizontalAlignment(JLabel.CENTER);
        return label;
    }

    /**
     * A method for aligning content containing a subtitle vertically in the center of the screen
     *
     * @param panel the panel to be aligned
     * @param text  the text to be displayed on the subtitle
     * @return a CustomPanel with the correct formatting and the panel passed in as a parameter aligned vertically in the center of the screen
     * @author Aidan Baker
     * @see CustomPanel
     * @see #makeSubTitle(String)
     */
    private CustomPanel verticalCenterAlign(JPanel panel, String text) {
        CustomPanel formattedPanel = new CustomPanel(new GridLayout(3, 1));
        formattedPanel.add(makeSubTitle(text));
        formattedPanel.add(panel);
        return formattedPanel;
    }

    /**
     * A method for adding the search and swap buttons to the center of the screen
     *
     * @return a JPanel containing the search and swap buttons with the correct formatting
     * @author Aidan Baker
     * @see CustomPanel
     */
    private JPanel addCenterButtons() {
        CustomPanel centerButtonPanel = new CustomPanel(new GridLayout(3, 1));
        centerButtonPanel.add(new JLabel(""));

        CustomPanel innerCenterButtonPanel = new CustomPanel(BoxLayout.Y_AXIS);

        CustomPanel searchButtonPanel = new CustomPanel(new GridLayout(1, 1));
        searchButtonPanel.add(new CustomButton("Search", "Search", this));
        searchButtonPanel.setPreferredSize(new Dimension(80, 70));
        innerCenterButtonPanel.add(searchButtonPanel);

        CustomPanel swapButtonPanel = new CustomPanel(new GridLayout(1, 1));
        searchButtonPanel.add(new CustomButton("Swap", "Swap", this));
        swapButtonPanel.setPreferredSize(new Dimension(80, 70));
        innerCenterButtonPanel.add(swapButtonPanel);

        centerButtonPanel.add(innerCenterButtonPanel);
        return centerButtonPanel;
    }

    /**
     * A method for resetting the selection of the departure and destination buttons to All
     */
    public void makeVisible() {
        super.makeVisible();
        departureButtons.get(0).setSelected(true);
        destinationButtons.get(0).setSelected(true);
    }

    /**
     * A method for swapping selected departure and destination
     * @author Oleksandr Denchenko
     */
    private void actionSwap() {
        String sel1 = departureGroup.getSelection().getActionCommand();
        String sel2 = destinationGroup.getSelection().getActionCommand();
        for (CustomRadioButton button : departureButtons) {
            if (button.getActionCommand().equals(sel2)) button.setSelected(true);
            else if (button.isSelected()) button.setSelected(false);
        }
        for (CustomRadioButton button : destinationButtons) {
            if (button.getActionCommand().equals(sel1)) button.setSelected(true);
            else if (button.isSelected()) button.setSelected(false);
        }
    }

    /**
     * A getter method for the selected departure.
     *
     * @return the selected departure.
     * @author Oleksandr Danchenko
     */
    private String getSelectedDeparture() {
        return departureGroup.getSelection().getActionCommand();
    }

    /**
     * A getter method for the selected destination.
     *
     * @return the selected destination.
     * @author Oleksandr Danchenko
     */
    private String getSelectedDestination() {
        return destinationGroup.getSelection().getActionCommand();
    }

    @Override
    public String getTitle() {
        return "Search for a Flight";
    }

    /**
     * A method that executes whe a button is pressed.
     *
     * @param e the event to be processed.
     * @author Oleksandr danchenko
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        
        if (e.getActionCommand().equals("Search")) {
            if (getSelectedDeparture().equals("All")) {
                if (getSelectedDestination().equals("All"))
                    applicationFrame.switchToFlightList(getFlights(new AnyFlight()));
                else applicationFrame.switchToFlightList(getFlights(new HasDestination(getSelectedDestination())));
            } else if (getSelectedDestination().equals("All")) {
                applicationFrame.switchToFlightList(getFlights(new HasDeparture(getSelectedDeparture())));
            } else if (!(getSelectedDeparture().equals(getSelectedDestination()))) {
                applicationFrame.switchToFlightList(getFlights(new HasRoute(getSelectedDeparture(), getSelectedDestination())));
            } else {
                JOptionPane.showMessageDialog(this, "Departure and destination cannot be the same", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (e.getActionCommand().equals("Swap")) actionSwap();
    }
}
