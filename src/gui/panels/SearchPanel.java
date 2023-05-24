package gui.panels;

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

public class SearchPanel extends ScreenPanel {
    private final Calendar calendar;
    private final ButtonGroup departureGroup = new ButtonGroup();
    private final ButtonGroup destinationGroup = new ButtonGroup();
    private final JPanel departurePanel = new JPanel();
    private final JPanel destinationPanel = new JPanel();

    public SearchPanel(ApplicationFrame applicationFrame, Calendar calendar) {
        super(applicationFrame);
        this.calendar = calendar;

        centerPanel.setLayout(new BorderLayout());
        departurePanel.setLayout(new GridLayout(4, 1));
        destinationPanel.setLayout(new GridLayout(4, 1));
        addButtons("", true);
        addButtons(Route.TORONTO, false);
        addButtons(Route.OTTAWA, false);
        addButtons(Route.VANCOUVER, false);
        centerPanel.add(departurePanel, BorderLayout.WEST);
        JPanel center = new JPanel();
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(this);
        searchButton.setActionCommand("Search");
        center.add(searchButton);
        centerPanel.add(center, BorderLayout.CENTER);
        centerPanel.add(destinationPanel, BorderLayout.EAST);

    }

    private void addButtons(String message, boolean selected) {
        JRadioButton destButton = new JRadioButton(message), depButton = new JRadioButton(message);
        destButton.setSelected(selected); depButton.setSelected(selected);
        destButton.setActionCommand(message); depButton.setActionCommand(message);
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

    private String getSelectedDeparture() {
        return departureGroup.getSelection().getActionCommand();
    }

    private String getSelectedDestination() {
        return destinationGroup.getSelection().getActionCommand();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        if (e.getActionCommand().equals("Search")) {
            if (getSelectedDeparture().equals("")) {
                if (getSelectedDestination().equals("")) applicationFrame.switchToFlightList(getFlights(new AnyFlight()));
                else applicationFrame.switchToFlightList(getFlights(new HasDestination(getSelectedDestination())));
            } else if (getSelectedDestination().equals("")) {
                applicationFrame.switchToFlightList(getFlights(new HasDeparture(getSelectedDeparture())));
            } else {
                applicationFrame.switchToFlightList(getFlights(new HasRoute(getSelectedDeparture(), getSelectedDestination())));
            }
        }
    }
}
