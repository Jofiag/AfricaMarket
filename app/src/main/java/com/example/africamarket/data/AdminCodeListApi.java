package com.example.africamarket.data;

import java.util.ArrayList;
import java.util.List;

public class AdminCodeListApi {
    private String validAdminCode;
    private static AdminCodeListApi instance;

    public AdminCodeListApi() {
    }

    public static AdminCodeListApi getInstance(){
        if (instance == null)
            instance = new AdminCodeListApi();

        return instance;
    }

    public String getValidAdminCode() {
        return validAdminCode;
    }

    public void setValidAdminCode(String validAdminCode) {
        this.validAdminCode = validAdminCode;
    }
}
