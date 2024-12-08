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

    public boolean addVendor(String name, String phone, String address) {
        return deoService.addVendor(name, phone, address);
    }

    public Object[][] getVendors() {
       return deoService.getVendors();
    }
    
}
