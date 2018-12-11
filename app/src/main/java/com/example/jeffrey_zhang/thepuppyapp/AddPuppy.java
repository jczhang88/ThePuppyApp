package com.example.jeffrey_zhang.thepuppyapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddPuppy extends Activity implements View.OnClickListener {

    private static final int PICK_IMAGE_REQUEST = 1;
    private EditText editTextAddPuppyName;
    private EditText editTextAddPuppyTemperament;
    private EditText editTextAddPuppyBreed;
    private EditText editTextAddPuppyAge;
    private Button buttonAddPuppySelectImage;
    private Button buttonAddPuppyUploadImage;
    private Button buttonAddPuppyAdd;
    private StorageReference mStorageRef;
    private Uri selectedImageUri;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_puppy);

        editTextAddPuppyName = findViewById(R.id.editTextAddPuppyName);
        editTextAddPuppyTemperament = findViewById(R.id.editTextAddPuppyTemperament);
        editTextAddPuppyBreed = findViewById(R.id.editTextAddPuppyBreed);
        editTextAddPuppyAge = findViewById(R.id.editTextAddPuppyAge);
        buttonAddPuppySelectImage = findViewById(R.id.buttonAddPuppySelectImage);
        buttonAddPuppyUploadImage = findViewById(R.id.buttonAddPuppyUploadImage);
        buttonAddPuppyAdd = findViewById(R.id.buttonAddPuppyAdd);

        mAuth = FirebaseAuth.getInstance();
        mStorageRef = FirebaseStorage.getInstance().getReference();

        buttonAddPuppySelectImage.setOnClickListener(this);
        buttonAddPuppyUploadImage.setOnClickListener(this);
        buttonAddPuppyAdd.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view == buttonAddPuppyAdd){
            addPuppy();
        }

        else if(view == buttonAddPuppySelectImage){
            selectImage();
        }

        else if(view == buttonAddPuppyUploadImage){
            uploadFile();
        }
    }

    private void addPuppy(){
        final FirebaseUser user = mAuth.getCurrentUser();
        final String newName = editTextAddPuppyName.getText().toString().trim();
        final String newTemperament = editTextAddPuppyTemperament.getText().toString().trim();
        final String newBreed = editTextAddPuppyBreed.getText().toString().trim();
        final int age = Integer.parseInt(editTextAddPuppyAge.getText().toString().trim());

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("users");

        Query puppy_owner = myRef.orderByChild("emailAddress").equalTo(user.getEmail());


        puppy_owner.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if(dataSnapshot.exists()){


                    User dogOwner = dataSnapshot.getValue(User.class);

                    final FirebaseDatabase database = FirebaseDatabase.getInstance();
                    final DatabaseReference dogRef = database.getReference("dogs/"
                            + dogOwner.getUserID() + "/" + newName);

                    Dog newDog = new Dog(newName, newBreed, age, newTemperament,
                            dogOwner.getDisplayName(), dogOwner.getUserID());
                    dogRef.setValue(newDog);

                    finish();
                    startActivity(new Intent(AddPuppy.this, MyProfile.class));
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
        //saves date of report and under milliseconds converted from current date to
        //make sure that sightings are not overwritten in the database.

    }

    private void selectImage(){
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

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void uploadFile(){
        final FirebaseUser user = mAuth.getCurrentUser();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("users");

        final Query dog_owner = myRef.orderByChild("emailAddress").equalTo(user.getEmail());

        dog_owner.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if(dataSnapshot.exists()){
                    User dogOwner = dataSnapshot.getValue(User.class);

                    StorageReference propicRef = mStorageRef.child("images/puppyProfilePics/"
                            + user.getUid() + "/" + dogOwner.getNum_dogs() + "/");

                    dogOwner.setNum_dogs(dogOwner.getNum_dogs() + 1);
                    final DatabaseReference userRef = database.getReference("users/" + user.getUid());

                    userRef.setValue(dogOwner);

                    propicRef.putFile(selectedImageUri)
                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    // Get a URL to the uploaded content
                                    Toast.makeText(AddPuppy.this, "Upload Successful",
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
