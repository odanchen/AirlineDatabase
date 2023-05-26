/*
Author: Oleksandr Danchenko
time spent: 25 minutes
Date: 24 May 2023
version #1
 */

package gui.components;

import javax.swing.*;
import java.awt.*;

/**
 * A custom button class, a button that is designed to look in the style of the app.
 */
public class CustomButton extends JButton {
    /**
     * The default color of the button.
     */
    public static final Color BUTTON_BLUE = new Color(7, 16, 55);
    /**
     * The default font size on the button.
     */
    public static final int DEFAULT_FONT_SIZE = 15;

    /**
     * A constructor for the CustomButton class.
     *
     * @param text the text to be displayed on the button.
     * @author Oleksandr Danchenko
     */
    public CustomButton(String text) {
        super(text);
        repaint();
        setBackground(BUTTON_BLUE);
        setBorderPainted(false);
        setForeground(Color.WHITE);
        setFont(new Font(Font.SANS_SERIF, Font.BOLD, DEFAULT_FONT_SIZE));
    }

    /**
     * An overloaded constructor, creates a button with a specified font size.
     *
     * @param text     the text on the button.
     * @param fontSize the font size of the text on the button.
     */
    public CustomButton(String text, int fontSize) {
        this(text);
        setFont(new Font(Font.SANS_SERIF, Font.BOLD, fontSize));
    }

    /**
     * A method that draws the button.
     *
     * @param g the Graphics class instance.
     * @author Oleksandr Danchenko
     */
    @Override
    public void paint(Graphics g) {
        int w = getWidth(), h = getHeight();
        g.setColor(CustomPanel.BACKGROUND_WHITE);
        g.drawRect(0, 0, w, h);
        g.setColor(BUTTON_BLUE);
        g.fillRoundRect((int) (w * 0.05), (int) (h * 0.05), (int) (w * 0.9), (int) (h * 0.9), 7, 7);
        super.paint(g);
    }
}