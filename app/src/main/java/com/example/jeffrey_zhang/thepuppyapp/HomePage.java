package com.example.jeffrey_zhang.thepuppyapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class HomePage extends Activity implements View.OnClickListener{

    private ImageButton imageButtonPlaydates, imageButtonBarkBoard, imageButtonFetch, imageButtonMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        imageButtonPlaydates = findViewById(R.id.imageButtonPlaydates);
        imageButtonBarkBoard = findViewById(R.id.imageButtonBarkBoard);
        imageButtonFetch = findViewById(R.id.imageButtonFetch);
        imageButtonMap = findViewById(R.id.imageButtonMap);

        imageButtonPlaydates.setOnClickListener(this);
        imageButtonBarkBoard.setOnClickListener(this);
        imageButtonFetch.setOnClickListener(this);
        imageButtonMap.setOnClickListener(this);
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
        if (view == imageButtonPlaydates) {
            Intent playdatesIntent = new Intent(HomePage.this, PuppyPlaydates.class);
            startActivity(playdatesIntent);
        } else if (view == imageButtonBarkBoard) {
            Intent barkboardIntent = new Intent(HomePage.this, BarkBoard.class);
            startActivity(barkboardIntent);
        } else if (view == imageButtonFetch) {
            Intent fetchIntent = new Intent(HomePage.this, BrowseDoggies.class);
            startActivity(fetchIntent);
        } else {
            Intent mapIntent = new Intent(HomePage.this, Map.class);
            startActivity(mapIntent);
        }
    }
}
