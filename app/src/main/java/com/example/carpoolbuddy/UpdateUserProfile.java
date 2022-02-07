package com.example.carpoolbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class UpdateUserProfile extends AppCompatActivity {
    public FirebaseAuth mAuth;
    public FirebaseFirestore firebase;
    private EditText name;
    private EditText type;
    private EditText email;
    private EditText password;
    private EditText confirmedPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user_profile);

        mAuth = FirebaseAuth.getInstance();
        firebase = FirebaseFirestore.getInstance();
        name = (EditText) findViewById(R.id.resetName);
        type = (EditText) findViewById(R.id.resetType);
        email = (EditText) findViewById(R.id.resetEmail);
        password = (EditText) findViewById(R.id.resetPassword);
        confirmedPass = (EditText) findViewById(R.id.confirmPassword);

    }

    public void reset(View v) {
        String nameString = name.getText().toString();
        String typeString = type.getText().toString();
        String emailString = email.getText().toString();
        String passwordString = password.getText().toString();
        String confirmString = confirmedPass.getText().toString();

        firebase.collection("User").whereEqualTo("email", mAuth.getCurrentUser().getEmail())
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (DocumentSnapshot ds : task.getResult().getDocuments()) {
                    String ID = ds.getId();
                    if (passwordString.equals(confirmString)) {
                        firebase.collection("User").document(ID).update("name", nameString);
                        firebase.collection("User").document(ID).update("userType", typeString);
                        firebase.collection("User").document(ID).update("email", emailString);
                        mAuth.getCurrentUser().updatePassword(passwordString);
                    } else {
                        Toast.makeText(getApplicationContext(), "Your passwords do not match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        startActivity(new Intent(this, UserProfileActivity.class));


    }
}