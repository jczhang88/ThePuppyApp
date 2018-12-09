package com.example.jeffrey_zhang.thepuppyapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PuppyPlaydates extends Activity implements View.OnClickListener {

    private Button buttonMyEvents, buttonCreateEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puppy_playdates);

        buttonMyEvents = findViewById(R.id.buttonMyEvents);
        buttonCreateEvents = findViewById(R.id.buttonCreateEvents);

        buttonMyEvents.setOnClickListener(this);
        buttonCreateEvents.setOnClickListener(this);
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
}
