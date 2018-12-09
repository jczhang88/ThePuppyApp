package com.example.jeffrey_zhang.thepuppyapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

        }
    }
}
