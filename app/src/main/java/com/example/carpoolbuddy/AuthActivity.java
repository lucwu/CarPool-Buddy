package com.example.carpoolbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class AuthActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore firestore;
    private EditText emailField;
    private EditText passwordField;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        emailField = findViewById(R.id.EditTextEmail);
        passwordField = findViewById(R.id.EditTextPassword);
    }

    public void signIn(View v)
    {
        System.out.println("Log in");
        String emailString = emailField.getText().toString();
        String passwordString = passwordField.getText().toString();
        System.out.println(String.format("Email: %s and Password: %s", emailString, passwordString));

    }

    public void signUp(View v)
    {
        System.out.println("Sign up");
        String emailString = emailField.getText().toString();
        String passwordString = passwordField.getText().toString();

        mAuth.createUserWithEmailAndPassword(emailString, passwordString).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    Log.d("SIGN UP", "Successfully signed up the user");
                    FirebaseUser user = mAuth.getCurrentUser();
                    updateUI(user);
                }
                else
                {
                    Log.w("SIGN UP", "createUserWithEmailFailure", task.getException());
                    updateUI(null);
                }
            }
        });

    }

    public void updateUI(FirebaseUser currentUser)
    {
        if (currentUser != null)
        {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

    }
}