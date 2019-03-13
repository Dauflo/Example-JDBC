package com.dauflo.gui;

import com.dauflo.dao.JdbcPlaceDao;
import com.dauflo.entity.Place;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddPlaceFrame extends JFrame implements ActionListener{
    private JTextField text;

    public AddPlaceFrame() throws HeadlessException {
        this.setTitle("Add place");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        text = new JTextField();
        text.setPreferredSize(new Dimension(200, 30));
        JButton button = new JButton("Add place");
        button.addActionListener(this);

        Container container = this.getContentPane();
        FlowLayout flowLayout = new FlowLayout();

        container.add(text);
        container.add(button);

        container.setLayout(flowLayout);

        this.pack();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (!text.getText().trim().isEmpty()) {
            String placeName = text.getText();
            JdbcPlaceDao placeDao = new JdbcPlaceDao();
            placeDao.create(new Place(placeName));
            JOptionPane.showMessageDialog(this, String.format("Place added into DB : %s", placeName));
            this.dispose();
        }
    }
}
