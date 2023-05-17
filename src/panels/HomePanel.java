package panels;

import javax.swing.*;
import java.awt.*;

public class HomePanel extends CustomPanel {
    public HomePanel() {
        super();

        JPanel centerPanel = new JPanel();

        centerPanel.setLayout(new BorderLayout());
        JLabel label = new JLabel("Welcome");
        label.setFont(new Font("Arial", Font.BOLD, 140));
        label.setHorizontalAlignment(JLabel.CENTER);

        centerPanel.add(label, BorderLayout.CENTER);

        super.add(centerPanel, BorderLayout.CENTER);
    }
}
