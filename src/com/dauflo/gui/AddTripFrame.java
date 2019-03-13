package com.dauflo.gui;

import com.dauflo.dao.JdbcPlaceDao;
import com.dauflo.dao.JdbcTripDao;
import com.dauflo.entity.Place;
import com.dauflo.entity.Trip;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AddTripFrame extends JFrame implements ActionListener {

    private JComboBox departure;
    private JComboBox destination;
    private JTextField price;
    private JButton button;
    private List<Place> places;

    public AddTripFrame() throws HeadlessException {
        this.setTitle("Add trip");

        JdbcPlaceDao placeDao = new JdbcPlaceDao();

        places = placeDao.getPlaces();

        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        departure = new JComboBox(new DefaultComboBoxModel(places.toArray()));
        departure.setPreferredSize(new Dimension(200, 30));
        destination = new JComboBox(new DefaultComboBoxModel(places.toArray()));
        destination.setPreferredSize(new Dimension(200, 30));
        price = new JTextField();
        price.setPreferredSize(new Dimension(50, 30));
        button = new JButton("Add trip");
        button.addActionListener(this);

        Container container = this.getContentPane();


        // Top
        JPanel topPanel = new JPanel(new GridLayout(3, 2));
        topPanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        topPanel.add(new JLabel("From :"));
        topPanel.add(departure);

        topPanel.add(new JLabel("To :"));
        topPanel.add(destination);

        topPanel.add(new JLabel("Price :"));
        topPanel.add(price);


        container.add(topPanel, BorderLayout.CENTER);

        // Bottom
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(button);
        bottomPanel.setLayout(new FlowLayout());

        container.add(bottomPanel, BorderLayout.PAGE_END);

        this.pack();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (!price.getText().trim().isEmpty()) {
            try {
                long priceLong = Long.parseLong(price.getText().trim());

                int departurePos = departure.getSelectedIndex();
                int destinationPos = destination.getSelectedIndex();

                JdbcTripDao tripDao = new JdbcTripDao();

                Place dep = places.get(departurePos);
                Place des = places.get(destinationPos);

                tripDao.create(new Trip(dep, des, priceLong));
                DashBoardFrame.updateTable();
                this.dispose();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
