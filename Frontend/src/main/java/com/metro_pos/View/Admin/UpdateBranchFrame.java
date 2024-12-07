package com.metro_pos.View.Admin;

import javax.swing.*;

import com.metro_pos.Model.BranchModel;

import java.awt.*;

public class UpdateBranchFrame extends JFrame {

    private JTextField nameField;
    private JTextField cityField;
    private JTextField addressField;
    private JCheckBox isActiveCheckBox;
    private JTextField phoneField;

    public UpdateBranchFrame(int branchCode, String name, String city, String address, String phone, String isActive) {
        // Set up the frame
        setTitle("Update Branch");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 550); // Adjusted size
        setLocationRelativeTo(null); // Center the frame on the screen
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Define larger font
        Font largeFont = new Font("Arial", Font.PLAIN, 18);

        // Create and add form labels and fields with larger font
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(largeFont);
        nameField = new JTextField(20);
        nameField.setFont(largeFont);
        nameField.setText(name);

        JLabel cityLabel = new JLabel("City:");
        cityLabel.setFont(largeFont);
        cityField = new JTextField(20);
        cityField.setFont(largeFont);
        cityField.setText(city);

        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setFont(largeFont);
        addressField = new JTextField(20);
        addressField.setFont(largeFont);
        addressField.setText(address);

        JLabel isActiveLabel = new JLabel("Is Active:");
        isActiveLabel.setFont(largeFont);
        isActiveCheckBox = new JCheckBox();
        isActiveCheckBox.setFont(largeFont);
        isActiveCheckBox.setPreferredSize(new Dimension(30, 30)); // Making checkbox bigger
        isActiveCheckBox.setSelected(isActive.equals("1"));

        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setFont(largeFont);
        phoneField = new JTextField(20);
        phoneField.setFont(largeFont);
        phoneField.setText(phone);

        // Add components to the frame
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(nameLabel, gbc);
        gbc.gridx = 1;
        add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(cityLabel, gbc);
        gbc.gridx = 1;
        add(cityField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(addressLabel, gbc);
        gbc.gridx = 1;
        add(addressField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(isActiveLabel, gbc);
        gbc.gridx = 1;
        add(isActiveCheckBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(phoneLabel, gbc);
        gbc.gridx = 1;
        add(phoneField, gbc);

        // Create and add the submit button with larger font
        JButton submitButton = new JButton("Submit");
        submitButton.setFont(largeFont);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        add(submitButton, gbc);

        // Create and add the cancel button with larger font
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(largeFont);
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        add(cancelButton, gbc);

        // Add action listener for the submit button
        submitButton.addActionListener(e -> {
            // Retrieve updated values from the form
            String updatedName = nameField.getText().trim();
            String updatedCity = cityField.getText().trim();
            String updatedAddress = addressField.getText().trim();
            boolean updatedIsActive = isActiveCheckBox.isSelected();
            String updatedPhone = phoneField.getText().trim();

            // Perform input validation
            if (updatedName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Name field cannot be empty.", "Validation Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (updatedCity.isEmpty()) {
                JOptionPane.showMessageDialog(this, "City field cannot be empty.", "Validation Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (updatedAddress.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Address field cannot be empty.", "Validation Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (updatedPhone.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Phone field cannot be empty.", "Validation Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!updatedPhone.matches("\\d{10}$")) { // Basic phone validation (e.g., 123-456-7890)
                JOptionPane.showMessageDialog(this, "Phone number must be in the format .", "Validation Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Call the updateBranch method
            BranchModel branchModel = new BranchModel();
            branchModel.updateBranch(
                    branchCode, // Pass the branch code
                    updatedName, // Updated name
                    updatedCity, // Updated city
                    updatedAddress, // Updated address
                    updatedIsActive, // Updated active status
                    updatedPhone // Updated phone
            );

            // Show success message
            JOptionPane.showMessageDialog(this, "Branch updated successfully!");

            // Optionally, close the frame after successful update
            dispose();
        });

        // Add action listener for the cancel button
        cancelButton.addActionListener(e -> {
            // Logic to cancel the update and close the frame
            dispose();
        });

        // Display the frame
        setVisible(true);
    }

    public static void main(String[] args) {
        new UpdateBranchFrame(1, "Main Branch", "New York", "123 Main St", "123-456-7890", "1"); // Example usage
    }
}
