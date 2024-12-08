package com.metro_pos.View.Admin;

import javax.swing.*;

import com.metro_pos.Controller.AdminController;

import java.awt.*;
import java.util.List;

public class GenerateReport extends JDialog {

    private JLabel totalCostLabel;
    private JLabel totalSalesLabel;
    private JLabel totalProfitLabel;
    private AdminController adminController;

    public GenerateReport(Frame parent) {
        super(parent, "Generate Report", true);
        this.adminController = new AdminController();
        setSize(600, 500);
        setLayout(new BorderLayout());
        setResizable(false);

        List<Integer> branchIds = new AdminController().getBranchCodes();

        JLabel titleLabel = new JLabel("GENERATE REPORT", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        add(titleLabel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Dropdown Menu
        JLabel dropdownLabel = new JLabel("Select Branch:");
        dropdownLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(dropdownLabel, gbc);

        JComboBox<String> branchDropdown = new JComboBox<>();
        branchDropdown.setFont(new Font("Arial", Font.PLAIN, 18));
        branchDropdown.addItem("ALL");
        for (int branch : branchIds) {
            branchDropdown.addItem(String.valueOf(branch));
        }
        gbc.gridx = 1;
        gbc.gridy = 0;
        centerPanel.add(branchDropdown, gbc);

        // Labels for totals
        totalCostLabel = new JLabel("Total Cost: $0.00");
        totalCostLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        centerPanel.add(totalCostLabel, gbc);

        totalSalesLabel = new JLabel("Total Sales: $0.00");
        totalSalesLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        gbc.gridy = 2;
        centerPanel.add(totalSalesLabel, gbc);

        totalProfitLabel = new JLabel("Total Profit: $0.00");
        totalProfitLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        gbc.gridy = 3;
        centerPanel.add(totalProfitLabel, gbc);

        // Go Back Button
        JButton goBackButton = new JButton("Go Back");
        goBackButton.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        centerPanel.add(goBackButton, gbc);
        goBackButton.addActionListener(e -> dispose());

        add(centerPanel, BorderLayout.CENTER);

        // Add Action Listener for Dropdown
        branchDropdown.addActionListener(e -> updateTotals((String) branchDropdown.getSelectedItem()));

        // Set default close operation
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    private void updateTotals(String branch) {
        
        List<Double> totals;
        if (branch.equals("ALL")) {
            totals = adminController.getTotalsForAllBranches();
        } else {
            totals = adminController.getTotalsForBranch(Integer.parseInt(branch));
        }
        double totalCost = totals.get(0);
        double totalSales = totals.get(1);
        double totalProfit = totals.get(2);

        // Update labels
        totalCostLabel.setText(String.format("Total Cost: %.2f", totalCost));
        totalSalesLabel.setText(String.format("Total Sales: %.2f", totalSales));
        totalProfitLabel.setText(String.format("Total Profit: %.2f", totalProfit));
    }
}
