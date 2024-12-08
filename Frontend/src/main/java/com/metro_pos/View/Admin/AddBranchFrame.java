package com.metro_pos.View.Admin;

import javax.swing.*;

import com.metro_pos.Controller.BranchController;

import java.awt.*;

public class AddBranchFrame extends JFrame {
    BranchController b = new BranchController();

    public AddBranchFrame() {
        // Set up the frame
        setTitle("Add Branch");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 700); // Increased size
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
        JTextField nameField = new JTextField(20);
        nameField.setFont(largeFont);

        JLabel cityLabel = new JLabel("City:");
        cityLabel.setFont(largeFont);
        JTextField cityField = new JTextField(20);
        cityField.setFont(largeFont);

        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setFont(largeFont);
        JTextField addressField = new JTextField(20);
        addressField.setFont(largeFont);

        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setFont(largeFont);
        JTextField phoneField = new JTextField(20);
        phoneField.setFont(largeFont);

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
        add(phoneLabel, gbc);
        gbc.gridx = 1;
        add(phoneField, gbc);

        // Create and add the submit button with larger font
        JButton submitButton = new JButton("Submit");
        submitButton.setFont(largeFont);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(submitButton, gbc);

        // Create and add the back button with larger font
        JButton backButton = new JButton("Back");
        backButton.setFont(largeFont);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        add(backButton, gbc);

        // Add action listener for the submit button
        submitButton.addActionListener(e -> {
            // Get values from input fields
            String name = nameField.getText();
            String city = cityField.getText();
            String address = addressField.getText();
            String phone = phoneField.getText();

            // Validation checks
            if (name.isEmpty() || city.isEmpty() || address.isEmpty() || phone.isEmpty()) {
                // Show error if any of the required fields are empty
                JOptionPane.showMessageDialog(this, "Please fill out all fields.");
                return; // Exit the action listener if validation fails
            }

            // Validate phone number format (simple example, you can adjust this regex)
            String phoneRegex = "^\\d{10}$"; // Assumes 10-digit phone numbers
            if (!phone.matches(phoneRegex)) {
                // Show error if phone format is invalid
                JOptionPane.showMessageDialog(this, "Please enter a valid 10-digit phone number.");
                return; // Exit the action listener if validation fails
            }

            // If all validations pass, proceed with adding the branch
            JOptionPane.showMessageDialog(this, "Branch added successfully!");
            b.addBranch(name, city, address, phone);

            // Close the dialog or window
            dispose();
        });

        // Add action listener for the back button
        backButton.addActionListener(e -> {
            // Logic to go back to the previous screen
            dispose(); // Close the current frame
        });

        // Display the frame
        setVisible(true);
    }

    public static void main(String[] args) {
        new AddBranchFrame(); // Create an instance of AddBranchFrame
    }
}
