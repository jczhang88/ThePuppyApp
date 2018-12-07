package com.example.jeffrey_zhang.thepuppyapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.content.ContentValues.TAG;

// Implement ClickListener
public class ThePuppyApp extends Activity implements View.OnClickListener {

    // Initialize objects
    private Button buttonLogin, buttonRegister;
    private EditText editTextEmail, editTextPassword;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_puppy_app);

        // Attach objects to Layout widgets
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonRegister = findViewById(R.id.buttonRegister);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);

        // Make button clickable
        buttonLogin.setOnClickListener(this);
        buttonRegister.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "on.AuthStateChanged:signed_out");
                }
            }
        };
    }

    @Override
    public void onClick(View view) {
        // Button to log in
        if (view == buttonLogin) {
            String password = editTextPassword.getText().toString();
            String email = editTextEmail.getText().toString();

            signIn(email, password);
        } else if (view == buttonRegister) {
            Intent register = new Intent(ThePuppyApp.this, Register.class);
            startActivity(register);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    public void signIn (String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            Intent loginIntent = new Intent(ThePuppyApp.this, HomePage.class);
                            startActivity(loginIntent);
                            Toast.makeText(ThePuppyApp.this, "Logged in!",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(ThePuppyApp.this, "Login failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}