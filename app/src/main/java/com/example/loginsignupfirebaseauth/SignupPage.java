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

public class SignupPage extends AppCompatActivity {
    private FirebaseAuth mAuth;
    Button btnCreateAccount, btnTracelogin;
    EditText etReRegPassword,etRegPassword,etRegEmail;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);

        // Getting the button
        btnCreateAccount = findViewById(R.id.CreateAccount);
        btnTracelogin = findViewById(R.id.Tracelogin);

        // Getting the variables
        etReRegPassword = findViewById(R.id.SignupRePassword);
        etRegPassword = findViewById(R.id.Signuppassword);
        etRegEmail = findViewById(R.id.Email);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        ////////////////////////////////////////////////////////// SIGN UP BUTTON ////////////////////////////////////////////

        // Here main operations will be performed
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Storing users details in the google firestore after authentication
                createUser();
            }
        });

        ////////////////////////////////////////////////////////// LOGIN Trace button ///////////////////////////////////////
        btnTracelogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iNext;

                iNext = new Intent(SignupPage.this,LoginPage.class);
                startActivity(iNext);
            }
        });
    }

    private void createUser()
    {
        String email = etRegEmail.getText().toString();
        String password = etRegPassword.getText().toString();
        String repassword = etReRegPassword.getText().toString();

        if(TextUtils.isEmpty(email)){
            etRegEmail.setError("Email can't be empty");
            etRegEmail.requestFocus();
        }
        else if(TextUtils.isEmpty(password)){
            etRegPassword.setError("Password can't be empty");
            etRegPassword.requestFocus();
        }
        else if(!repassword.equals(password))
        {
            etReRegPassword.setError("Passwords are different");
            etReRegPassword.requestFocus();

            etRegPassword.setError("Password are different");
            etRegPassword.requestFocus();
        }
        else
        {
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(SignupPage.this,"User registered successfully",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SignupPage.this,LoginPage.class));
                    }
                    else{
                        Toast.makeText(SignupPage.this,"User registered un-successfully",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}