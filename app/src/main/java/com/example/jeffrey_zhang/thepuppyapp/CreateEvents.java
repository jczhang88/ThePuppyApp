package com.example.jeffrey_zhang.thepuppyapp;

import android.app.Activity;
import android.os.Bundle;
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
    public void onClick(View view) {
        if (view == buttonCreateYourEvent) {
            String eventName = editTextCreateEventName.getText().toString();
            String eventLocation = editTextCreateEventLocation.getText().toString();
            String eventDate = editTextCreateEventDate.getText().toString();
            String eventDescription = editTextCreateEventDescription.getText().toString();
            String stringCapacity = editTextCapacity.getText().toString();
            Integer maxCapacity = Integer.parseInt(stringCapacity);

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
