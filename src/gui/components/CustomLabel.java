package gui.components;

import gui.panels.ScreenPanel;

import javax.swing.*;
import java.awt.*;

public class CustomLabel extends JLabel {
    private static final Color borderColor = new Color(229, 229, 229);
    public CustomLabel(String text) {
        super(text);
        setBackground(CustomPanel.backgroundWhite);
        setForeground(CustomButton.buttonBlue);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(CustomPanel.backgroundWhite);
        g.drawRect(0, 0, getWidth(), getHeight());
        g.setColor(borderColor);
        g.drawRoundRect(0, 0, getWidth(), getHeight(), 7, 7);
        super.paint(g);
    }
}
