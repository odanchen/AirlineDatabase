package gui.components;

import javax.swing.*;
import java.awt.*;

public class CustomRadioButton extends JRadioButton {
    public static final Color SELECTED_COLOR = new Color(50, 102, 203);

    public CustomRadioButton(String message, boolean isSelected) {
        super(message);
        setBackground(CustomButton.BUTTON_BLUE);
        setBorderPainted(false);
        setForeground(Color.WHITE);
        setFont(new Font("Arial", Font.PLAIN, 32));
        setSelected(isSelected);
        setAlignmentX(Component.CENTER_ALIGNMENT);
        setActionCommand(message);
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        Color color;
        if (isSelected()) color = SELECTED_COLOR;
        else color = CustomButton.BUTTON_BLUE;

        int w = getWidth(), h = getHeight();
        g.setColor(color);
        g.fillRoundRect((int) (w * 0.02), (int) (h * 0.07), (int) (w * 0.98), (int) (h * 0.93), 7, 7);
        setIcon(new CustomIcon());
        setPressedIcon(new CustomIcon());
        setSelectedIcon(new CustomIcon());
        super.paint(g);
    }
}
