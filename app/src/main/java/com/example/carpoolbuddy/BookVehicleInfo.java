package com.example.carpoolbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class BookVehicleInfo extends AppCompatActivity implements AllVAdapter.vehicleListener {

    private FirebaseAuth mAuth;
    private FirebaseUser user;
    public FirebaseFirestore firebase;
    private Vehicle vehicleInfo;
    private ArrayList<Vehicle> vehiclesList;

    public static Vehicle vehicle;
    private ArrayList<String> mdata;
    private ArrayList<String> tdata;
    private ArrayList<String> sdata;
    private AllVAdapter myAdapter;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_vehicle_info);

        recyclerView = findViewById(R.id.recViewAllV);

        mAuth = FirebaseAuth.getInstance();
        firebase = FirebaseFirestore.getInstance();
        vehiclesList = new ArrayList<>();
        user = mAuth.getCurrentUser();


        mdata = new ArrayList();
        tdata = new ArrayList();
        sdata = new ArrayList();

        myAdapter = new AllVAdapter(mdata, tdata, sdata, this);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getAndPopulateData();
    }

    public void getAndPopulateData() {
        firebase.collection("Vehicle").
                get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot ds : task.getResult().getDocuments()) {
                        Vehicle getVehicle = ds.toObject(Vehicle.class);
                        vehiclesList.add(getVehicle);
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

                }

            }
        });
    }

    public void vehicleOnClick(int p) {
        vehicle = vehiclesList.get(p);
        startActivity(new Intent(this, BookVehicleProfile.class));

    }
}