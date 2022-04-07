package com.example.progresstracker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class quizpage extends AppCompatActivity {

    TextView question, qnum, inpAnsw;
    Button inpConfirm, tfAnswTrue, tfAnswFalse, VarAnsw1, VarAnsw2, VarAnsw3, VarAnsw4;
    View inputansw, varansw, tfansw;
    private DatabaseReference mDatabase;
    QuizBuild quiz;
    Integer count, score;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizpage);
        question = findViewById(R.id.Question);
        qnum = findViewById(R.id.questionNum);
        inpAnsw = findViewById(R.id.inpAnsw);
        inpConfirm = findViewById(R.id.inpConfirm);
        tfAnswFalse = findViewById(R.id.tfAnsvFalse);
        tfAnswTrue = findViewById(R.id.tfAnsvTrue);
        VarAnsw1 = findViewById(R.id.VarAnsw1);
        VarAnsw2 = findViewById(R.id.VarAnsw2);
        VarAnsw3 = findViewById(R.id.VarAnsw3);
        VarAnsw4 = findViewById(R.id.VarAnsw4);
        inputansw = findViewById(R.id.inputansw);
        varansw = findViewById(R.id.VarAnsw);
        tfansw = findViewById(R.id.tfAnsw);
        inputansw.setVisibility(View.INVISIBLE);
        varansw.setVisibility(View.INVISIBLE);
        tfansw.setVisibility(View.INVISIBLE);
        mDatabase = FirebaseDatabase.getInstance().getReference("quiz");
        mDatabase.orderByChild("quiz/group").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                // Вывод по группе
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}