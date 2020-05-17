package com.example.africamarket.data;

import com.example.africamarket.model.Admin;

public class AdminApi {
    private Admin admin;
    private static AdminApi instance;

    public static AdminApi getInstance(){
        if (instance == null)
            instance = new AdminApi();

        return instance;
    }

    public AdminApi() {
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
}
