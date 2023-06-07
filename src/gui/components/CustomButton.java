/*
Author: Oleksandr Danchenko
time spent: 25 minutes
Date: 24 May 2023
version #2
Changes: added overloaded constructors for convenience
    time spent: 10 minutes.
    Date: 31 May 2023
    Author Oleksandr Danchenko
 */

package gui.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * A custom button class, a button that is designed to look in the style of the app.
 *
 * @author Oleksandr Danchenko
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
     * The background color of the button.
     */
    private Color background = CustomPanel.BACKGROUND_WHITE;

    /**
     * A constructor for the CustomButton class.
     *
     * Citation: https://docs.oracle.com/javase/8/docs/api/javax/swing/AbstractButton.html#setBorderPainted-boolean-
     *      The setBorderPainted() method is used to disable the painting of the button border.
     * Citation: https://docs.oracle.com/javase/8/docs/api/javax/swing/JComponent.html#setForeground-java.awt.Color-
     *      The setForeground() method is used to change the color of text on the button.
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
     * @author Oleksandr Danchenko
     */
    public CustomButton(String text, ActionListener listener, int fontSize) {
        this(text, listener);
        setFont(new Font(Font.SANS_SERIF, Font.BOLD, fontSize));
    }

    /**
     * Creates a button with the selected parameters.
     *
     * @param text     the text displayed on the button.
     * @param command  the action command of the button.
     * @param listener the action listener of the button.
     * @param fontSize the font size of the text on the button.
     * @author Oleksandr Danchenko
     */
    public CustomButton(String text, String command, ActionListener listener, int fontSize) {
        this(text, listener, fontSize);
        setActionCommand(command);
    }

    /**
     * Creates a button with the selected parameters.
     *
     * @param text     the text displayed on the button.
     * @param command  the action command of the button.
     * @param listener the action listener of the button.
     * @author Oleksandr Danchenko
     */
    public CustomButton(String text, String command, ActionListener listener) {
        this(text, command, listener, DEFAULT_FONT_SIZE);
    }

    /**
     * Creates a button with the selected parameters.
     *
     * Citation: https://docs.oracle.com/javase/8/docs/api/javax/swing/JComponent.html#setPreferredSize-java.awt.Dimension-
     * Citation: https://docs.oracle.com/javase/8/docs/api/java/awt/Dimension.html
     *      The setPreferredSize() method is used to specify the preferred size of the component.
     *      Here, it is used to specify the approximate size of the button for its container's layout manager.
     *      The Dimension class is used as a parameter and contains the preferred width and height of the component respectively.
     * @param text      the text displayed on the button.
     * @param command   the action command of the button.
     * @param listener  the action listener of the button.
     * @param fontSize  the font size of the text on the button.
     * @param dimension the preferred size of the button.
     * @author Oleksandr Danchenko
     */
    public CustomButton(String text, String command, ActionListener listener, int fontSize, Dimension dimension) {
        this(text, command, listener, fontSize);
        setPreferredSize(dimension);
    }

    /**
     * Sets the color used to paint the button.
     *
     * @param color the color to be used to paint the button.
     * @author Oleksandr Danchenko
     */
    public void setColor(Color color) {
        this.color = color;
        repaint();
    }

    /**
     * Sets the background color used to paint the button.
     *
     * @param color the color to be used to paint the button.
     * @author Oleksandr Danchenko
     */
    public void setBackgroundColor(Color color) {
        this.background = color;
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
        g.setColor(background);
        g.fillRect(0, 0, w, h);
        g.setColor(color);
        g.fillRoundRect((int) (w * 0.05), (int) (h * 0.05), (int) (w * 0.9), (int) (h * 0.9), 7, 7);
        super.paint(g);
    }
}