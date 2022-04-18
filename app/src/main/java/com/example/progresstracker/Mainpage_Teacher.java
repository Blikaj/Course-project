package com.example.progresstracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;

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
        ArrayList<QuizArray> quizArrayArray = new ArrayList<>();
        ArrayList<ArrayList> quizDoubleArray = new ArrayList<>();
        ArrayList<Integer> qmaxArray = new ArrayList<>();
        ArrayList<String> QuizID = new ArrayList<>();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("quiz");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                qmaxArray.clear();
                quizDoubleArray.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    QuizID.add(snapshot.getKey().toString());
                    QuizBuild qbuild = snapshot.getValue(QuizBuild.class);
                    String txt = qbuild.getName();
                    Integer qmax = qbuild.getNumofquestions();
                    list.add(txt);
                    qmaxArray.add(qmax);
                    quizArrayArray.clear();
                    for (DataSnapshot quizSnap : snapshot.child("quizArray").getChildren()){
                        quizArrayArray.add(quizSnap.getValue(QuizArray.class));
                    }
                    quizDoubleArray.add(quizArrayArray);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        quizlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(Mainpage_Teacher.this, quizpage.class);
                intent.putExtra("Quizname", quizlistview.getItemAtPosition(i).toString());
                Integer qMaxNum = qmaxArray.get(i);
                intent.putExtra("QMax", qMaxNum.toString());
                String s = "quiz/"+QuizID.get(i)+"/quizArray";
                intent.putExtra("Path", s);
                startActivity(intent);
            }
        });


        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Mainpage_Teacher.this, profile.class);
                startActivity(intent);
            }
        });

        leaderboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Mainpage_Teacher.this, Leaderboard.class);
                startActivity(intent);
            }
        });


    }


    public void genQuiz(View view){
        Intent intent = new Intent(this, quizgen.class);
        startActivity(intent);
    }
}