package com.example.africamarket.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.africamarket.R;
import com.example.africamarket.data.AdminCodeListApi;
import com.example.africamarket.data.AdminCreationListApi;
import com.example.africamarket.model.AdminCreation;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class AddAdminActivity extends AppCompatActivity {

    private EditText emailEdit;
    private Button sendCodeButton;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collectionReference = db.collection("Auth code");
    private CollectionReference adminCreationReference = db.collection("Admins creation");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_admin);

        referencingUiFields();

        //Send the creation code with the email entered
        sendCodeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (isValidEmail(emailEdit.getText())){
                        AdminCreation adminCreation = getNewAdminCreation();

                        //Save adminCreation in the db
                        adminCreationReference.add(adminCreation);

                        updateAdminCreationListApi(adminCreation);

                        String[] TO = {adminCreation.getEmail()};
                        String subject = getString(R.string.subject);
                        String message = getString(R.string.message) + "\n\n" + adminCreation.getCode();

                        sendCreationCodeByEmail(TO, subject, message);
                    }
                    else
                        Toast.makeText(AddAdminActivity.this, "Ce champs est obligatoire!", Toast.LENGTH_SHORT).show();
                }
            });
  }

  private void sendCreationCodeByEmail(String[] TO, String subject, String message){
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, TO);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);

        if (intent.resolveActivity(getPackageManager()) != null)
            startActivity(intent);
    }

  private AdminCreation getNewAdminCreation(){
        final String email = emailEdit.getText().toString().trim();
        final String code = getNewValidCode();

        return new AdminCreation(email, code);
  }

  public static boolean isValidEmail(CharSequence target) {
        return target != null && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
  }

  private void updateAdminCreationListApi(AdminCreation adminCreation){
        AdminCreationListApi adminCreationListApi = AdminCreationListApi.getInstance();
        List<AdminCreation> adminCreationList = adminCreationListApi.getAdminCreationList();
        adminCreationList.add(adminCreation);
        adminCreationListApi.setAdminCreationList(adminCreationList);
  }

  private void referencingUiFields(){
        emailEdit = findViewById(R.id.email_addAdmin_edit);
        sendCodeButton = findViewById(R.id.send_code_button);
  }

  private String getNewValidCode(){
        Random rand = new Random();
        int randNum = rand.nextInt();
        final String result = Integer.toHexString(randNum);

        collectionReference.addSnapshotListener(this, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null)
                    Log.d("Get from db", "onEvent: " + e.getMessage());


                Map<String, Object> data = new HashMap<>();
                data.put("code", result);


                if (queryDocumentSnapshots != null && queryDocumentSnapshots.isEmpty())
                    collectionReference
                            .add(data)
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d("Add from db", "onFailure: " + e.getMessage());
                                }
                            });
                else{
                    assert queryDocumentSnapshots != null;
                    for (QueryDocumentSnapshot snapshot : queryDocumentSnapshots){
                        String code = snapshot.getString("code");

                        if (result.equals(code))
                            getNewValidCode();
                    }
                }

            }
        });

        AdminCodeListApi codeListApi = AdminCodeListApi.getInstance();

        List<String> codeList = codeListApi.getCodeList();
        codeList.add(result);

        codeListApi.setCodeList(codeList);

        return result;
  }
}
