package com.example.jeffrey_zhang.thepuppyapp;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class BarkBoard extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bark_board);
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

}
