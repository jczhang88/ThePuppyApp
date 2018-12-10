package com.example.jeffrey_zhang.thepuppyapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class playdatesRecyclerViewAdapter extends RecyclerView.Adapter<playdatesRecyclerViewAdapter.ViewHolder_playdates> {

    private ArrayList<Event> playdates;
    private Context mContext;

    playdatesRecyclerViewAdapter(ArrayList<Event> playdates, Context mContext) {
        this.playdates = playdates;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder_playdates onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.
                layout_list_playdates, viewGroup, false);
        ViewHolder_playdates viewHold = new ViewHolder_playdates(view);
        return viewHold;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder_playdates viewHold, final int i) {
        viewHold.textViewPlaydatesName.setText(playdates.get(i).eventName);
        viewHold.textViewPlaydatesLocation.setText("Location: " + playdates.get(i).eventLocation);
        viewHold.textViewPlaydatesDate.setText("Date: " + playdates.get(i).eventDate);
        viewHold.textViewPlaydatesDescription.setText(playdates.get(i).eventDescription);

        viewHold.playdates_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, playdates.get(i).eventName, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return playdates.size();
    }

    public class ViewHolder_playdates extends RecyclerView.ViewHolder {

        TextView textViewPlaydatesName, textViewPlaydatesLocation, textViewPlaydatesDate,
                textViewPlaydatesDescription;
        RelativeLayout playdates_layout;

        public ViewHolder_playdates(@NonNull View itemView) {
            super(itemView);

            textViewPlaydatesName = itemView.findViewById(R.id.textViewPlaydatesName);
            textViewPlaydatesLocation = itemView.findViewById(R.id.textViewPlaydatesLocation);
            textViewPlaydatesDate = itemView.findViewById(R.id.textViewPlaydatesDate);
            textViewPlaydatesDescription = itemView.findViewById(R.id.textViewPlaydatesDescription);
            playdates_layout = itemView.findViewById(R.id.playdates_layout);
        }
    }
}
