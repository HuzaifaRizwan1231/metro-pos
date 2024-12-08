package com.metro_pos.View.Admin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import com.metro_pos.Controller.BranchController;
import java.awt.*;
import java.awt.event.ActionListener;

public class ManageBranchFrame extends JFrame {

    private JButton updateBranchButton;
    private JButton deleteBranchButton;
    private JTable table;
    private final String[] columnNames = {
        "Branch Code", "Name", "City", "Address", "Phone", "Active", "No of Employees", "Manager Assigned"
    };
    private final BranchController branchController;

    public ManageBranchFrame() {
        branchController = new BranchController(); // Initialize the controller

        // Set up the frame
        setTitle("Manage Branches");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());

        // Initialize components
        initializeTable();
        initializeButtons();

        // Display the frame
        setVisible(true);
    }

    private void initializeTable() {
        Object[][] branchData = branchController.getAllBranch();

        // Create and configure the table
        table = new JTable(new DefaultTableModel(branchData, columnNames));
        table.setFont(new Font("Arial", Font.PLAIN, 18));
        table.setRowHeight(30);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 20));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Enable/disable buttons based on selection
        table.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting()) {
                boolean isSelected = table.getSelectedRow() != -1;
                updateBranchButton.setEnabled(isSelected);
                deleteBranchButton.setEnabled(isSelected);
            }
        });

        // Add the table to a scroll pane and attach it to the frame
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void initializeButtons() {
        // Create buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        Font buttonFont = new Font("Arial", Font.PLAIN, 18);

        JButton backButton = createButton("Back", buttonFont, e -> handleBackButton());
        JButton addBranchButton = createButton("Add Branch", buttonFont, e -> handleAddBranch());
        updateBranchButton = createButton("Update Branch", buttonFont, e -> handleUpdateBranch());
        deleteBranchButton = createButton("Delete Branch", buttonFont, e -> handleDeleteBranch());

        // Initially disable update and delete buttons
        updateBranchButton.setEnabled(false);
        deleteBranchButton.setEnabled(false);

        // Add buttons to the panel
        buttonPanel.add(backButton);
        buttonPanel.add(addBranchButton);
        buttonPanel.add(updateBranchButton);
        buttonPanel.add(deleteBranchButton);

        // Attach the panel to the frame
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JButton createButton(String text, Font font, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.setFont(font);
        button.setPreferredSize(new Dimension(200, 50));
        button.addActionListener(actionListener);
        return button;
    }

    private void handleBackButton() {
        new AdminFrame(); // Open the admin frame
        dispose(); // Close the current frame
    }

    private void handleAddBranch() {
        new AddBranchFrame(); // Open the AddBranchFrame

    }

    private void handleUpdateBranch() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            // Retrieve branch data from the selected row
            int branchCode = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
            String name = table.getValueAt(selectedRow, 1).toString();
            String city = table.getValueAt(selectedRow, 2).toString();
            String address = table.getValueAt(selectedRow, 3).toString();
            String phone = table.getValueAt(selectedRow, 4).toString();
            String isActive = table.getValueAt(selectedRow, 5).toString();

            // Open the UpdateBranchFrame with the selected data
            new UpdateBranchFrame(branchCode, name, city, address, phone, isActive);
            refreshTable();

        } else {
            JOptionPane.showMessageDialog(this, "No branch selected.", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void handleDeleteBranch() {
        int selectedRow = table.getSelectedRow();

        if (selectedRow != -1) {
            // Retrieve the branch code from the selected row
            int branchCode = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());

            int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete this branch?",
                "Confirm Deletion",
                JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                boolean isDeleted = branchController.deleteBranch(branchCode);

                if (isDeleted) {
                    JOptionPane.showMessageDialog(this, "Branch deleted successfully!");
                    refreshTable();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to delete branch.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "No branch selected.", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void refreshTable() {
        Object[][] branchData = branchController.getAllBranch();
        table.setModel(new DefaultTableModel(branchData, columnNames));
    }

    public static void main(String[] args) {
        new ManageBranchFrame(); // Launch the frame
    }
}
