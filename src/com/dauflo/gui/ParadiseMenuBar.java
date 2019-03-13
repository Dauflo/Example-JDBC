package com.dauflo.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ResourceBundle;

public class ParadiseMenuBar extends JMenuBar {
    public ParadiseMenuBar() {
        ResourceBundle bundle = ResourceBundle.getBundle("com.dauflo.lang.lang");

        JMenu menu = new JMenu("Menu");
        menu.setMnemonic(KeyEvent.VK_M);

        JMenuItem addPlace = new JMenuItem(new AbstractAction("Add a Place") {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new AddPlaceFrame();
            }
        });
        menu.add(addPlace);

        JMenuItem addTrip = new JMenuItem(new AbstractAction(bundle.getString("addTrip")) {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new AddTripFrame();
            }
        });
        menu.add(addTrip);

        JMenuItem quit = new JMenuItem(new AbstractAction("Quit") {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(3);
            }
        });
        menu.add(quit);

        this.add(menu);
    }
}
