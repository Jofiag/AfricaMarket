package com.example.africamarket.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.africamarket.R;

public class ChooseAddingActivity extends AppCompatActivity implements View.OnClickListener {

    private Button addProductButton;
    private Button addAdminButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_adding);

        addProductButton = findViewById(R.id.add_product_button);
        addAdminButton = findViewById(R.id.add_admin_button);

        addProductButton.setOnClickListener(this);
        addAdminButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_product_button:
                startAddProductActivity();
                break;
            case R.id.add_admin_button:
                startAddAdminActivity();
                break;
        }
    }

    private void startAddAdminActivity() {
        Intent intent = new Intent(ChooseAddingActivity.this, AddAdminActivity.class);
        startActivity(intent);
    }

    private void startAddProductActivity() {
        Intent intent = new Intent(ChooseAddingActivity.this, AddNewProductActivity.class);
        startActivity(intent);
    }
}
