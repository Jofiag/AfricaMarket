package com.example.africamarket.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.africamarket.R;
import com.example.africamarket.model.Admin;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button adminButton;
    private Button clientButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adminButton = findViewById(R.id.admin_button);
        clientButton = findViewById(R.id.client_button);

        adminButton.setOnClickListener(this);
        clientButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.admin_button:
                startAdminLoginActivity();
                break;
            case R.id.client_button:
                startProductListActivity();
                break;
        }

    }

    private void startProductListActivity() {
        Intent intent = new Intent(MainActivity.this, ProductsListActivity.class);
        startActivity(intent);
    }

    private void startAdminLoginActivity() {
        Intent intent = new Intent(MainActivity.this, AdminLoginActivity.class);
        startActivity(intent);
    }
}
