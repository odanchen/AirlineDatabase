package gui.graphics;

import database.interaction.DataReader;
import gui.components.CustomPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class HomeImage extends JComponent {
    private final BufferedImage image;
    public HomeImage(String filename) {
        image = DataReader.readImage(filename);
        setPreferredSize(new Dimension(460, 450));
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(CustomPanel.backgroundWhite);
        g.drawImage(image, 0, 0, null);
        System.out.println(getHeight());
        System.out.println(getWidth());
    }
}
