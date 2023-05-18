package panels;

import frame.ApplicationFrame;
import graphics.LoadingBar;

import java.awt.*;

public class LoadingPanel extends CustomPanel {
    LoadingBar loadingBar = new LoadingBar();

    public LoadingPanel(ApplicationFrame applicationFrame) {
        super(applicationFrame);
        buttonPanel.setVisible(false);
        centerPanel.setLayout(new BorderLayout());
        centerPanel.add(loadingBar, BorderLayout.CENTER);
    }

    public void showSplashScreen() {
        for (int i = 0; i < 2000000000; i++) {
            if (i % 100000000 == 0) loadingBar.update(i / 6000000);
        }
        applicationFrame.switchToHome();
    }
}
