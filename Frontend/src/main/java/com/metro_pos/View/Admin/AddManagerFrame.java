package com.metro_pos.View.Admin;

import javax.swing.*;
import com.metro_pos.Controller.ManagerController;
import com.metro_pos.Controller.BranchController;
import java.awt.*;
import java.util.List;

public class AddManagerFrame extends JFrame {

    public AddManagerFrame() {
        // Set up the frame
        setTitle("Add Manager");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 600); // Adjusted size due to phone and address field removal
        setLocationRelativeTo(null); // Center the frame
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Define larger font
        Font largeFont = new Font("Arial", Font.PLAIN, 18);

        // Create and add form labels and fields
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(largeFont);
        JTextField nameField = new JTextField(20);
        nameField.setFont(largeFont);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(largeFont);
        JTextField emailField = new JTextField(20);
        emailField.setFont(largeFont);

        JLabel branchCodeLabel = new JLabel("Branch Code:");
        branchCodeLabel.setFont(largeFont);

        // Create a dropdown for branch codes
        JComboBox<Integer> branchCodeComboBox = new JComboBox<>();
        branchCodeComboBox.setFont(largeFont);
        populateBranchCodes(branchCodeComboBox); // Populate the dropdown with branch codes

        JLabel salaryLabel = new JLabel("Salary:");
        salaryLabel.setFont(largeFont);
        JTextField salaryField = new JTextField(20);
        salaryField.setFont(largeFont);

        // Add components to the frame
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(nameLabel, gbc);
        gbc.gridx = 1;
        add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(emailLabel, gbc);
        gbc.gridx = 1;
        add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(branchCodeLabel, gbc);
        gbc.gridx = 1;
        add(branchCodeComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(salaryLabel, gbc);
        gbc.gridx = 1;
        add(salaryField, gbc);

        // Create and add the submit button
        JButton submitButton = new JButton("Submit");
        submitButton.setFont(largeFont);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(submitButton, gbc);

        // Create and add the back button
        JButton backButton = new JButton("Back");
        backButton.setFont(largeFont);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        add(backButton, gbc);

        // Add action listener for the submit button
        submitButton.addActionListener(e -> {
            if (validateInputs(nameField, emailField, branchCodeComboBox, salaryField)) {
                // Retrieve input from fields
                String name = nameField.getText().trim();
                String email = emailField.getText().trim();
                Integer branchCode = (Integer) branchCodeComboBox.getSelectedItem();
                double salary = Double.parseDouble(salaryField.getText().trim());

                // Call the controller to handle adding the manager
                ManagerController managerController = new ManagerController();
                boolean isAdded = managerController.addManager(name, email, branchCode, salary, "Manager");

                // Provide feedback based on the operation result
                if (isAdded) {
                    JOptionPane.showMessageDialog(this, "Manager added successfully!");
                    dispose(); // Close the frame
                } else {
                    JOptionPane.showMessageDialog(this, "email already exists . Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Add action listener for the back button
        backButton.addActionListener(e -> dispose());

        // Display the frame
        setVisible(true);
    }

    private void populateBranchCodes(JComboBox<Integer> branchCodeComboBox) {
        BranchController branchController = new BranchController();
        List<Integer> branchCodes = branchController.getUnassignedBranchCodes();

        if (branchCodes != null) {
            for (Integer code : branchCodes) {
                branchCodeComboBox.addItem(code);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Failed to retrieve branch codes.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean validateInputs(JTextField nameField, JTextField emailField, JComboBox<Integer> branchCodeComboBox, JTextField salaryField) {
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String salary = salaryField.getText().trim();
        Integer branchCode = (Integer) branchCodeComboBox.getSelectedItem();

        // Validate name
        if (name.isEmpty() || !name.matches("[a-zA-Z\\s]+")) {
            JOptionPane.showMessageDialog(this, "Please enter a valid name (letters and spaces only).", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Validate email
        String emailPattern = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        if (email.isEmpty() || !email.matches(emailPattern)) {
            JOptionPane.showMessageDialog(this, "Please enter a valid email address.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Validate branch code
        if (branchCode == null) {
            JOptionPane.showMessageDialog(this, "Please select a branch code.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Validate salary
        try {
            double salaryDouble = Double.parseDouble(salary);
            if (salaryDouble <= 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid positive number for salary.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        new AddManagerFrame(); // Create an instance of AddManagerFrame
    }
}
