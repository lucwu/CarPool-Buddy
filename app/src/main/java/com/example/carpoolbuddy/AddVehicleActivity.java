package com.example.carpoolbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;


public class AddVehicleActivity extends AppCompatActivity {

    EditText model;
    EditText type;
    EditText capacity;
    EditText iD;
    EditText price;
    private FirebaseAuth mAuth;
    private FirebaseFirestore firebase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehicle);

        model = (EditText) findViewById(R.id.vModel);
        type = (EditText) findViewById(R.id.vType);
        capacity = (EditText) findViewById(R.id.vCapacity);
        iD = (EditText) findViewById(R.id.vID);
        price = (EditText) findViewById(R.id.vPrice);


        mAuth = FirebaseAuth.getInstance();
        firebase = FirebaseFirestore.getInstance();

    }

    public boolean formValid() {
        if (model != null && type != null && capacity != null && iD != null && price != null) {
            return true;
        }
        Toast.makeText(getApplicationContext(), "Please fill in everything", Toast.LENGTH_SHORT).show();
        return false;
    }

    public void addNewVehicle(View v) {
        if (formValid()) {
            String vModel = model.getText().toString();
            String vType = type.getText().toString();
            int vCapa = Integer.parseInt(capacity.getText().toString());
            String vID = iD.getText().toString();
            double vPrice = Double.parseDouble(String.valueOf(price.getText()));

            FirebaseUser addVehicleUser = mAuth.getCurrentUser();
            if (addVehicleUser != null) {
                Boolean open = true;
                String vcOwner = mAuth.getCurrentUser().getEmail();
                Vehicle newVehicle = new Vehicle(vcOwner, vModel, vCapa, vID, open, vType, vPrice);

                firebase.collection("Vehicle").add(newVehicle);
                startActivity(new Intent(this, VehicleInfoActivity.class));
            } else {
                Toast.makeText(getApplicationContext(), "PLEASE LOG IN FIRST", Toast.LENGTH_SHORT).show();
            }

        }

    }


}