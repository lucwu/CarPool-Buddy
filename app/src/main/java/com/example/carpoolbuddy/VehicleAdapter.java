package com.example.carpoolbuddy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class VehicleAdapter extends RecyclerView.Adapter<VehicleViewHolder> {
    ArrayList<String> vmodelData;
    ArrayList<String> vtypeData;
    ArrayList<String> vstatusData;

    private vehicleListener listener;

    public VehicleAdapter(ArrayList modelData, ArrayList typeData, ArrayList statusData, vehicleListener listener1) {
        vmodelData = modelData;
        vtypeData = typeData;
        vstatusData = statusData;
        listener = listener1;
    }

    @NonNull
    @Override
    public VehicleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myView = LayoutInflater.from(parent.getContext()).inflate(R.layout.vehicle_row_view, parent, false);
        VehicleViewHolder holder = new VehicleViewHolder(myView, listener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull VehicleViewHolder holder, int position) {
        holder.modelText.setText(vmodelData.get(position));
        holder.statusText.setText(vstatusData.get(position));
        holder.typeText.setText(vtypeData.get(position));

    }

    @Override
    public int getItemCount() {
        return vmodelData.size();
    }

    public void newData(ArrayList modelData, ArrayList typeData, ArrayList statusData) {
        vmodelData = modelData;
        vtypeData = typeData;
        vstatusData = statusData;
    }

    public interface vehicleListener {
        void vehicleOnClick(int p);

    }
}
