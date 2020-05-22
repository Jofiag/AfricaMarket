package com.example.africamarket.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.example.africamarket.R;
import com.example.africamarket.data.AdminCreationListApi;
import com.example.africamarket.model.AdminCreation;

public class CreateNewAdminAccount extends AppCompatActivity {

    private EditText sentCodeEdit;
    private EditText usernameEdit;
    private EditText emailEdit;
    private EditText passwordEdit;
    private Button createAccountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_admin_account);

        referencingUIFields();

        AdminCreationListApi adminCreationListApi = AdminCreationListApi.getInstance();
        for (AdminCreation adminCreation : adminCreationListApi.getAdminCreationList())
            Log.d("List", "onCreate: " + adminCreation + "\n\n");
    }

    private void referencingUIFields(){
        sentCodeEdit = findViewById(R.id.sent_code_edit);
        usernameEdit = findViewById(R.id.name_create_admin_edit);
        emailEdit = findViewById(R.id.email_create_admin_edit);
        passwordEdit = findViewById(R.id.password_create_admin_edit);
    }
}
