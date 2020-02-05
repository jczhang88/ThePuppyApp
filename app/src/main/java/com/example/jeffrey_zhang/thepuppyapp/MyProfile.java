package com.example.jeffrey_zhang.thepuppyapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.net.URI;

public class MyProfile extends Activity implements View.OnClickListener {

    private TextView textViewMyProfileName;
    private Button buttonEditProfile;
    private FirebaseAuth mAuth;
    private ImageView imageViewProfilePicture;
    private StorageReference mStorageRef2;
    private TextView textViewBio;
    private TextView textViewLocation;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        textViewMyProfileName = findViewById(R.id.textViewMyProfilesName);

        buttonEditProfile = findViewById(R.id.buttonEditProfile);
        imageViewProfilePicture = findViewById(R.id.imageView3);
        textViewBio = findViewById(R.id.textView8);
        textViewLocation = findViewById(R.id.textView7);
        mStorageRef2 = FirebaseStorage.getInstance().getReference();

        try{
            downloadAndSetImage();
        } catch(IOException exc){

        }

        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = mAuth.getCurrentUser();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference users_ref = database.getReference("users");

        Query ref = users_ref.orderByChild("emailAddress").equalTo(user.getEmail());

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if(dataSnapshot.exists()){

                    User curr_user = dataSnapshot.getValue(User.class);
                    String name = curr_user.getDisplayName();
                    textViewMyProfileName.setText(name);
                    textViewBio.setText(curr_user.getBio());
                    textViewLocation.setText(curr_user.getLocation());
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        buttonEditProfile.setOnClickListener(this);

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

    private void downloadAndSetImage() throws IOException {
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        StorageReference profilePicRef = mStorageRef2.child("images/userProfilePics/"
                + user.getUid() + "/");
        final File localFile = File.createTempFile("images", "jpg");
        profilePicRef.getFile(localFile)
                .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                        // Successfully downloaded data to local file
                        // ..
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.fromFile(localFile));
                            imageViewProfilePicture.setImageBitmap(bitmap);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle failed download
                // ...
            }
        });
    }

    @Override
    public void onClick(View view) {
        if(view == buttonEditProfile){
            startActivity(new Intent(MyProfile.this, EditProfile.class));
        }
    }
}
