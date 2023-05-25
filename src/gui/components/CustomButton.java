package gui.components;

import javax.swing.*;
import java.awt.*;

public class CustomButton extends JButton {
    public static final Color BUTTON_BLUE = new Color(7, 16, 55);
    public static final int DEFAULT_FONT_SIZE = 15;
    public CustomButton(String text) {
        super(text);
        repaint();
        setBackground(BUTTON_BLUE);
        setBorderPainted(false);
        setForeground(Color.WHITE);
        setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
    }

    public CustomButton(String text, int fontSize) {
        this(text);
        setFont(new Font(Font.SANS_SERIF, Font.BOLD, fontSize));
    }

    @Override
    public void paint(Graphics g) {
        int w = getWidth(), h = getHeight();
        g.setColor(CustomPanel.BACKGROUND_WHITE);
        g.drawRect(0, 0, w, h);
        g.setColor(BUTTON_BLUE);
        g.fillRoundRect((int) (w * 0.05), (int) (h * 0.05), (int) (w * 0.9), (int)(h * 0.9), 7, 7);
        super.paint(g);
    }
}