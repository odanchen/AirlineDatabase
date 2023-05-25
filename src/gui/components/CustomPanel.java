package gui.components;

import javax.swing.*;
import java.awt.*;

public class CustomPanel extends JPanel {
    public static final Color backgroundWhite = new Color(230, 249, 249);

    public CustomPanel() {
        super();
        setBackground(backgroundWhite);
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
