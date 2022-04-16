package com.example.progresstracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Authactivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private EditText ETemail;
    private EditText ETpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authactivity);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    startNewActivity();


                } else {
                    // User is signed out

                }

            }
        };

        ETemail = (EditText) findViewById(R.id.et_email);
        ETpassword = (EditText) findViewById(R.id.et_password);
    }

    private void startNewActivity() {
        Intent intent = new Intent(this, Mainpage_Teacher.class);
        intent.putExtra("useremail", ETemail.getText().toString());
        startActivity(intent);
    }

    public void onClick(View view) {
        if(view.getId() == R.id.btn_sign_in)
        {
            if (ETemail.getText().toString().length() != 0 && ETpassword.getText().toString().length() != 0 ) {
                signin(ETemail.getText().toString(),ETpassword.getText().toString());}
            else{
                Toast.makeText(Authactivity.this, "Aвторизация провалена, неверен один и/или несколько аргументов", Toast.LENGTH_SHORT).show();
            }
        }else if (view.getId() == R.id.btn_registration)
        {   if (ETemail.getText().toString().length() != 0 && ETpassword.getText().toString().length() != 0 ) {
            registration(ETemail.getText().toString(),ETpassword.getText().toString());}
        else{
            Toast.makeText(Authactivity.this, "Регистрация провалена, неверен один и/или несколько аргументов", Toast.LENGTH_SHORT).show();
        }
        }

    }

    public void signin(String email , String password)
    {
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(Authactivity.this, "Aвторизация успешна", Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(Authactivity.this, "Aвторизация провалена, пользователь не существует или произошла ошибка сервера", Toast.LENGTH_SHORT).show();

            }
        });
    }
    public void registration (String email , String password){
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    DatabaseReference dRef = FirebaseDatabase.getInstance().getReference("users");
                    User user = new User(FirebaseAuth.getInstance().getUid(), "null", "100");
                    dRef.child(FirebaseAuth.getInstance().getUid()).setValue(user);
                    Toast.makeText(Authactivity.this, "Регистрация успешна", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(Authactivity.this, "Регистрация провалена, пользователь существует или произошла ошибка сервера", Toast.LENGTH_SHORT).show();
            }
        });
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

}