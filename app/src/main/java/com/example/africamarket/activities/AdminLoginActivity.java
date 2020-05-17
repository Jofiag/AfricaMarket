package com.example.africamarket.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.africamarket.R;
import com.example.africamarket.data.AdminApi;
import com.example.africamarket.model.Admin;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class AdminLoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText emailEdit;
    private EditText passwordEdit;
    private Button loginButton;
    private Button passwordForgottenButton;
    private Button notMemberYetButton;

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseUser firebaseUser;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collectionReference = db.collection("Admins");

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = database.getReference("Message");

    private Admin adminDirector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        referencingUiComponents();

        firebaseAuth = FirebaseAuth.getInstance();


        adminDirector = new Admin("Med", "jocelynaguemon@gmail.com", "Jofiag22");
        adminDirector.setId("7vrwG7bmdFO6F4hRPyFTR5AzL7c2");
        adminDirector.setDirector(true);
        addAnAdminToTheCollection(adminDirector);

    }

    private void referencingUiComponents(){
        emailEdit = findViewById(R.id.admin_email);
        passwordEdit = findViewById(R.id.admin_password);
        loginButton = findViewById(R.id.login_button);
        passwordForgottenButton = findViewById(R.id.forgotten_password_button);
        notMemberYetButton = findViewById(R.id.create_account_button);

        loginButton.setOnClickListener(this);
        passwordForgottenButton.setOnClickListener(this);
        notMemberYetButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.login_button:
                loginAdmin();
                break;
            case R.id.forgotten_password_button:
                break;
            case R.id.create_account_button:
                break;
        }

    }

    private void loginAdmin() {
        String adminEmail = emailEdit.getText().toString().trim();
        String adminPassword = passwordEdit.getText().toString().trim();

        if (!TextUtils.isEmpty(adminEmail) && !TextUtils.isEmpty(adminPassword)){
            firebaseAuth.signInWithEmailAndPassword(adminEmail, adminPassword)
                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {

                            FirebaseUser adminConnected = firebaseAuth.getCurrentUser();

                            //Retrieving adminConnect data from firebase
                            collectionReference
                                    .whereEqualTo("id", adminConnected.getUid())
                                    .addSnapshotListener(new EventListener<QuerySnapshot>() {
                                        @Override
                                        public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots,
                                                            @Nullable FirebaseFirestoreException e) {
                                            if (e != null){
                                                Log.d("GetAdminConnected", "onEvent: " + e.getMessage());
                                            }


                                            if (queryDocumentSnapshots != null && !queryDocumentSnapshots.isEmpty()){
                                                for (QueryDocumentSnapshot snapshot : queryDocumentSnapshots){
                                                    Admin admin = new Admin();
                                                    admin.setId(snapshot.getId());
                                                    admin.setName(snapshot.getString("name"));
                                                    admin.setEmail(snapshot.getString("email"));
                                                    admin.setPassword(snapshot.getString("password"));
                                                    admin.setDirector(snapshot.getBoolean("director"));

                                                    Log.d("GET", "onEvent: " + admin);

                                                    //Save the connected admin
                                                    AdminApi adminApi = AdminApi.getInstance();
                                                    adminApi.setAdmin(admin);

                                                    Toast.makeText(AdminLoginActivity.this, admin.getName() + " Connected", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        }
                                    });

                            if (adminConnected.getUid().equals(adminDirector.getId()))
                                startChooseAddingActivity();
                            else
                                startAddNewProductActivity();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("ConnectionD", "onFailure: " + e.getMessage());
                            Toast.makeText(AdminLoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
        else {
            Toast.makeText(AdminLoginActivity.this, "Both field are required!", Toast.LENGTH_SHORT).show();
        }
    }

    private void startChooseAddingActivity(){
        Intent intent = new Intent(AdminLoginActivity.this, ChooseAddingActivity.class);
        startActivity(intent);
    }

    private void startAddNewProductActivity(){
        Intent intent = new Intent(AdminLoginActivity.this, AddNewProductActivity.class);
        startActivity(intent);
    }

    private void addAnAdminToTheCollection(Admin newAdmin){
        collectionReference
                .document("Admins Director")
                .set(newAdmin)
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("CollectionD", "onFailure: " + e.toString());
                        Toast.makeText(AdminLoginActivity.this, "Create collection Failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
