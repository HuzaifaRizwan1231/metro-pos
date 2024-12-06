package com.metro_pos.View.DataEntryOperator;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

class ChooseVendorDialogue extends JDialog {
    public ChooseVendorDialogue(JDialog parent) {
        super(parent, "Choose Vendor", true);
        setSize(600, 500);
        setLocationRelativeTo(parent);
        setLayout(new GridBagLayout());
        setResizable(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;

        // Add search bar at the top
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        JTextField searchBar = new JTextField();
        add(new JLabel("Search: "), gbc);

        gbc.gridx = 1;
        add(searchBar, gbc);

        // Add table in the middle
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        gbc.weighty = 1;
        String[] columnNames = {"ID", "Name", "Phone", "Address"};
        Object[][] data = {}; // Populate this with vendor data from the database
        JTable vendorTable = new JTable(data, columnNames);
        JScrollPane tableScrollPane = new JScrollPane(vendorTable);
        add(tableScrollPane, gbc);

        // Add "Choose" button at the bottom
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.weighty = 0;
        JButton chooseButton = new JButton("Choose");
        chooseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddNewProduct(parent);
                dispose();
            }
        });
        add(chooseButton, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        add(cancelButton, gbc);
        setVisible(true);
    }
}