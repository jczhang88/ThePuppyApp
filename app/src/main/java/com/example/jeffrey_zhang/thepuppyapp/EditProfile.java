package com.example.jeffrey_zhang.thepuppyapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class EditProfile extends Activity implements View.OnClickListener {

    private final int PICK_IMAGE_REQUEST = 1;
    private Button buttonSelectImage;
    private Button buttonUploadImage;
    private ImageView imageViewProfilePicture;
    private StorageReference mStorageRef;
    private Uri selectedImageUri;
    private FirebaseAuth mAuth;
    private EditText editTextDisplayName;
    private Button buttonEditName;
    private Button buttonAddPuppy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        buttonSelectImage = findViewById(R.id.buttonSelectImage);
        buttonUploadImage = findViewById(R.id.buttonUploadImage);
        imageViewProfilePicture = findViewById(R.id.imageViewProfilePicture);
        mStorageRef = FirebaseStorage.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        editTextDisplayName= findViewById(R.id.editTextDisplayName);
        buttonEditName = findViewById(R.id.buttonEditName);
        buttonAddPuppy = findViewById(R.id.buttonAddPuppy);

        buttonSelectImage.setOnClickListener(this);
        buttonUploadImage.setOnClickListener(this);
        buttonEditName.setOnClickListener(this);
        buttonAddPuppy.setOnClickListener(this);
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
                finish();
                startActivity(new Intent(this, MyProfile.class));
                return true;
            case R.id.homepageMenuItem:
                finish();
                startActivity(new Intent(this, HomePage.class));
                return true;
            case R.id.puppyPlaydatesMenuItem:
                finish();
                startActivity(new Intent(this, PuppyPlaydates.class));
                return true;
            case R.id.barkBoardMenuItem:
                finish();
                startActivity(new Intent(this, BarkBoard.class));
                return true;
            case R.id.fetchMenuItem:
                finish();
                startActivity(new Intent(this, BrowseDoggies.class));
                return true;
            case R.id.mapMenuItem:
                finish();
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
        if (view == buttonSelectImage){
            selectImages();
        }

        else if(view == buttonUploadImage){
            uploadFile();
        }

        else if(view == buttonEditName){
            editName();
        }

        else if(view == buttonAddPuppy){
            startActivity(new Intent(EditProfile.this, AddPuppy.class));
        }
    }

    private void selectImages(){
        Intent intent = new Intent();
        // Show only images, no videos or anything else
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        // Always show the chooser (if there are multiple options available)
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();
            selectedImageUri = uri;
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                // Log.d(TAG, String.valueOf(bitmap));

                imageViewProfilePicture = (ImageView) findViewById(R.id.imageViewProfilePicture);
                imageViewProfilePicture.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void uploadFile(){
        FirebaseUser user = mAuth.getCurrentUser();
        StorageReference propicRef = mStorageRef.child("images/userProfilePics/" + user.getUid()
                + "/");

        propicRef.putFile(selectedImageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                        Toast.makeText(EditProfile.this, "Upload Successful",
                                Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...
                    }
                });
    }

    private void editName(){
        final FirebaseUser user = mAuth.getCurrentUser();
        final String newName = editTextDisplayName.getText().toString().trim();

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("users");

        Query name_change = myRef.orderByChild("emailAddress").equalTo(user.getEmail());

        name_change.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if(dataSnapshot.exists()){
                    User editUser = dataSnapshot.getValue(User.class);

                    editUser.setDisplayName(newName);

                    myRef.child(user.getUid()).setValue(editUser);


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


    }
}
