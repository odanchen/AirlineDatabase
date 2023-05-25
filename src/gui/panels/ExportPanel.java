/*
Author: Aidan Baker
time spent: 5 minutes
Date: 23 May 2023
version #1
 */

package gui.panels;

import javax.swing.*;

import gui.ApplicationFrame;
import gui.components.CustomPanel;

import java.awt.*;

public class ExportPanel extends ScreenPanel {
    /**
     * The panel that contains the option buttons.
     */
    private CustomPanel optionButtons = new CustomPanel(BoxLayout.X_AXIS);


    /**
     * The button to sort the manifest by seat number.
     */
    private JButton sortBySeatNumber = new JButton("Sort by Seat #");;

    /**
     * The button to sort the manifest by name.
     */
    private JButton sortByName = new JButton("Sort by name");

    /**
     * The button to print the manifest.
     */
    private JButton printButton = new JButton("Print");

    /**
     * The path to the file containing the flight manifest
     */
    private String filePath;

    /**
     * The text area to display the flight manifest.
     */
    private JTextArea filePreview;


    public ExportPanel(ApplicationFrame applicationFrame) {
        super(applicationFrame, BoxLayout.Y_AXIS);
        setTitle("Flight Manifest");

        setBackButtonVisibility(true);

        setupButtons();

        filePreview = new JTextArea("File Preview here");
        filePreview.setPreferredSize(new Dimension(1000, 500));
        centerPanel.add(filePreview);
    }

    private void setupButtons() {
        addButton(sortBySeatNumber, "sortSeat",optionButtons);
        addButton(sortByName, "sortName",optionButtons);
        addButton(printButton, "print",optionButtons);
        centerPanel.add(optionButtons);
    }
}
