package gui.components;

import javax.swing.*;
import java.awt.*;

public class CustomRadioButton extends JRadioButton {
    public static final Color SELECTED_COLOR = new Color(50, 102, 203);

    public CustomRadioButton(String message, boolean isSelected) {
        super(message);
        setBackground(new Color(0, 0, 0, 0));
        setBorderPainted(false);
        setForeground(Color.WHITE);
        setFont(new Font("Arial", Font.PLAIN, 32));
        setSelected(isSelected);
        setAlignmentX(Component.CENTER_ALIGNMENT);
        setActionCommand(message);
        setIcon(new CustomIcon());
        setPressedIcon(new CustomIcon());
        setSelectedIcon(new CustomIcon());
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        int w = getWidth(), h = getHeight();
        if (isSelected()) g.setColor(SELECTED_COLOR);
        else g.setColor(CustomButton.BUTTON_BLUE);
        g.fillRoundRect((int) (w * 0.02), (int) (h * 0.07), (int) (w * 0.98), (int) (h * 0.93), 7, 7);
        super.paint(g);
    }
}
