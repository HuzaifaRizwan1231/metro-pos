package com.metro_pos.View.DataEntryOperator;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.metro_pos.Controller.DEOController;

class AddVendorDialogue extends JDialog {
    public AddVendorDialogue(JDialog parent) {
        super(parent, "Add Vendor", true);
        setSize(600, 500);
        setLocationRelativeTo(parent);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel nameLabel = new JLabel("Vendor Name:");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        JTextField nameField = new JTextField(20);
        nameField.setFont(new Font("Arial", Font.PLAIN, 16));

        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        JTextField phoneField = new JTextField(20);
        phoneField.setFont(new Font("Arial", Font.PLAIN, 16));

        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        JTextField addressField = new JTextField(20);
        addressField.setFont(new Font("Arial", Font.PLAIN, 16));

        JButton saveButton = new JButton("Save");
        saveButton.setFont(new Font("Arial", Font.BOLD, 16));
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String phone = phoneField.getText();
                String address = addressField.getText();

                boolean hasNonDigits = phone.matches(".*\\D.*"); // Checks if there is any non-digit character in the string
                if(name.isEmpty() || phone.isEmpty() || address.isEmpty()) {
                    JOptionPane.showMessageDialog(AddVendorDialogue.this, "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                else if(phone.length() != 10) {
                    JOptionPane.showMessageDialog(AddVendorDialogue.this, "Phone number must be 10 digits long", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                else if(hasNonDigits) {
                    JOptionPane.showMessageDialog(AddVendorDialogue.this, "Phone number must contain only digits", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                else{
                    // Save the vendor to the database
                    DEOController deoController = new DEOController();
                    int savedId = deoController.addVendor(name, phone, address);
                    if(savedId != -1){
                        JOptionPane.showMessageDialog(AddVendorDialogue.this, "Vendor saved successfully with ID" + savedId, "Success", JOptionPane.INFORMATION_MESSAGE);
                        new AddNewProduct(parent, Integer.toString(savedId));
                    }
                    else{
                        JOptionPane.showMessageDialog(AddVendorDialogue.this, "Failed to save vendor", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                dispose();
            }
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Arial", Font.BOLD, 16));
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(nameLabel, gbc);

        gbc.gridx = 1;
        add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(phoneLabel, gbc);

        gbc.gridx = 1;
        add(phoneField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(addressLabel, gbc);

        gbc.gridx = 1;
        add(addressField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(saveButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(cancelButton, gbc);

        setVisible(true);
    }
}