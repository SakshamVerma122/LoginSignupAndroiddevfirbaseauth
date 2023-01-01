package com.example.loginsignupfirebaseauth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPage extends AppCompatActivity {
    EditText etLoginPassword, etLoginEmail;
    Button btnSignin, btnLogin;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        // Initialisation of buttons
        btnSignin = findViewById(R.id.directsignup);
        btnLogin = findViewById(R.id.login);

        // Initialisation of Edit texts
        etLoginEmail = findViewById(R.id.EmailAddress);
        etLoginPassword = findViewById(R.id.password);
        mAuth = FirebaseAuth.getInstance();

        //////////////////////////////////// SIGN IN BUTTON REDIRECT /////////////////////////////////////////////////
        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iNext;
                iNext = new Intent(LoginPage.this, SignupPage.class);
                startActivity(iNext);
            }
        });

        //////////////////////////////////////////// LOG IN BUTTON /////////////////////////////////////////////////
        // This is the main functionality
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });
    }

    ///////////////////////////////////////////// LOG IN FUNCTIONALITY /////////////////////////////////////////////
    private void loginUser() {
        String email = etLoginEmail.getText().toString();
        String password = etLoginPassword.getText().toString();
        if (TextUtils.isEmpty(email)) {
            etLoginEmail.setError("Email can't be empty");
            etLoginEmail.requestFocus();
        } else if (TextUtils.isEmpty(password)) {
            etLoginPassword.setError("Password can't be empty");
            etLoginPassword.requestFocus();
        } else {
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        // Showing toast message
                        Toast.makeText(LoginPage.this, "User Logged in successfully", Toast.LENGTH_SHORT).show();

                        // passing the intent
                        Intent iNext;
                        // Make a blank page which it will lead to
                        iNext = new Intent(LoginPage.this, MainActivity.class);
                        startActivity(iNext);
                    } else {
                        // Showeing the failed attempt
                        Toast.makeText(LoginPage.this, "User Logged in un-successful", Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }
    }
}