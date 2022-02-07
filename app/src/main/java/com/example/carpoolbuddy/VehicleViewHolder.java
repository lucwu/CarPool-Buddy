package com.example.carpoolbuddy;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class VehicleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    protected TextView modelText;
    protected TextView typeText;
    protected TextView statusText;
    VehicleAdapter.vehicleListener listener;

    public VehicleViewHolder(@NonNull View itemView, VehicleAdapter.vehicleListener listener1) {
        super(itemView);

        modelText = itemView.findViewById(R.id.modelTextView);
        typeText = itemView.findViewById(R.id.typeTextView);
        statusText = itemView.findViewById(R.id.statusTextView);
        listener = listener1;

        itemView.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        listener.vehicleOnClick(getAdapterPosition());
    }
}
