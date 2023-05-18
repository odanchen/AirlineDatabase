package gui.panels;

import logic.data_record.Calendar;
import gui.ApplicationFrame;

public class SearchPanel extends CustomPanel {
    private final Calendar calendar;
    public SearchPanel(ApplicationFrame applicationFrame, Calendar calendar) {
        super(applicationFrame);
        this.calendar = calendar;
    }
}
