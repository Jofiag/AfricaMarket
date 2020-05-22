package com.example.africamarket.data;

import com.example.africamarket.model.AdminCreation;

import java.util.ArrayList;
import java.util.List;

public class AdminCreationListApi {
    private List<AdminCreation> adminCreationList = new ArrayList<>();
    private static AdminCreationListApi instance;

    public AdminCreationListApi() {
    }

    public static AdminCreationListApi getInstance(){
        if (instance == null)
            instance = new AdminCreationListApi();

        return instance;
    }

    public List<AdminCreation> getAdminCreationList() {
        return adminCreationList;
    }

    public void setAdminCreationList(List<AdminCreation> adminCreationList) {
        this.adminCreationList = adminCreationList;
    }
}
