package com.example.jeffrey_zhang.thepuppyapp;

import android.app.Activity;
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

        } else if (view == buttonCreateEvents) {

        }
    }
}
