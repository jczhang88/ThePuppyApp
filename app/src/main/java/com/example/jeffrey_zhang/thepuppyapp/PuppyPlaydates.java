package com.example.jeffrey_zhang.thepuppyapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
        if (view == buttonMyEvents) {
            Intent myEvents = new Intent(PuppyPlaydates.this, MyEvents.class);
            startActivity(myEvents);
        } else if (view == buttonCreateEvents) {
            Intent createEvents = new Intent(PuppyPlaydates.this, CreateEvents.class);
            startActivity(createEvents);
        }
    }
}
