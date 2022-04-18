package com.example.progresstracker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

public class quizpage extends AppCompatActivity {

    TextView question, qnum, inpAnsw;
    Button inpConfirm, tfAnswTrue, tfAnswFalse, VarAnsw1, VarAnsw2, VarAnsw3, VarAnsw4;
    View inputansw, varansw, tfansw;
    QuizArray quiz;
    Integer count=0, score=0, curPos, countMax, pts;
    ArrayList<QuizArray> quizArrayArray;
    String qType, ic;


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
        Random random = new Random();
        Intent itnt = getIntent();
        String quizname = itnt.getStringExtra("Quizname");
        countMax = Integer.parseInt(itnt.getStringExtra("QMax"))-1;
        String path = itnt.getStringExtra("Path");
        DatabaseReference quizArrayReference = FirebaseDatabase.getInstance().getReference(path);
        quizArrayArray = new ArrayList<>();
        quizArrayReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot quizSnap : snapshot.getChildren()){
                    quizArrayArray.add(quizSnap.getValue(QuizArray.class));
                }
                setDataToView(count);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        inpConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count < countMax) {
                    if (quizArrayArray.get(count).getAnswer().trim().toLowerCase().equals(inpAnsw.getText().toString().trim().toLowerCase())) {
                        score++;
                    }
                    count++;
                    setDataToView(count);
                }
                else{
                    if (quizArrayArray.get(count).getAnswer().trim().toLowerCase().equals(inpAnsw.getText().toString().trim().toLowerCase())) {
                        score++;
                    }
                    btmdialog();
                }
            }
        });

        tfAnswTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count < countMax) {
                    Button b = (Button) view;
                    if (quizArrayArray.get(count).getAnswer().trim().toUpperCase().equals("TRUE".trim())) {
                        score++;
                    }
                    count++;
                    setDataToView(count);
                }
                else{
                    if (quizArrayArray.get(count).getAnswer().trim().toUpperCase().equals("TRUE".trim())) {
                        score++;
                    }
                    btmdialog();
                }
            }
        });

        tfAnswFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count < countMax) {
                    if (quizArrayArray.get(count).getAnswer().trim().toUpperCase().equals("FALSE".trim())) {
                        score++;
                    }
                    count++;
                    setDataToView(count);
                }
                else{
                    if (quizArrayArray.get(count).getAnswer().trim().toUpperCase().equals("FALSE".trim())) {
                        score++;
                    }
                    btmdialog();
                }
            }
        });

        VarAnsw1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count < countMax) {
                    if (quizArrayArray.get(count).getAnswer().trim().toUpperCase().equals(VarAnsw1.getText().toString().trim().toUpperCase())) {
                        score++;
                    }
                    count++;
                    setDataToView(count);
                }
                else{
                    if (quizArrayArray.get(count).getAnswer().trim().toUpperCase().equals(VarAnsw1.getText().toString().trim().toUpperCase())) {
                        score++;
                    }
                    btmdialog();
                }
            }
        });

        VarAnsw2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count < countMax) {
                    if (quizArrayArray.get(count).getAnswer().trim().equals(VarAnsw2.getText().toString().trim())) {
                        score++;
                    }
                    count++;
                    setDataToView(count);
                }
                else{
                    if (quizArrayArray.get(count).getAnswer().trim().equals(VarAnsw2.getText().toString().trim())) {
                        score++;
                    }
                    btmdialog();
                }
            }
        });

        VarAnsw3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count < countMax) {
                    if (quizArrayArray.get(count).getAnswer().trim().equals(VarAnsw3.getText().toString().trim())) {
                        score++;
                    }
                    count++;
                    setDataToView(count);
                }
                else{
                    if (quizArrayArray.get(count).getAnswer().trim().equals(VarAnsw3.getText().toString().trim())) {
                        score++;
                    }
                    btmdialog();
                }
            }
        });

        VarAnsw4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count < countMax) {
                    if (quizArrayArray.get(count).getAnswer().equals(VarAnsw4.getText().toString())) {
                        score++;
                    }
                    count++;
                    setDataToView(count);
                }
                else{
                    if (quizArrayArray.get(count).getAnswer().trim().equals(VarAnsw4.getText().toString().trim())) {
                        score++;
                    }
                    btmdialog();
                }
            }
        });
    }

    private void setDataToView(Integer curPos) {
        qnum.setText("Question " + (curPos + 1) + " / " + (countMax+1));
        qType = (quizArrayArray.get(curPos).getType());
        question.setText(quizArrayArray.get(curPos).getQuestion().toUpperCase());
        switch (qType) {
            case "1":
                inputansw.setVisibility(View.VISIBLE);
                tfansw.setVisibility(View.INVISIBLE);
                varansw.setVisibility(View.INVISIBLE);
                break;
            case "2":
                inputansw.setVisibility(View.INVISIBLE);
                tfansw.setVisibility(View.VISIBLE);
                varansw.setVisibility(View.INVISIBLE);
                break;
            case "3":
                inputansw.setVisibility(View.INVISIBLE);
                tfansw.setVisibility(View.INVISIBLE);
                varansw.setVisibility(View.VISIBLE);
                ArrayList<String> vars = new ArrayList<>();
                vars.add(quizArrayArray.get(curPos).getOption1().toUpperCase());
                vars.add(quizArrayArray.get(curPos).getOption2().toUpperCase());
                vars.add(quizArrayArray.get(curPos).getOption3().toUpperCase());
                vars.add(quizArrayArray.get(curPos).getOption4().toUpperCase());
                Random rndm = new Random();
                VarAnsw1.setText(quizArrayArray.get(curPos).getOption1().toUpperCase());
                VarAnsw2.setText(quizArrayArray.get(curPos).getOption2().toUpperCase());
                VarAnsw3.setText(quizArrayArray.get(curPos).getOption3().toUpperCase());
                VarAnsw4.setText(quizArrayArray.get(curPos).getOption4().toUpperCase());
                break;
            default:
                break;
        }
    }

    private void btmdialog(){
        BottomSheetDialog bsd = new BottomSheetDialog(quizpage.this);
        View bsv = LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_quiz_result,(LinearLayout)findViewById(R.id.idLLScore));
        TextView scoreTV = bsv.findViewById(R.id.scoreTV);
        TextView motivator = bsv.findViewById(R.id.motivationTV);
        Button backBTN = bsv.findViewById(R.id.backButton);
        pts = score*10;
        scoreTV.setText("Your Score is \n"+score+"/"+(countMax+1)+"\n Your PTS is "+pts);
        motivator.setText("This is a good motivator");
        backBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(quizpage.this, Mainpage_Teacher.class);
                startActivity(intent);
                bsd.dismiss();
            }
        });
        bsd.setCancelable(false);
        bsd.setContentView(bsv);
        bsd.show();
    }
}