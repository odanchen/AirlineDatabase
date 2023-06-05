/*
Author: Oleksandr Danchenko
time spent: 110 minutes
Date: 3 June 2023
version #2
Changes: added wings to the plane
 */

package gui.graphics;

import gui.components.CustomButton;
import gui.components.CustomPanel;
import gui.panels.SeatPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The SeatButtonPanel class represents the panel that contains the seat buttons in the SeatPanel.
 *
 * @author Oleksandr Danchenko
 */
public class SeatButtonPanel extends CustomPanel implements ActionListener {
    /**
     * The reference to the seat panel.
     */
    private final SeatPanel seatPanel;
    /**
     * The buttons on the screen.
     */
    private final CustomButton[] seatButtons = new CustomButton[10];

    /**
     * Creates a new instance of SeatButtonPanel.
     * Initializes the panel and adds the seat buttons.
     * Sets the background color and layout of the panel.
     *
     * @param seatPanel The SeatPanel associated with this SeatButtonPanel.
     * @author Oleksandr Danchenko
     */
    public SeatButtonPanel(SeatPanel seatPanel) {
        super(new BorderLayout());
        setPreferredSize(new Dimension(1400, 300));
        setBackground(new Color(0, 0, 0, 0));
        this.seatPanel = seatPanel;
        addButtons();
        fitSeatButtons();
    }

    /**
     * Adds the seat buttons to the panel.
     *
     * @author Oleksandr Danchenko
     */
    private void addButtons() {
        CustomPanel buttons = new CustomPanel(new GridLayout(2, 10));
        buttons.setBackground(new Color(0, 0, 0, 0));
        for (int i = 0; i < 10; i++) {
            seatButtons[i] = new CustomButton(String.valueOf(i + 1), String.valueOf(i + 1), this);
            seatButtons[i].setBackgroundColor(Color.WHITE);
            buttons.add(seatButtons[i]);
            buttons.add(new JLabel());
        }
        add(buttons, BorderLayout.CENTER);
    }

    /**
     * Adds placeholders in each location of the BorderLayout to push the seat buttons in their correct position.
     *
     * @author Oleksandr Danchenko
     */
    private void fitSeatButtons() {
        JLabel northHolder = new JLabel();
        northHolder.setPreferredSize(new Dimension(1000, 115));
        add(northHolder, BorderLayout.NORTH);
        JLabel southHolder = new JLabel();
        southHolder.setPreferredSize(new Dimension(1000, 210));
        add(southHolder, BorderLayout.SOUTH);
        JLabel eastHolder = new JLabel();
        eastHolder.setPreferredSize(new Dimension(210, 400));
        add(eastHolder, BorderLayout.EAST);
        JLabel westHolder = new JLabel();
        westHolder.setPreferredSize(new Dimension(150, 400));
        add(westHolder, BorderLayout.WEST);
    }

    /**
     * Colors the seat buttons depending on whether the seats they represent are taken or not.
     *
     * @author Oleksandr Danchenko
     */
    public void colorSeatButtons() {
        for (int i = 0; i < seatButtons.length; i++) {
            if (seatPanel.getFlight().getSeating()[i].isEmpty()) seatButtons[i].setColor(CustomButton.BUTTON_BLUE);
            else seatButtons[i].setColor(Color.GRAY);
        }
    }

    /**
     * Paints the panel with the plane on it.
     * @param g the instance of the Graphics class.
     *
     * @author Oleksandr Danchenko
     */
    public void paint(Graphics g) {
        g.setColor(BACKGROUND_WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.WHITE);
        g.fillRect(150, 100, 900, 150);
        g.fillArc(900, 100, 300, 150, 90, -180);
        g.fillArc(100, 100, 110, 150, 90, 180);
        g.fillPolygon(new int[]{550, 550, 400, 450, 900}, new int[]{250, 270, 330, 340, 250}, 5);
        g.fillPolygon(new int[]{550, 550, 400, 450, 900}, new int[]{100, 80, 20, 10, 100}, 5);
        g.setColor(Color.CYAN);
        g.fillPolygon(new int[]{1115, 1130, 1160, 1145}, new int[]{140, 125, 140, 155}, 4);
        g.fillPolygon(new int[]{1115, 1130, 1160, 1145}, new int[]{212, 227, 212, 197}, 4);
        g.fillRect(1155, 160, 20, 30);
        g.setColor(Color.black);
        g.drawPolygon(new int[]{1115, 1130, 1160, 1145}, new int[]{140, 125, 140, 155}, 4);
        g.drawPolygon(new int[]{1115, 1130, 1160, 1145}, new int[]{212, 227, 212, 197}, 4);
        g.drawRect(1155, 160, 20, 30);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(6));
        g2d.drawLine(150, 100, 1050, 100);
        g2d.drawLine(150, 250, 1050, 250);
        g2d.drawArc(900, 100, 300, 150, 90, -180);
        g2d.drawArc(100, 100, 110, 150, 90, 180);
        g.drawPolygon(new int[]{550, 550, 400, 450, 900}, new int[]{250, 270, 330, 340, 250}, 5);
        g.drawPolygon(new int[]{550, 550, 400, 450, 900}, new int[]{100, 80, 20, 10, 100}, 5);
        g2d.drawLine(150, 100, 1050, 100);
        g2d.drawLine(150, 250, 1050, 250);
        g2d.drawArc(900, 100, 300, 150, 90, -180);
        g2d.drawArc(95, 100, 110, 150, 90, 180);
        super.paint(g);
    }

    /**
     * A method that is called when one of the buttons is pressed
     *
     * @param e the event to be processed
     * @author Olekandr Danchenko
     */
    public void actionPerformed(ActionEvent e) {
        seatPanel.actionPerformed(e);
    }
}
