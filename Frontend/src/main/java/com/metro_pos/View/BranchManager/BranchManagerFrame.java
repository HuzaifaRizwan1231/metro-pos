package com.metro_pos.View.BranchManager;

import javax.swing.*;

import com.metro_pos.View.Login.MainFrame;

import java.awt.*;
import java.sql.Connection;

public class BranchManagerFrame extends JFrame {

    // Constructor to set up the frame
    public BranchManagerFrame() {
        // Set up the frame
        setTitle("Branch Manager Panel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Set the frame to full screen

        // Set layout manager
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding

        // Define larger font and button styling
        Font buttonFont = new Font("Arial", Font.PLAIN, 18);
        Dimension buttonSize = new Dimension(400, 100);

        // Create and style buttons
        JButton manageEmployeeButton = new JButton("Manage Employee");
        manageEmployeeButton.setPreferredSize(buttonSize);
        manageEmployeeButton.setFont(buttonFont);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setPreferredSize(buttonSize);
        logoutButton.setFont(buttonFont);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        add(manageEmployeeButton, gbc);

        gbc.gridy = 1;
        add(logoutButton, gbc);

        // Add action listeners for buttons
        manageEmployeeButton.addActionListener(e -> {
            new ManageEmployeeFrame();
            dispose();
        });

        logoutButton.addActionListener(e -> {
            dispose();
            new MainFrame();
        });

        // Display the frame
        setVisible(true);
    }

    // Main method to launch the application
    public static void main(String[] args) {
        new BranchManagerFrame(); // Create an instance of BranchManagerFrame
    }
}
