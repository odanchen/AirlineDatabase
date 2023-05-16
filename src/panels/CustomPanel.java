package panels;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public abstract class CustomPanel extends JPanel implements ActionListener {
    //protected ApplicationFrame applicationFrame = new ApplicationFrame();
    protected JPanel centerPanel;
    //private Logo logo;
    protected JPanel buttonPanel;
    private JButton homeButton;
    private JButton flightSearchButton;
    private JButton calendarButton;
    private JButton exitButton;
    private JButton manualButton;
    //protected Calendar calendar;

    public CustomPanel() {
        super();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
