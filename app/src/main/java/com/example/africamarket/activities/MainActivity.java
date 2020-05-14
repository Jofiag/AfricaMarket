package com.example.africamarket.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.africamarket.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button adminButton = findViewById(R.id.admin_button);
        Button clientButton = findViewById(R.id.client_button);

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
