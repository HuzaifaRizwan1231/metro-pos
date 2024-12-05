package com.metro_pos.View.Cashier;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class GenerateSaleDialogue extends JDialog {

    public GenerateSaleDialogue(JFrame parent) {
        super(parent, "Generate Sale", true);
        setLayout(new BorderLayout());

        String[] columnNames = {"Name", "Quantity", "Unit Price", "Total Price"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable productTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(productTable);
        add(scrollPane, BorderLayout.CENTER);

        Font font = new Font(null, Font.PLAIN, 16);

        JButton generateSaleButton = new JButton("Generate Sale");
        generateSaleButton.setFont(font);
        JButton addItemButton = new JButton("Add Item");
        addItemButton.setFont(font);
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(font);

        generateSaleButton.addActionListener(e -> {
            // Logic to generate the sale
            JOptionPane.showMessageDialog(this, "Sale generated successfully!");
        });

        addItemButton.addActionListener(e -> {
            new AddProductToSale(GenerateSaleDialogue.this);
        });

        cancelButton.addActionListener(e -> {
            GenerateSaleDialogue.this.dispose();
        });

        // Create a panel for the buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(generateSaleButton);
        buttonPanel.add(addItemButton);
        buttonPanel.add(cancelButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Set dialog properties
        setSize(600, 400);
        setLocationRelativeTo(parent);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

}

