package com.metro_pos.View.DataEntryOperator;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

class TypeOfVendor extends JDialog {
    public TypeOfVendor(JFrame parent) {
        super(parent, "Add Product", true);

        setLayout(new GridBagLayout());
        setSize(500, 350);
        setLocationRelativeTo(parent);
        setResizable(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel prompt = new JLabel("Choose an option:", SwingConstants.CENTER);
        prompt.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(prompt, gbc);

        JButton newVendorButton = new JButton("New Vendor");
        newVendorButton.setFont(new Font("Arial", Font.BOLD, 24));
        newVendorButton.setMargin(new Insets(20, 20, 20, 20)); 
        newVendorButton.setFocusPainted(false);
        newVendorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open AddVendorDialogue
                new AddVendorDialogue(TypeOfVendor.this);
            }
        });

        JButton existingVendorButton = new JButton("Existing Vendor");
        existingVendorButton.setFont(new Font("Arial", Font.BOLD, 24));
        existingVendorButton.setMargin(new Insets(20, 20, 20, 20)); 
        existingVendorButton.setFocusPainted(false);
        existingVendorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open ChooseVendorDialogue
                new ChooseVendorDialogue(TypeOfVendor.this);
            }
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Arial", Font.BOLD, 24));
        cancelButton.setMargin(new Insets(20, 20, 20, 20)); 
        cancelButton.setFocusPainted(false);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.gridx = 0;
        add(newVendorButton, gbc);

        gbc.gridx = 1;
        add(existingVendorButton, gbc);

        gbc.gridwidth = 2;
        gbc.gridy = 2;
        gbc.gridx = 0;
        add(cancelButton, gbc);

        setVisible(true);
    }
}