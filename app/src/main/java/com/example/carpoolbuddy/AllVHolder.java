package com.example.carpoolbuddy;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AllVHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    protected TextView modelText;
    protected TextView typeText;
    protected TextView statusText;
    AllVAdapter.vehicleListener listener;

    public AllVHolder(@NonNull View itemView, AllVAdapter.vehicleListener listener1) {
        super(itemView);

        modelText = itemView.findViewById(R.id.modelTextView);
        typeText = itemView.findViewById(R.id.typeTextView);
        statusText = itemView.findViewById(R.id.statusTextView);
        listener = listener1;

        itemView.setOnClickListener(this);
    }

    public void onClick(View view) {
        listener.vehicleOnClick(getAdapterPosition());
    }
}
