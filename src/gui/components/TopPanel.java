package gui.components;

import gui.ApplicationFrame;
import gui.graphics.Logo;

import javax.swing.*;
import java.awt.*;

public class TopPanel extends CustomPanel {
    private final CustomButton backButton;
    private final ApplicationFrame applicationFrame;
    private final JLabel placeholder = new JLabel("");
    private final JTextField title = new JTextField();
    public TopPanel(ApplicationFrame applicationFrame) {
        super(BoxLayout.X_AXIS);
        setPreferredSize(new Dimension(1400, 120));
        this.applicationFrame = applicationFrame;
        backButton = new CustomButton("<", "back", applicationFrame, 20);
        backButton.setVisible(false);
        backButton.setPreferredSize(new Dimension(50, 50));
        placeholder.setPreferredSize(new Dimension(51, 50));

        add(placeholder);
        add(backButton);
        add(new Logo());
        title.setBackground(CustomPanel.BACKGROUND_WHITE);
        title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 55));
        title.setForeground(CustomButton.BUTTON_BLUE);
        title.setEditable(false);
        title.setHorizontalAlignment(JTextField.CENTER);
        add(title);
    }

    /**
     * Sets the title of the screen to be a specified text.
     *
     * @param text the text in the title.
     * @author Aidan Baker
     */
    public void setTitle(String text) {
        title.setText(text);
        title.repaint();
    }

    /**
     * Makes the back button at the top left visible or invisible.
     *
     * @param visible whether the back button should be visible or not.
     */
    public void setBackButtonVisibility(boolean visible) {
        backButton.setVisible(visible);
        placeholder.setVisible(!visible);
    }
}
