package com.example.africamarket.data;

import java.util.ArrayList;
import java.util.List;

public class AdminCodeListApi {
    private List<String> codeList = new ArrayList<>();
    private static AdminCodeListApi instance;

    public AdminCodeListApi() {
    }

    public static AdminCodeListApi getInstance(){
        if (instance == null)
            instance = new AdminCodeListApi();

        return instance;
    }

    public List<String> getCodeList() {
        return codeList;
    }

    public void setCodeList(List<String> codeList) {
        this.codeList = codeList;
    }
}
