/*
Author: Oleksandr Danchenko
time spent: 15 minutes
Date: 24 May 2023
version #1
 */

package gui.components;

import javax.swing.*;
import java.awt.*;

/**
 * A custom panel class, a panel that is designed to look in the style of the app.
 */
public class CustomPanel extends JPanel {
    /**
     * The background color.
     */
    public static final Color BACKGROUND_WHITE = new Color(234, 249, 249);

    /**
     * A basic constructor of the class, sets the background to be of a proper color.
     *
     * @author Oleksandr Danchenko
     */
    public CustomPanel() {
        super();
        setBackground(BACKGROUND_WHITE);
    }

    /**
     * A constructor of the class that specifies the layout manager of the panel.
     *
     * @param manager the layout manager of the panel.
     * @author Oleksandr Danchenko
     */
    public CustomPanel(LayoutManager manager) {
        this();
        setLayout(manager);
    }

    /**
     * A constructor of the class, used when the panel has a box layout.
     *
     * @param axis the alignment axis of the box layout.
     * @author Oleksandr Danchenko
     */
    public CustomPanel(int axis) {
        this();
        setLayout(new BoxLayout(this, axis));
    }
}
