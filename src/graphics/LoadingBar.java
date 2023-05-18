package graphics;

import javax.swing.*;
import java.awt.*;

public class LoadingBar extends JComponent {
    private int pixelCnt = 0;

    public LoadingBar() {
    }

    public void update(int pixelCnt) {
        this.pixelCnt = pixelCnt;
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(getWidth() / 3, getHeight() / 2, pixelCnt, 40);
    }
}
