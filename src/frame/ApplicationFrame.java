package frame;

import javax.swing.*;
import panels.*;

import java.awt.*;

public class ApplicationFrame extends JFrame {
    private final CustomPanel loadingPanel = new LoadingPanel(this);
    private final CustomPanel homePanel = new HomePanel(this);
    private final CustomPanel calendarPanel = new CalendarPanel(this);
    private final CustomPanel searchPanel = new SearchPanel(this);
    private final CustomPanel flightListPanel = new FlightListPanel(this);
    private final CustomPanel seatPanel = new SearchPanel(this);
    private final CustomPanel userInputPanel = new UserInputPanel(this);
    private final CustomPanel exportPanel = new ExportPanel(this);
    private CustomPanel currentPanel = homePanel;

    public ApplicationFrame() {
        super();
        setLayout(new BorderLayout());
        JPanel centerPanel = new JPanel();
        centerPanel.add(loadingPanel);
        centerPanel.add(homePanel);
        centerPanel.add(calendarPanel);
        centerPanel.add(searchPanel);
        centerPanel.add(flightListPanel);
        centerPanel.add(seatPanel);
        centerPanel.add(userInputPanel);
        centerPanel.add(exportPanel);
        add(centerPanel, BorderLayout.CENTER);
        setSize(1400, 850);
        homePanel.setVisible(true);
        setVisible(true);

    }

    public void switchToHome() {
        currentPanel.setVisible(false);
        homePanel.setVisible(true);
        currentPanel = homePanel;
    }

    public void switchToCalendar() {
        currentPanel.setVisible(false);
        calendarPanel.setVisible(true);
        currentPanel = calendarPanel;
    }

    public void switchToSearch() {
        currentPanel.setVisible(false);
        searchPanel.setVisible(true);
        currentPanel = searchPanel;
    }

    public void switchToFlight() {
        currentPanel.setVisible(false);
        flightListPanel.setVisible(true);
        currentPanel = flightListPanel;
    }

    public void switchToSeat() {
        currentPanel.setVisible(false);
        seatPanel.setVisible(true);
        currentPanel = seatPanel;
    }

    public void switchToInput() {
        currentPanel.setVisible(false);
        userInputPanel.setVisible(true);
        currentPanel = userInputPanel;
    }

    public void switchToExport() {
        currentPanel.setVisible(false);
        exportPanel.setVisible(true);
        currentPanel = exportPanel;
    }
}
