package panels;

import data_record.Calendar;
import frame.ApplicationFrame;

public class SearchPanel extends CustomPanel {
    private final Calendar calendar;
    public SearchPanel(ApplicationFrame applicationFrame, Calendar calendar) {
        super(applicationFrame);
        this.calendar = calendar;
    }
}
