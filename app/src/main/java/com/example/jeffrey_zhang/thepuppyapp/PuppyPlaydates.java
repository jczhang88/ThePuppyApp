package com.example.jeffrey_zhang.thepuppyapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PuppyPlaydates extends Activity implements View.OnClickListener {

    private Button buttonMyEvents, buttonCreateEvents;
    private ArrayList<Event> playdates;
    private playdatesRecyclerViewAdapter playdatesRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puppy_playdates);

        buttonMyEvents = findViewById(R.id.buttonMyEvents);
        buttonCreateEvents = findViewById(R.id.buttonCreateEvents);

        buttonMyEvents.setOnClickListener(this);
        buttonCreateEvents.setOnClickListener(this);

        playdates = new ArrayList<>();
        initplaydatesRecyclerView();
        getPlaydates();
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

    @Override
    public void onClick(View view) {
        if (view == buttonMyEvents) {
            Intent myEvents = new Intent(PuppyPlaydates.this, MyEvents.class);
            startActivity(myEvents);
        } else if (view == buttonCreateEvents) {
            Intent createEvents = new Intent(PuppyPlaydates.this, CreateEvents.class);
            startActivity(createEvents);
        }
    }

    private void getPlaydates() {

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference usersRef = database.getReference("users");

        // Read from the database
        usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot userSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for (DataSnapshot userChild : userSnapshot.getChildren()) {
                    User user = userChild.getValue(User.class);
                    final String userID = user.userID;

                    DatabaseReference eventsRef = database.getReference("events");
                    eventsRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot eventSnapshot) {
                            for (DataSnapshot eventChild : eventSnapshot.getChildren()) {
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                String uid = user.getUid();


                                if (userID.equals(uid)) {
                                    Event playdate = eventChild.getValue(Event.class);
                                    playdates.add(playdate);
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }

                    });
                }
                playdatesRecyclerViewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void initplaydatesRecyclerView() {
        RecyclerView recyclerViewPlaydates = findViewById(R.id.recyclerViewPlaydates);
        playdatesRecyclerViewAdapter = new playdatesRecyclerViewAdapter(playdates, this);
        recyclerViewPlaydates.setAdapter(playdatesRecyclerViewAdapter);
        recyclerViewPlaydates.setLayoutManager(new LinearLayoutManager(this));
    }
}
