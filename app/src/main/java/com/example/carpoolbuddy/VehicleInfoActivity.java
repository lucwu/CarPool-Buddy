package com.example.carpoolbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class VehicleInfoActivity extends AppCompatActivity implements VehicleAdapter.vehicleListener {
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    public FirebaseFirestore firebase;
    private Vehicle vehicleInfo;
    private ArrayList<Vehicle> vehiclesList;

    public static Vehicle vehicle;

    RecyclerView recView;
    private ArrayList<String> mdata;
    private ArrayList<String> tdata;
    private ArrayList<String> sdata;
    private VehicleAdapter myAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_info);

        mAuth = FirebaseAuth.getInstance();
        firebase = FirebaseFirestore.getInstance();
        vehiclesList = new ArrayList<>();
        recView = findViewById(R.id.vehiclesRecView);
        user = mAuth.getCurrentUser();


        mdata = new ArrayList();
        tdata = new ArrayList();
        sdata = new ArrayList();

        myAdapter = new VehicleAdapter(mdata, tdata, sdata, this);
        recView.setAdapter(myAdapter);
        recView.setLayoutManager(new LinearLayoutManager(this));
        getAndPopulateData();

    }

    public void getAndPopulateData() {
        firebase.collection("Vehicle").whereEqualTo("owner", user.getEmail())
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful())
                {
                    for (DocumentSnapshot ds : task.getResult().getDocuments()) {
                        Vehicle vehicle = ds.toObject(Vehicle.class);
                        vehiclesList.add(vehicle);
                    }

                    for (Vehicle eachVehicle : vehiclesList) {
                        String eachModel = eachVehicle.getModel();
                        mdata.add(eachModel);

                        String eachType = eachVehicle.getVehicleType();
                        tdata.add(eachType);

                        String eachStatus = eachVehicle.isOpen().toString();
                        sdata.add(eachStatus);
                    }

                    myAdapter.newData(mdata, tdata, sdata);
                    myAdapter.notifyDataSetChanged();

                } else {
                    Toast.makeText(getApplicationContext(), "you don't have any vehicle yet", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void toAddVehicle(View v) {
        startActivity(new Intent(this, AddVehicleActivity.class));
    }

    public void toBookVehicle(View v) {
        startActivity(new Intent(this, BookVehicleInfo.class));
    }

    @Override
    public void vehicleOnClick(int p) {
        vehicle = vehiclesList.get(p);
        startActivity(new Intent(this, VehicleProfileActivity.class));

    }
}