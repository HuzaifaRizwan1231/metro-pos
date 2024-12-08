package com.metro_pos.View.Admin;

import javax.swing.*;

import com.metro_pos.View.DataEntryOperator.MainFrame;

import java.awt.*;

public class AdminFrame extends JFrame {

    // Constructor to set up the frame
    public AdminFrame() {
        // Set up the frame
        setTitle("Admin Panel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Set the frame to full screen

        // Enable default decorations (includes minimize, maximize, and close buttons)

        // Set layout manager
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding

        // Define larger font and button styling
        Font buttonFont = new Font("Arial", Font.PLAIN, 18);
        Dimension buttonSize = new Dimension(400, 100);

        // Create and style buttons
        JButton manageBranchButton = new JButton("Manage Branch");
        manageBranchButton.setPreferredSize(buttonSize);
        manageBranchButton.setFont(buttonFont);
        
        JButton manageBankButton = new JButton("Manage Branch Manager");
        manageBankButton.setPreferredSize(buttonSize);
        manageBankButton.setFont(buttonFont);
        
        JButton managerReportsButton = new JButton("Manager Reports");
        managerReportsButton.setPreferredSize(buttonSize);
        managerReportsButton.setFont(buttonFont);
        
        JButton logoutButton = new JButton("Logout");
        logoutButton.setPreferredSize(buttonSize);
        logoutButton.setFont(buttonFont);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        add(manageBranchButton, gbc);

        gbc.gridy = 1;
        add(manageBankButton, gbc);

        gbc.gridy = 2;
        add(managerReportsButton, gbc);

        gbc.gridy = 3;
        add(logoutButton, gbc);

        // Add action listeners for buttons
        manageBranchButton.addActionListener(e -> {
            new ManageBranchFrame();
            dispose();
        });

        manageBankButton.addActionListener(e -> {
            new ManageBranchManagerFrame();
            dispose();
        });

        managerReportsButton.addActionListener(e -> {
            new GenerateReport(this);
        });

        logoutButton.addActionListener(e -> {
            dispose();
            new com.metro_pos.View.Login.MainFrame();
        });

        // Display the frame
        setVisible(true);
    }

}
