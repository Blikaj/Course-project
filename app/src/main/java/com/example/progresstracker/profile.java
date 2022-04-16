package com.example.progresstracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class profile extends AppCompatActivity {

    Button logOutBtn;
    TextView userName, userGroup, userPTS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        logOutBtn = findViewById(R.id.logoutButton);
        userName = findViewById(R.id.userName);
        userGroup = findViewById(R.id.userGroup);
        userPTS = findViewById(R.id.userGroup);
        DatabaseReference uRef = FirebaseDatabase.getInstance().getReference("user/"+ FirebaseAuth.getInstance().getUid());

    }
}