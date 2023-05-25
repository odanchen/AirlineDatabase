package gui.components;

import javax.swing.*;
import java.awt.*;

public class CustomPanel extends JPanel {
    public static final Color BACKGROUND_WHITE = new Color(234, 249, 249);

    public CustomPanel() {
        super();
        setBackground(BACKGROUND_WHITE);
    }

    public CustomPanel(LayoutManager manager) {
        this();
        setLayout(manager);
    }

    public CustomPanel(int axis) {
        this();
        setLayout(new BoxLayout(this, axis));
    }
}
