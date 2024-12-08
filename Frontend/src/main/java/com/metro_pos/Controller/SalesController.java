package com.metro_pos.Controller;

import java.sql.Timestamp;
import java.util.List;

import com.metro_pos.Model.Product;
import com.metro_pos.Model.Sales;
import com.metro_pos.Model.SalesProducts;
import com.metro_pos.Service.SalesProductsService;
import com.metro_pos.Service.SalesService;
import com.metro_pos.Store.UserStore;

public class SalesController {

    private SalesService saleService;
    private SalesProductsService salesProductsService;

    public SalesController() {
        this.saleService = new SalesService();
        this.salesProductsService = new SalesProductsService();
    }

    public Boolean generateSale(List<Product> products) {

        // inserting into sales
        int totalQuantity = products.stream().mapToInt(p -> p.getQuantity()).sum();
        double totalPrice = products.stream().mapToDouble(p -> p.getQuantity() * p.getSalePrice()).sum();

        Sales sales = new Sales(0, new Timestamp(0), UserStore.getCurrentUser().getEmployeeNum(),
                UserStore.getCurrentUser().getBranchCode(), totalQuantity, totalPrice);

        sales = saleService.insert(sales);

        // inserting into salesProducts
        for (Product p : products) {
            SalesProducts salesProducts = new SalesProducts(sales.getId(), p.getId(), p.getQuantity(),
                    p.getPriceByUnit() * p.getQuantity());
            salesProductsService.insert(salesProducts);
        }

        return true;
    }
}
