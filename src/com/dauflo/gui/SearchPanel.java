package com.dauflo.gui;

import com.dauflo.dao.JdbcPlaceDao;
import com.dauflo.dao.JdbcTripDao;
import com.dauflo.entity.Place;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SearchPanel extends JPanel implements ActionListener {
    private JComboBox departureCombo;
    private JComboBox destinationCombo;
    private List<Place> places;

    public SearchPanel() {
        JdbcPlaceDao placeDao = new JdbcPlaceDao();

        places = placeDao.getPlaces();

        departureCombo = new JComboBox(new DefaultComboBoxModel(places.toArray()));
        destinationCombo = new JComboBox(new DefaultComboBoxModel(places.toArray()));

        departureCombo.addActionListener(this);
        destinationCombo.addActionListener(this);

        this.add(departureCombo);
        this.add(destinationCombo);

        FlowLayout flowLayout = new FlowLayout();
        this.setLayout(flowLayout);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Place departure = (Place) departureCombo.getSelectedItem();
        Place destination = (Place) destinationCombo.getSelectedItem();

        JdbcTripDao tripDao = new JdbcTripDao();

        DashBoardFrame.updateTable(tripDao.getTripsByDepAndDesId(departure.getId(), destination.getId()));
    }
}
