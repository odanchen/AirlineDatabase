package gui.components;

;

import javax.swing.*;
import java.awt.*;

public class CustomButton extends JButton {
    public static final Color buttonBlue = new Color(7, 16, 55);
    public CustomButton(String text) {
        super(text);
        repaint();
        setBackground(buttonBlue);
        setBorderPainted(false);
        setForeground(Color.WHITE);
        setFont(new Font(Font.SERIF, Font.BOLD, 15));
    }

    @Override
    public void paint(Graphics g) {
        int w = getWidth(), h = getHeight();
        g.setColor(CustomPanel.backgroundWhite);
        g.drawRect(0, 0, w, h);
        g.setColor(buttonBlue);
        g.fillRoundRect((int) (w * 0.05), (int) (h * 0.05), (int) (w * 0.9), (int)(h * 0.9), 7, 7);
        super.paint(g);
    }
}