package com.example.jeffrey_zhang.thepuppyapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateEvents extends Activity implements View.OnClickListener {

    private Button buttonCreateYourEvent;
    private EditText editTextCreateEventName, editTextCreateEventLocation, editTextCreateEventDate,
                     editTextCreateEventDescription, editTextCapacity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_events);

        buttonCreateYourEvent = findViewById(R.id.buttonCreateYourEvent);
        editTextCreateEventName = findViewById(R.id.editTextCreateEventName);
        editTextCreateEventLocation = findViewById(R.id.editTextCreateEventLocation);
        editTextCreateEventDate = findViewById(R.id.editTextCreateEventDate);
        editTextCreateEventDescription = findViewById(R.id.editTextCreateEventDescription);
        editTextCapacity = findViewById(R.id.editTextCapacity);

        buttonCreateYourEvent.setOnClickListener(this);
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
            default:
                return false;
        }
    }

    @Override
    public void onClick(View view) {
        if (view == buttonCreateYourEvent) {
            String eventName = editTextCreateEventName.getText().toString();
            String eventLocation = editTextCreateEventLocation.getText().toString();
            String eventDate = editTextCreateEventDate.getText().toString();
            String eventDescription = editTextCreateEventDescription.getText().toString();
            String maxCapacity = editTextCapacity.getText().toString();

            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();

            // Calls Firebase database and gets existing item
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("events").child(uid);

            // Add a new bird sighting entry in Firebase database based on the user's information
            Event event = new Event(eventName, eventLocation, eventDate, eventDescription,
                    maxCapacity, 0);

            // Write the user's input into Firebase database
            myRef.push().setValue(event);
            Toast.makeText(CreateEvents.this, "Successfully created your event!",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
