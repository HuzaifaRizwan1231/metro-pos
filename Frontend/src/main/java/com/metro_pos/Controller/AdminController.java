package com.metro_pos.Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.metro_pos.Database.DatabaseConnection;

public class AdminController {
    private Connection connection;
    public AdminController() {
        connection = DatabaseConnection.getConnection();
    }
    public List<Integer> getBranchCodes() {
        List<Integer> branchCodes = new ArrayList<>();
        String sql = "SELECT branch_code FROM branch WHERE is_active = true";

        try (PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                branchCodes.add(rs.getInt("branch_code"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return branchCodes;
    }

    public List<Double> getTotalsForBranch(int branchCode) {
        List<Double> totals = new ArrayList<>();
        String query = "SELECT " +
                       "SUM(sp.quantity_sold * p.original_price) AS total_cost, " +
                       "SUM(sp.quantity_sold * p.sale_price) AS total_sales, " +
                       "SUM(sp.quantity_sold * p.sale_price) - SUM(sp.quantity_sold * p.original_price) AS total_profit " +
                       "FROM sales_products sp " +
                       "JOIN product p ON sp.product_id = p.id " +
                       "JOIN sales s ON sp.sale_id = s.id " +
                       "WHERE s.branch_code = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, branchCode);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    totals.add(resultSet.getDouble("total_cost"));
                    totals.add(resultSet.getDouble("total_sales"));
                    totals.add(resultSet.getDouble("total_profit"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totals;
    }

    public List<Double> getTotalsForAllBranches() {
        List<Double> totals = new ArrayList<>();
        String query = "SELECT " +
                       "SUM(sp.quantity_sold * p.original_price) AS total_cost, " +
                       "SUM(sp.quantity_sold * p.sale_price) AS total_sales, " +
                       "SUM(sp.quantity_sold * p.sale_price) - SUM(sp.quantity_sold * p.original_price) AS total_profit " +
                       "FROM sales_products sp " +
                       "JOIN product p ON sp.product_id = p.id " +
                       "JOIN sales s ON sp.sale_id = s.id";

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                totals.add(resultSet.getDouble("total_cost"));
                totals.add(resultSet.getDouble("total_sales"));
                totals.add(resultSet.getDouble("total_profit"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totals;
    }
}
