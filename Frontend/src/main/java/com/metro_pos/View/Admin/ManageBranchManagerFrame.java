package com.metro_pos.View.Admin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.metro_pos.Database.DatabaseConnection;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.sql.*;

public class ManageBranchManagerFrame extends JFrame {

    private JButton updateManagerButton;
    private JButton deleteManagerButton;
    private JTable table;

    public ManageBranchManagerFrame() {
        // Set up the frame
        setTitle("Manage Branch Managers");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(false);
        setLayout(new BorderLayout());

        // Create the table with updated columns (removed "Address")
        String[] columnNames = {"Employee Code", "Name", "Email", "Branch Code", "Salary"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);  // Initially no data

        table = new JTable(model);

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

        JButton addManagerButton = new JButton("Add Manager");
        addManagerButton.setPreferredSize(new Dimension(200, 50));
        addManagerButton.setFont(buttonFont);

        updateManagerButton = new JButton("Update Manager");
        updateManagerButton.setPreferredSize(new Dimension(200, 50));
        updateManagerButton.setFont(buttonFont);

        deleteManagerButton = new JButton("Delete Manager");
        deleteManagerButton.setPreferredSize(new Dimension(200, 50));
        deleteManagerButton.setFont(buttonFont);

        // Initially disable the update and delete buttons
        updateManagerButton.setEnabled(false);
        deleteManagerButton.setEnabled(false);

        buttonPanel.add(backButton);
        buttonPanel.add(addManagerButton);
        buttonPanel.add(updateManagerButton);
        buttonPanel.add(deleteManagerButton);

        add(buttonPanel, BorderLayout.SOUTH);

        backButton.addActionListener(e -> {
            new AdminFrame();
            dispose(); // Close the current frame
        });
        addManagerButton.addActionListener(e -> {
            AddManagerFrame addManagerFrame = new AddManagerFrame();
        
            // Add a WindowListener to reload the table data after the AddManagerFrame is closed
            addManagerFrame.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent e) {
                    loadManagerData(); // Refresh the table data
                }
            });
        });
        
        updateManagerButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                // Retrieve selected manager details
                int managerCode = (int) table.getValueAt(selectedRow, 0);
                String name = (String) table.getValueAt(selectedRow, 1);
                String email = (String) table.getValueAt(selectedRow, 2);
                int branchCode = (int) table.getValueAt(selectedRow, 3);
                double salary = (double) table.getValueAt(selectedRow, 4);
        
                // Open the UpdateBranchManagerFrame with the selected manager's details
                UpdateBranchManagerFrame updateBranchManagerFrame = new UpdateBranchManagerFrame(managerCode, name, email, salary);
        
                // Add a WindowListener to reload the table data after the UpdateBranchManagerFrame is closed
                updateBranchManagerFrame.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosed(java.awt.event.WindowEvent e) {
                        loadManagerData(); // Refresh the table data
                    }
                });
            } else {
                JOptionPane.showMessageDialog(this, "No manager selected");
            }
        });
        deleteManagerButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                // Retrieve the selected manager's Employee Code and Branch Code
                int employeeCode = (int) table.getValueAt(selectedRow, 0);
                int branchCode = (int) table.getValueAt(selectedRow, 3); // Assuming branch_code is in the 4th column
                deleteManager(employeeCode, branchCode);
            } else {
                JOptionPane.showMessageDialog(this, "No manager selected");
            }
        });
        

        table.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting()) {
                boolean isSelected = table.getSelectedRow() != -1;
                updateManagerButton.setEnabled(isSelected);
                deleteManagerButton.setEnabled(isSelected);
            }
        });

        // Load managers from the database
        loadManagerData();

        // Display the frame
        setVisible(true);
    }

    // Method to load manager data from the database (modified query to exclude "Address")
    private void loadManagerData() {
        String sql = "SELECT employee_num, name, email, branch_code, salary FROM user WHERE role = 'Manager'";
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);  // Clear the table before loading new data

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int employeeCode = rs.getInt("employee_num");
                String name = rs.getString("name");
                String email = rs.getString("email");
                int branchCode = rs.getInt("branch_code");
                double salary = rs.getDouble("salary");

                // Add row to the table
                model.addRow(new Object[]{employeeCode, name, email, branchCode, salary});
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading manager data.", "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
// Method to delete a manager from the database
private void deleteManager(int employeeCode, int branchCode) {
    String deleteManagerSql = "DELETE FROM user WHERE employee_num = ? AND role = 'Manager'";
    String updateBranchSql = "UPDATE branch SET manager_assigned = 0 WHERE branch_code = ?";

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement deletePs = conn.prepareStatement(deleteManagerSql);
         PreparedStatement updatePs = conn.prepareStatement(updateBranchSql)) {

        conn.setAutoCommit(false); // Begin transaction

        // Prepare and execute delete manager query
        deletePs.setInt(1, employeeCode);
        int rowsAffected = deletePs.executeUpdate();

        if (rowsAffected > 0) {
            // Update the branch's manager_assigned status
            updatePs.setInt(1, branchCode);
            updatePs.executeUpdate();

            conn.commit(); // Commit transaction
            JOptionPane.showMessageDialog(this, "Manager deleted successfully.");
            loadManagerData(); // Reload the manager data after deletion
        } else {
            conn.rollback(); // Rollback transaction in case of failure
            JOptionPane.showMessageDialog(this, "Failed to delete manager. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error deleting manager.", "Database Error", JOptionPane.ERROR_MESSAGE);
    }
}


    public static void main(String[] args) {
        new ManageBranchManagerFrame(); // Create an instance of ManageBranchManagerFrame
    }
}
