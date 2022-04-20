package com.example.progresstracker;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class quizgen extends AppCompatActivity {

    TextView quizname, qnum, qnumimp, inp_quest, inp_answ, tf_quest, mlt_quest, mlt_answ1, mlt_answ2, mlt_answ3, mlt_answ4, qtype_inp;
    Spinner spinner_num, tf_spin;
    Button btn_next;
    ArrayList<QuizArray> quizArrayArrayList;
    Random random;
    int qcount, qcountmax;
    String tp, qst, answ, op2, op3, op4, name;
    View inp_group, tf_group, mcq_group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizgen);
        btn_next = findViewById(R.id.btn_next);
        quizname = findViewById(R.id.quizname);
        qnum = findViewById(R.id.qnumtext);
        qnumimp = findViewById(R.id.qnuminp);
        qtype_inp = findViewById(R.id.qtype_inp);
        inp_quest = findViewById(R.id.inp_quest);
        inp_answ = findViewById(R.id.inp_answ);
        tf_quest = findViewById(R.id.tf_quest);
        mlt_quest = findViewById(R.id.mlt_quest);
        mlt_answ1 = findViewById(R.id.mlt_answ1);
        mlt_answ2 = findViewById(R.id.mlt_answ2);
        mlt_answ3 = findViewById(R.id.mlt_answ3);
        mlt_answ4 = findViewById(R.id.mlt_answ4);
        //spinner_num = findViewById(R.id.spinner_num);
        tf_spin = findViewById(R.id.tf_spin);
        quizArrayArrayList = new ArrayList<>();
        random = new Random();
        inp_group = findViewById(R.id.inp_group);
        tf_group = findViewById(R.id.tf_group);
        mcq_group = findViewById(R.id.mcq_group);
        inp_group.setVisibility(View.INVISIBLE);
        tf_group.setVisibility(View.INVISIBLE);
        mcq_group.setVisibility(View.INVISIBLE);
        qnum.setVisibility(View.INVISIBLE);
        qcount = 1;
        qcountmax = 0;


        String[] tf = {"TRUE", "FALSE"};

        String[] qnm = new String [10];
        for (int i = 0; i < 10; i++) {
            qnm[i] = String.valueOf(i+1);
        }

        ArrayAdapter<String> spArrayTF = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tf);
        //ArrayAdapter<String> spArrayQNM = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, qnm);

        spArrayTF.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //spArrayQNM.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        tf_spin.setAdapter(spArrayTF);
        //spinner_num.setAdapter(spArrayQNM);

        qnumimp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (qnumimp.getText().toString().equals("") || qnumimp.getText().toString().equals("0")){
                    Toast.makeText(quizgen.this, "The number of questions must be more than 0!", Toast.LENGTH_SHORT).show();
                }
                else {
                    qcountmax = Integer.parseInt((qnumimp.getText().toString()));
                    qnum.setVisibility(View.VISIBLE);
                    qnum.setText("Question " + qcount);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        qtype_inp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                inp_group.setVisibility(View.INVISIBLE);
                tf_group.setVisibility(View.INVISIBLE);
                mcq_group.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (qtype_inp.length() > 0) {
                    switch ((qtype_inp.getText().toString())) {
                        case "1":
                            inp_group.setVisibility(View.VISIBLE);
                            tf_group.setVisibility(View.INVISIBLE);
                            mcq_group.setVisibility(View.INVISIBLE);
                            break;
                        case "2":
                            inp_group.setVisibility(View.INVISIBLE);
                            tf_group.setVisibility(View.VISIBLE);
                            mcq_group.setVisibility(View.INVISIBLE);
                            break;
                        case "3":
                            inp_group.setVisibility(View.INVISIBLE);
                            tf_group.setVisibility(View.INVISIBLE);
                            mcq_group.setVisibility(View.VISIBLE);
                            break;
                        default:
                            inp_group.setVisibility(View.INVISIBLE);
                            tf_group.setVisibility(View.INVISIBLE);
                            mcq_group.setVisibility(View.INVISIBLE);
                            break;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void onClickNext(View view) {
        if (qcount < qcountmax) {
            btn_next.setText("Next");
            qcount += 1;
            String tp = qtype_inp.getText().toString();
            switch (tp) {
                case "1":
                    qst = inp_quest.getText().toString();
                    answ = inp_answ.getText().toString();
                    inp_quest.setText("");
                    inp_answ.setText("");
                    qtype_inp.setText("");
                    break;
                case "2":
                    qst = tf_quest.getText().toString();
                    answ = tf_spin.getSelectedItem().toString();
                    tf_quest.setText("");
                    qtype_inp.setText("");
                    break;
                case "3":
                    qst = mlt_quest.getText().toString();
                    answ = mlt_answ1.getText().toString();
                    op2 = mlt_answ2.getText().toString();
                    op3 = mlt_answ3.getText().toString();
                    op4 = mlt_answ4.getText().toString();
                    mlt_quest.setText("");
                    mlt_answ1.setText("");
                    mlt_answ2.setText("");
                    mlt_answ3.setText("");
                    mlt_answ4.setText("");
                    qtype_inp.setText("");
                    break;
                default:
                    throw new IllegalStateException("Unexpected value:" + tp + ':');
            }
            getQuizQuestion(quizArrayArrayList,  tp, qst, answ, op2, op3, op4);

            qnum.setText("Question " + qcount);
            if (qcount == qcountmax){
                btn_next.setText("Finish");
            }
        }

        else {
            String tp = qtype_inp.getText().toString();
            switch (tp) {
                case "1":
                    qst = inp_quest.getText().toString();
                    answ = inp_answ.getText().toString();
                    inp_quest.setText("");
                    inp_answ.setText("");
                    break;
                case "2":
                    qst = tf_quest.getText().toString();
                    answ = tf_spin.getSelectedItem().toString();
                    tf_quest.setText("");
                    break;
                case "3":
                    qst = mlt_quest.getText().toString();
                    answ = mlt_answ1.getText().toString();
                    op2 = mlt_answ2.getText().toString();
                    op3 = mlt_answ3.getText().toString();
                    op4 = mlt_answ4.getText().toString();
                    mlt_quest.setText("");
                    mlt_answ1.setText("");
                    mlt_answ2.setText("");
                    mlt_answ3.setText("");
                    mlt_answ4.setText("");
                    break;
                default:
                    throw new IllegalStateException("Unexpected value:" + tp + ':');
            }
            getQuizQuestion(quizArrayArrayList, tp, qst, answ, op2, op3, op4);
            finish();
        }
    }

    public void finish() {
        if (qcount == qcountmax || qcount > qcountmax){
            name = quizname.getText().toString();
            generateQuiz();
        }
        else {
            Toast.makeText(this, "Error: not all the questions are filled!", Toast.LENGTH_SHORT).show();
        }
    }

    private void getQuizQuestion(ArrayList<QuizArray> quizArrayArrayList, String tp, String qst, String answ, String op2, String op3, String op4) {
        QuizArray quizquest = new QuizArray();
        quizquest.setType(tp);
        quizquest.setQuestion(qst);
        quizquest.setAnswer(answ);
        quizquest.setOption1(answ);
        quizquest.setOption2(op2);
        quizquest.setOption3(op3);
        quizquest.setOption4(op4);
        quizArrayArrayList.add(quizquest);
    }

    private void generateQuiz() {

        QuizBuild quizBuild = new QuizBuild();
        quizBuild.setName(name);
        quizBuild.setNumofquestions(qcountmax);
        ArrayList<QuizArray> finalQuizArray = new ArrayList<>();
        for (Integer i=0; i < quizArrayArrayList.size(); i++){
            finalQuizArray.add(quizArrayArrayList.get(i));
        }
        quizBuild.setQuizArray(finalQuizArray);
        DatabaseReference postRef = FirebaseDatabase.getInstance().getReference("quiz");
        Integer quizid = random.nextInt()*10 + qcountmax;
        postRef.child(String.valueOf(quizid)).setValue(quizBuild);
        Toast.makeText(this, "Quiz generated", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, Mainpage_Teacher.class);
        startActivity(intent);
    }


}