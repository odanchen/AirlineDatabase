package panels;

import frame.ApplicationFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class FlightListPanel extends CustomPanel {
    private DefaultTableModel tableModel = new DefaultTableModel(
            new Object[][]{},
            new String[]{"Origin", "Destination", "Date", "Time (est)", "# Seats Available", "Status"}
    );
    private JTable table = new JTable(tableModel);

    public FlightListPanel(ApplicationFrame applicationFrame) {
        super(applicationFrame);

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

        centerPanel.add(scrollPane, BorderLayout.CENTER);
    }
}
