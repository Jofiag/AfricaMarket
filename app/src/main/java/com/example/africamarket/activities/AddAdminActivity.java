package com.example.africamarket.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.africamarket.R;
import com.example.africamarket.data.AdminCodeListApi;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class AddAdminActivity extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collectionReference = db.collection("Auth code");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_admin);

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
        codeListApi.setValidAdminCode(result);

        return result;
    }
}
