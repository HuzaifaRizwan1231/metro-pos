package com.metro_pos.View.BranchManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

public class ManageEmployeeFrame extends JFrame {

    private JButton updateEmployeeButton;
    private JButton deleteEmployeeButton;

    public ManageEmployeeFrame() {
        // Set up the frame
        setTitle("Manage Employees");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setUndecorated(false);
        // Set layout manager
        setLayout(new BorderLayout());

        // Create the table with updated columns
        String[] columnNames = {"Employee Code", "Name", "Email", "Branch Code", "Phone", "Address", "Salary", "Role"};
        Object[][] data = {
                {1, "Charlie Brown", "charlie@example.com", 1, "123-456-7890", "123 Main St", 40000.0, "Cashier"},
        };

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(model);

        // Set table font size and row height
        table.setFont(new Font("Arial", Font.PLAIN, 18));
        table.setRowHeight(30);

        // Set column header font size
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 20));

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Create buttons with larger preferred size and font
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        Font buttonFont = new Font("Arial", Font.PLAIN, 18);

        JButton backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(200, 50));
        backButton.setFont(buttonFont);

        JButton addEmployeeButton = new JButton("Add Employee");
        addEmployeeButton.setPreferredSize(new Dimension(200, 50));
        addEmployeeButton.setFont(buttonFont);

        updateEmployeeButton = new JButton("Update Employee");
        updateEmployeeButton.setPreferredSize(new Dimension(200, 50));
        updateEmployeeButton.setFont(buttonFont);

        deleteEmployeeButton = new JButton("Delete Employee");
        deleteEmployeeButton.setPreferredSize(new Dimension(200, 50));
        deleteEmployeeButton.setFont(buttonFont);

        // Initially disable the update and delete buttons
        updateEmployeeButton.setEnabled(false);
        deleteEmployeeButton.setEnabled(false);

        buttonPanel.add(backButton);
        buttonPanel.add(addEmployeeButton);
        buttonPanel.add(updateEmployeeButton);
        buttonPanel.add(deleteEmployeeButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Add action listeners for buttons
        backButton.addActionListener(e ->{
            new BranchManagerFrame();
            dispose();
        } );

        addEmployeeButton.addActionListener(e -> {
            new AddEmployeeFrame();
            // dispose();
        });

        deleteEmployeeButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                model.removeRow(selectedRow);
            } else {
                JOptionPane.showMessageDialog(this, "No employee selected");
            }
        });

        updateEmployeeButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                // Retrieve selected employee details
                int employeeCode = (int) table.getValueAt(selectedRow, 0);
                String name = (String) table.getValueAt(selectedRow, 1);
                String email = (String) table.getValueAt(selectedRow, 2);
                int branchCode = (int) table.getValueAt(selectedRow, 3);
                String phone = (String) table.getValueAt(selectedRow, 4);
                String address = (String) table.getValueAt(selectedRow, 5);
                double salary = (double) table.getValueAt(selectedRow, 6);
                String role = (String) table.getValueAt(selectedRow, 7);

                // Open the UpdateEmployeeFrame with the selected employee's details
                new UpdateEmployeeFrame(employeeCode, name, email, phone, address, salary, role);
            } else {
                JOptionPane.showMessageDialog(this, "No employee selected");
            }
        });

        table.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting()) {
                boolean isSelected = table.getSelectedRow() != -1;
                updateEmployeeButton.setEnabled(isSelected);
                deleteEmployeeButton.setEnabled(isSelected);
            }
        });

        // Display the frame
        setVisible(true);
    }

    public static void main(String[] args) {
        new ManageEmployeeFrame(); // Create an instance of ManageEmployeeFrame
    }
}
