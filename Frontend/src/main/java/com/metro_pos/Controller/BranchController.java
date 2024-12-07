package com.metro_pos.Controller;

import java.util.List;

import com.metro_pos.Model.BranchModel;

public class BranchController {

    private final BranchModel branchModel = new BranchModel();

    // Add a new branch
    public void addBranch(String name, String city, String address, String phone) {
        if (name == null || name.isEmpty()) {
            System.out.println("Branch name cannot be empty.");
            return;
        }
        if (city == null || city.isEmpty()) {
            System.out.println("City cannot be empty.");
            return;
        }
        if (address == null || address.isEmpty()) {
            System.out.println("Address cannot be empty.");
            return;
        }
        if (phone == null || phone.isEmpty()) {
            System.out.println("Phone number cannot be empty.");
            return;
        }

        branchModel.insert(name, city, address, phone);
    }

    // Retrieve all branches
    public String[][] getAllBranch() {
        return branchModel.getAllBranches();
    }

    // Update an existing branch
    public void updateBranch(int branchCode, String name, String city, String address, boolean isActive, String phone) {
        if (branchCode <= 0) {
            System.out.println("Invalid branch code.");
            return;
        }
        if (name == null || name.isEmpty()) {
            System.out.println("Branch name cannot be empty.");
            return;
        }
        if (city == null || city.isEmpty()) {
            System.out.println("City cannot be empty.");
            return;
        }
        if (address == null || address.isEmpty()) {
            System.out.println("Address cannot be empty.");
            return;
        }
        if (phone == null || phone.isEmpty()) {
            System.out.println("Phone number cannot be empty.");
            return;
        }

        branchModel.updateBranch(branchCode, name, city, address, isActive, phone);
    }
public List<Integer> getUnassignedBranchCodes() {
    return new BranchModel().getUnassignedBranchCodes();
}

    // Delete a branch by branch code
    public boolean deleteBranch(int branchCode) {
        if (branchCode <= 0) {
            System.out.println("Invalid branch code.");
            return false;
        }

        return branchModel.deleteBranch(branchCode);
    }
}
