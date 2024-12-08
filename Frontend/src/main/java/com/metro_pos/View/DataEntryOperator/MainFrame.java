package com.metro_pos.View.DataEntryOperator;

import javax.swing.*;

import com.metro_pos.Store.UserStore;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private String deoName;

    public MainFrame() {
        this.deoName = UserStore.getCurrentUser().getName();

        // Set frame properties
        setTitle("Metro POS System - Data Entry Operator");
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
        JLabel label = new JLabel("DEO " + deoName, SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 32));
        label.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        labelPanel.add(label, BorderLayout.CENTER);
        add(labelPanel, BorderLayout.NORTH);

        JButton addButton = new JButton("Add Product");
        addButton.setFont(new Font("Arial", Font.BOLD, 24));
        addButton.setMargin(new Insets(20, 20, 20, 20));
        addButton.setFocusPainted(false);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TypeOfVendor(MainFrame.this);
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

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.gridy = 0;
        gbc.gridx = 0;
        buttonPanel.add(addButton, gbc);
        gbc.gridy = 1;
        buttonPanel.add(cancelButton, gbc);
        add(buttonPanel, BorderLayout.CENTER);

    }

    public static void main(String[] args) {
        // Example usage
        new MainFrame();
    }
}
