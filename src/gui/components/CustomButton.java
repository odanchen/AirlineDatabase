/*
Author: Oleksandr Danchenko
time spent: 25 minutes
Date: 24 May 2023
version #1
 */

package gui.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

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
     * The color of the button.
     */
    private Color color = BUTTON_BLUE;

    /**
     * A constructor for the CustomButton class.
     *
     * @param text the text to be displayed on the button.
     * @author Oleksandr Danchenko
     */
    public CustomButton(String text, ActionListener listener) {
        super(text);
        addActionListener(listener);
        setBorderPainted(false);
        setForeground(Color.WHITE);
        setBackground(new Color(0, 0, 0, 0));
        setFont(new Font(Font.SANS_SERIF, Font.BOLD, DEFAULT_FONT_SIZE));
        repaint();
    }

    /**
     * An overloaded constructor, creates a button with a specified font size.
     *
     * @param text     the text on the button.
     * @param fontSize the font size of the text on the button.
     */
    public CustomButton(String text, ActionListener listener, int fontSize) {
        this(text, listener);
        setFont(new Font(Font.SANS_SERIF, Font.BOLD, fontSize));
    }

    public CustomButton(String text, String command, ActionListener listener, int fontSize) {
        this(text, listener, fontSize);
        setActionCommand(command);
    }

    public CustomButton(String text, String command, ActionListener listener) {
        this(text, listener);
        setActionCommand(command);
    }

    public CustomButton(String text, String command, ActionListener listener, int fontSize, Dimension dimension) {
        this(text, command, listener, fontSize);
        setPreferredSize(dimension);
    }

    /**
     * Sets the color used to paint the button.
     * @param color the color to be used to paint the button.
     */
    public void setColor(Color color) {
        this.color = color;
        repaint();
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
        g.fillRect(0, 0, w, h);
        g.setColor(color);
        g.fillRoundRect((int) (w * 0.05), (int) (h * 0.05), (int) (w * 0.9), (int) (h * 0.9), 7, 7);
        super.paint(g);
    }
}