package com.metro_pos.Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.metro_pos.Database.DatabaseConnection;
import com.metro_pos.Service.DEOService;

public class DEOController {
    private static DEOService deoService;
    public DEOController() {
        deoService = new DEOService();
    }

    public int addVendor(String name, String phone, String address) {
        return deoService.addVendor(name, phone, address);
    }

    public Object[][] getVendors() {
       return deoService.getVendors();
    }

    public boolean addProduct(String name, String category, double originalPrice, double salePrice, double priceByUnit, double priceByCarton, int quantity, int vendorId, int branch_id) {
        int id = deoService.checkProductExistsInBranch(name, branch_id);
        if(id != 0) {
            return deoService.updateProductQuantity(id, quantity, vendorId);
        }
        else if(id == -1) {
            return false;
        }
        else{
            return deoService.addNewProduct(name, category, originalPrice, salePrice, priceByUnit, priceByCarton, quantity, vendorId, branch_id);
        }
    }
    
}
