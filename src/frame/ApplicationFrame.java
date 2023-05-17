package frame;

import javax.swing.*;
import panels.*;

public class ApplicationFrame extends JFrame {
    private final JPanel centerPanel = new JPanel();
    private CustomPanel currentPanel;
    private final CustomPanel loadingPanel = new LoadingPanel(this);
    private final CustomPanel homePanel = new HomePanel(this);
    private final CustomPanel calendarPanel = new CalendarPanel(this);
    private final CustomPanel searchPanel = new SearchPanel(this);
    private final CustomPanel flightListPanel = new FlightListPanel(this);
    private final CustomPanel seatPanel = new SearchPanel(this);
    private final CustomPanel userInputPanel = new UserInputPanel(this);
    private final CustomPanel exportPanel = new ExportPanel(this);

    public ApplicationFrame() {
        setSize(1400, 850);
    }

    public void switchToHome() {

    }

    public void switchToCalendar() {

    }

    public void switchToSearch() {

    }

    public void switchToFlight() {

    }

    public void switchToSeat() {

    }

    public void switchToInput() {

    }

    public void switchToExport() {

    }
}
