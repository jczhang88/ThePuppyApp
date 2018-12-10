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

public class myEventsRecyclerViewAdapter extends RecyclerView.Adapter<myEventsRecyclerViewAdapter.ViewHolder> {

    private ArrayList<Event> events;
    private Context mContext;

    myEventsRecyclerViewAdapter(ArrayList<Event> events, Context mContext) {
        this.events = events;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.
                layout_list_myevents, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.textViewMyEventName.setText(events.get(i).eventName);
        viewHolder.textViewMyEventLocation.setText("Location: " + events.get(i).eventLocation);
        viewHolder.textViewMyEventDate.setText("Date: " + events.get(i).eventDate);
        viewHolder.textViewCapacity.setText("Capacity: " + events.get(i).numAttendees.toString() +
                "/" + events.get(i).maxCapacity);
        viewHolder.textViewMyEventDescription.setText(events.get(i).eventDescription);

        viewHolder.my_events_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, events.get(i).eventName, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewMyEventName, textViewMyEventLocation, textViewMyEventDate, textViewCapacity,
                textViewMyEventDescription;
        RelativeLayout my_events_layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewMyEventName = itemView.findViewById(R.id.textViewMyEventName);
            textViewMyEventLocation = itemView.findViewById(R.id.textViewMyEventLocation);
            textViewMyEventDate = itemView.findViewById(R.id.textViewMyEventDate);
            textViewCapacity = itemView.findViewById(R.id.textViewCapacity);
            textViewMyEventDescription = itemView.findViewById(R.id.textViewMyEventDescription);
            my_events_layout = itemView.findViewById(R.id.my_events_layout);
        }
    }
}
