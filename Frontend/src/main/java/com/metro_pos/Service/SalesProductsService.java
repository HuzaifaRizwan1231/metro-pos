package com.metro_pos.Service;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.metro_pos.Model.SalesProducts;

public class SalesProductsService extends BaseService {

    public Boolean insert(SalesProducts obj) {

        String sql = "INSERT INTO sales_products (sale_id, product_id, quantity_sold, price_sold) VALUES (?,?,?,?)";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, obj.getSaleId());
            ps.setInt(2, obj.getProductId());
            ps.setInt(3, obj.getQuantitySold());
            ps.setDouble(4, obj.getPriceSold());

            ps.execute();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error while retrieving branches: " + e.getMessage());
        }
        return false;
    }
}
