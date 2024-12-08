package com.metro_pos.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.metro_pos.Model.Sales;

public class SalesService extends BaseService {

    public Sales insert(Sales sales) {

        String sql = "INSERT INTO sales (cashier_id, branch_code, total_quantity, total_price) VALUES (?,?,?,?)";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, sales.getCashierId());
            ps.setInt(2, sales.getBranchCode());
            ps.setInt(3, sales.getTotalQuantity());
            ps.setDouble(4, sales.getTotalPrice());

            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                ResultSet generatedKeys = ps.getGeneratedKeys();

                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    sales.setId(generatedId);
                }

            }

            return sales;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error while retrieving branches: " + e.getMessage());
        }
        return null;
    }

}
