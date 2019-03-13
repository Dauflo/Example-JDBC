package com.dauflo.gui;

import com.dauflo.entity.Trip;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ParadiseTableModel extends AbstractTableModel {
    private List<Trip> trips;
    private String[] colsName = {"ID", "Departure", "Destination", "Price"};

    public ParadiseTableModel(List<Trip> trips) {
        this.trips = trips;
    }

    @Override
    public String getColumnName(int i) {
        return colsName[i];
    }

    @Override
    public int getRowCount() {
        return trips.size();
    }

    @Override
    public int getColumnCount() {
        return colsName.length;
    }

    @Override
    public Class<?> getColumnClass(int i) {
        switch (i) {
            case 0:
                return Long.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return Long.class;
        }
        return  null;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Trip trip = trips.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return trip.getId();
            case 1:
                return trip.getDeparture().getName();
            case 2:
                return trip.getDestination().getName();
            case 3:
                return trip.getPrice();
        }
        return null;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }
}
