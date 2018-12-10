package com.example.jeffrey_zhang.thepuppyapp;

import android.app.Activity;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MyEvents extends Activity {

    private ArrayList<Event> events;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_events);
        initEvents();
    }

    private void initEvents() {
        events = new ArrayList<>();
        initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerView recyclerViewMyEvents = findViewById(R.id.recyclerViewMyEvents);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(events, this);
        recyclerViewMyEvents.setAdapter(recyclerViewAdapter);
        recyclerViewMyEvents.setLayoutManager(new LinearLayoutManager(this));
    }
}
