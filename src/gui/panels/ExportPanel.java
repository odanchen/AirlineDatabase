package gui.panels;

import javax.swing.*;

import gui.ApplicationFrame;

import java.awt.*;

public class ExportPanel extends CustomPanel {
    /**
     * The panel that contains the option buttons.
     */
    private JPanel optionButtons;

    /**
     * The button to go back to the previous panel.
     */
    private JButton backButton;

    /**
     * The button to sort the manifest by seat number.
     */
    private JButton sortBySeatNumber;

    /**
     * The button to sort the manifest by name.
     */
    private JButton sortByName;

    /**
     * The button to print the manifest.
     */
    private JButton printButton;

    /**
     * The path to the file containing the flight manifest
     */
    private String filePath;

    /**
     * The text area to display the flight manifest.
     */
    private JTextArea filePreview;


    public ExportPanel(ApplicationFrame applicationFrame) {
        super(applicationFrame);
        setTitle("Flight Manifest");

        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        setupButtons();

        filePreview = new JTextArea("File Preview here");
        filePreview.setPreferredSize(new Dimension(1000, 500));
        centerPanel.add(filePreview);
    }

    private void setupButtons() {
        optionButtons = new JPanel();
        optionButtons.setLayout(new BoxLayout(optionButtons, BoxLayout.X_AXIS));

        backButton = new JButton("Back");
        backButton.setActionCommand("back");
        backButton.addActionListener(this);
        optionButtons.add(backButton);

        sortBySeatNumber = new JButton("Sort by Seat #");
        sortBySeatNumber.setActionCommand("sort by number");
        sortBySeatNumber.addActionListener(this);
        optionButtons.add(sortBySeatNumber);

        sortByName = new JButton("Sort by Name");
        sortByName.setActionCommand("sort by name");
        sortByName.addActionListener(this);
        optionButtons.add(sortByName);

        printButton = new JButton("Print");
        printButton.setActionCommand("print");
        printButton.addActionListener(this);
        optionButtons.add(printButton);

        centerPanel.add(optionButtons);
    }
}
