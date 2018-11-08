package com.example.jeffrey_zhang.thepuppyapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

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
    public void onClick(View view) {
        if (view == imageButtonPlaydates) {
            Intent PlaydatesIntent = new Intent(HomePage.this, PuppyPlaydates.class);
            startActivity(PlaydatesIntent);
        }
        else if (view == imageButtonBarkBoard) {
            Intent BarkBoardIntent = new Intent(HomePage.this, BarkBoard.class);
            startActivity(BarkBoardIntent);
        }
        else if (view == imageButtonFetch) {
            Intent FetchIntent = new Intent(HomePage.this, BrowseDoggies.class);
            startActivity(FetchIntent);
        } else {
            Intent MapIntent = new Intent(HomePage.this, Map.class);
            startActivity(MapIntent);
        }
    }
}
