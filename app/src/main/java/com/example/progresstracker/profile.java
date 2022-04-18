package com.example.progresstracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

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
        userName.setVisibility(View.INVISIBLE);
        userGroup.setVisibility(View.INVISIBLE);
        userPTS.setVisibility(View.INVISIBLE);
        String curUID = FirebaseAuth.getInstance().getUid();
        DatabaseReference uRef = FirebaseDatabase.getInstance().getReference("users");
        ArrayList<String> usersID = new ArrayList<>();
        ArrayList<User> users = new ArrayList<>();
        uRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                users.clear();
                for (DataSnapshot userSnap : snapshot.getChildren()){
                    User user = userSnap.getValue(User.class);
                    users.add(user);
                    String uid = userSnap.getKey();
                    usersID.add(uid);
                }
                if (usersID.indexOf(curUID) != -1) {
                    User curUser = users.get(usersID.indexOf(curUID));
                    generateProfilePage(curUser);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        logOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(profile.this,Authactivity.class);
                startActivity(intent);

            }
        });


    }

    private void generateProfilePage(User user){
        userName.setVisibility(View.VISIBLE);
        userGroup.setVisibility(View.VISIBLE);
        userPTS.setVisibility(View.VISIBLE);
        userName.setText(user.getName());
        userGroup.setText(user.getGroup());
        userPTS.setText(user.getPts());
    }
}