package com.dauflo.gui;

import com.dauflo.dao.JdbcTripDao;
import com.dauflo.entity.Trip;
import com.dauflo.utils.ParadiseSorter;


import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.util.Locale;

public class DashBoardFrame extends JFrame {
    private JTable table;
    private static ParadiseTableModel paradiseTableModel;
    private static List<Trip> trips;
    public static JdbcTripDao tripDao;

    public DashBoardFrame() throws HeadlessException {

        Locale.setDefault(new Locale("en", "US"));

        this.setTitle("Java Paradise - DashBoard");
        this.setSize(600, 400);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setJMenuBar(new ParadiseMenuBar());

        // Table
        tripDao = new JdbcTripDao();
        trips = tripDao.getTrips();

        table = new JTable();
        paradiseTableModel = new ParadiseTableModel(trips);
        table.setModel(paradiseTableModel);
        table.getTableHeader().setReorderingAllowed(false);
        table.setRowSorter(new ParadiseSorter(table.getModel()));
        JScrollPane scrollPane = new JScrollPane(table);

        this.add(new SearchPanel(), BorderLayout.PAGE_START);

        this.add(scrollPane, BorderLayout.CENTER);

        this.setVisible(true);
    }

    public static void updateTable() {
        trips = tripDao.getTrips();
        paradiseTableModel.setTrips(trips);
        paradiseTableModel.fireTableDataChanged();
    }

    public static void updateTable(List<Trip> trips) {
        paradiseTableModel.setTrips(trips);
        paradiseTableModel.fireTableDataChanged();
    }
}
