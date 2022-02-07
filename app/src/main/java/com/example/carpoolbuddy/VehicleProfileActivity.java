package com.example.carpoolbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class VehicleProfileActivity extends AppCompatActivity {
    TextView modelTextView;
    TextView typeTextView;
    TextView priceTextView;
    TextView IDTextView;
    TextView openTextView;

    Vehicle chosen;

    private FirebaseAuth mAuth;
    private FirebaseFirestore fb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_profile);
        mAuth = FirebaseAuth.getInstance();
        fb = FirebaseFirestore.getInstance();

        modelTextView = findViewById(R.id.ModelQ);
        typeTextView = findViewById(R.id.TypeQ);
        IDTextView = findViewById(R.id.IDQ);
        priceTextView = findViewById(R.id.PriceQ);
        openTextView = findViewById(R.id.OpenQ);

        chosen = VehicleInfoActivity.vehicle;

        String nameText = chosen.getModel();
        String typeText = chosen.getVehicleType();
        String IdText = chosen.getVehicleID();
        String openText = chosen.isOpen().toString();
        String priceText = chosen.getBasePrice().toString();

        modelTextView.setText(nameText);
        typeTextView.setText(typeText);
        IDTextView.setText(IdText);
        priceTextView.setText(priceText);
        openTextView.setText(openText);

    }

    public void closeV(View v) {
        fb.collection("Vehicle").whereEqualTo("model", chosen.getModel())
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (DocumentSnapshot ds : task.getResult().getDocuments()) {
                    String ID = ds.getId();
                    fb.collection("Vehicle").document(ID).update("open", false);
                }
            }
        });
        startActivity(new Intent(this, VehicleInfoActivity.class));

    }

    public void openV(View v) {
        fb.collection("Vehicle").whereEqualTo("model", chosen.getModel())
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (DocumentSnapshot ds : task.getResult().getDocuments()) {
                    String ID = ds.getId();
                    fb.collection("Vehicle").document(ID).update("open", true);
                }
            }
        });
        startActivity(new Intent(this, VehicleInfoActivity.class));

    }
}