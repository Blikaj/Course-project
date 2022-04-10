package com.example.progresstracker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Mainpage_Teacher extends AppCompatActivity {

    Button generator, leaderboard, profile;
    ListView quizlistview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage_teacher);
        generator = findViewById(R.id.generator);
        leaderboard = findViewById(R.id.leaderboard);
        profile = findViewById(R.id.profile);
        quizlistview = findViewById(R.id.quizlistview);

        ArrayList<String> list = new ArrayList<>();
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.menuitem, list);
        quizlistview.setAdapter(adapter);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("quiz");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    QuizBuild qbuild = snapshot.getValue(QuizBuild.class);
                    String txt = qbuild.getName() + " : " + qbuild.getNumofquestions().toString();
                    list.add(txt);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }


    public void genQuiz(View view){
        Intent intent = new Intent(this, quizgen.class);
        startActivity(intent);
    }
}