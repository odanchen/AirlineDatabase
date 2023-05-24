package gui.components;

import javax.swing.*;
import java.awt.*;

public class CustomPanel extends JPanel {
    public static final Color backgroundWhite = new Color(248, 249, 249);

    public CustomPanel() {
        super();
        setBackground(backgroundWhite);
    }

    public CustomPanel(LayoutManager manager) {
        super();
        setBackground(backgroundWhite);
        setLayout(manager);
    }
}
