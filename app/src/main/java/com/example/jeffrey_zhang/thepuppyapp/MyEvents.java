package com.example.jeffrey_zhang.thepuppyapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MyEvents extends Activity {

    private ArrayList<Event> events;
    private myEventsRecyclerViewAdapter myEventsRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_events);

        events = new ArrayList<>();
        initRecyclerView();
        getEvents();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater optionsMenuInflater = getMenuInflater();
        optionsMenuInflater.inflate(R.menu.dropdown_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.myProfileMenuItem:
                startActivity(new Intent(this, MyProfile.class));
                return true;
            case R.id.homepageMenuItem:
                startActivity(new Intent(this, HomePage.class));
                return true;
            case R.id.puppyPlaydatesMenuItem:
                startActivity(new Intent(this, PuppyPlaydates.class));
                return true;
            case R.id.barkBoardMenuItem:
                startActivity(new Intent(this, BarkBoard.class));
                return true;
            case R.id.fetchMenuItem:
                startActivity(new Intent(this, BrowseDoggies.class));
                return true;
            case R.id.mapMenuItem:
                startActivity(new Intent(this, Map.class));
                return true;
            case R.id.signOutMenuItem:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(this, ThePuppyApp.class));
                Toast.makeText(this, "Signed out", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return false;
        }
    }

    private void getEvents() {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference eventsRef = database.getReference("events").child(uid);

        // Read from the database
        eventsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Event event = child.getValue(Event.class);
                    events.add(event);
                }
                myEventsRecyclerViewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void initRecyclerView() {
        RecyclerView recyclerViewMyEvents = findViewById(R.id.recyclerViewMyEvents);
        myEventsRecyclerViewAdapter = new myEventsRecyclerViewAdapter(events, this);
        recyclerViewMyEvents.setAdapter(myEventsRecyclerViewAdapter);
        recyclerViewMyEvents.setLayoutManager(new LinearLayoutManager(this));
    }
}
