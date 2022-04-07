package com.example.progresstracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

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
    }
}