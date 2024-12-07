package com.metro_pos.View.Login;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private JPanel mainPanel;
    private CardLayout cardLayout;

    public MainFrame() {
        setTitle("Metro POS System - Login");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        JPanel welcomePanel = new JPanel(new GridBagLayout());
        JLabel welcomeLabel = new JLabel("Welcome to Metro", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 36));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        welcomePanel.add(welcomeLabel, gbc);

        mainPanel.add(welcomePanel, "Welcome");
        mainPanel.add(new LoginPanel("Admin"), "Admin Login");
        mainPanel.add(new LoginPanel("Manager"), "Manager Login");
        mainPanel.add(new LoginPanel("Cashier"), "Cashier Login");
        mainPanel.add(new LoginPanel("Data Operator"), "Data Operator Login");

        ChooseRolePanelView sidebarPanel = new ChooseRolePanelView(cardLayout, mainPanel);

        add(sidebarPanel, BorderLayout.WEST);
        add(mainPanel, BorderLayout.CENTER);

        setVisible(true);
        setLocationRelativeTo(null);

    }

}
