/*
Author: Aidan Baker
time spent: 15 minutes
version #1
*/
package gui.panels;

import gui.ApplicationFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

/**
 * The FlightListPanel class, displays the list of flights.
 * @see gui.panels.CustomPanel
 * @author Aidan Baker
 */
public class FlightListPanel extends CustomPanel {
    private DefaultTableModel tableModel = new DefaultTableModel(
            new Object[][]{},
            new String[]{"Origin", "Destination", "Date", "Time (est)", "# Seats Available", "Status"}
    );
    private JTable table = new JTable(tableModel);

    public FlightListPanel(ApplicationFrame applicationFrame) {
        super(applicationFrame);
        setTitle("Flights");
        //table
        setupTable();
    }

    public void setupTable() {
        table.setDefaultEditor(Object.class, null);
        table.setGridColor(Color.BLACK);

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Times New Roman", Font.BOLD, 20));
        header.setBackground(Color.lightGray);

        JScrollPane scrollPane = new JScrollPane(table);

        centerPanel.setLayout(new GridLayout(1, 1));
        centerPanel.add(scrollPane, BorderLayout.CENTER);
    }
}
