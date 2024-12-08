package com.metro_pos.View.Cashier;

import javax.swing.*;

import com.metro_pos.Store.UserStore;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private String cashierName;

    public MainFrame() {
        this.cashierName = UserStore.getCurrentUser().getName();

        // Set frame properties
        setTitle("Metro POS System - Cashier");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Initialize UI components
        initUI();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initUI() {
        setLayout(new BorderLayout());

        JPanel labelPanel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Cashier " + cashierName, SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 32));
        label.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        labelPanel.add(label, BorderLayout.CENTER);
        add(labelPanel, BorderLayout.NORTH);

        JButton addButton = new JButton("Generate Sale");
        addButton.setFont(new Font("Arial", Font.BOLD, 24));
        addButton.setMargin(new Insets(20, 20, 20, 20));
        addButton.setFocusPainted(false);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GenerateSaleDialogue(MainFrame.this);
            }
        });

        JButton logoutButton = new JButton("Logout");
        logoutButton.setFont(new Font("Arial", Font.BOLD, 24));
        logoutButton.setMargin(new Insets(20, 20, 20, 20));
        logoutButton.setFocusPainted(false);
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current MainFrame
                new com.metro_pos.View.Login.MainFrame(); // Explicitly use the full package path
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.gridy = 0;
        gbc.gridx = 0;
        buttonPanel.add(addButton, gbc);
        gbc.gridy = 1;
        buttonPanel.add(logoutButton, gbc);
        add(buttonPanel, BorderLayout.CENTER);
        
    }

    public static void main(String[] args) {
        // Example usage
        new MainFrame();
    }
}
