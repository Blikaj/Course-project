package com.example.progresstracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class quizgen extends AppCompatActivity {

    TextView quizname, qnum, qnumimp, inp_quest, inp_answ, tf_quest, mlt_quest, mlt_answ1, mlt_answ2, mlt_answ3, mlt_answ4, qtype_inp;
    Spinner spinner_num, tf_spin;
    Button btn_next, btn_fin;
    ArrayList<QuizArray> quizArrayArrayList;
    Random random;
    int qcount, qcountmax;
    String tp;
    String qst;
    String answ;
    String op2;
    String op3;
    String op4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizgen);
        btn_next = findViewById(R.id.btn_next);
        btn_fin = findViewById(R.id.btn_fin);
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
        spinner_num = findViewById(R.id.spinner_num);
        tf_spin = findViewById(R.id.tf_spin);
        quizArrayArrayList = new ArrayList<>();
        random = new Random();
        qcount = 1;
        qcountmax = 0;


        String[] tf = {"True", "False"};

        String[] qnm = new String [10];
        for (int i = 0; i < 10; i++) {
            qnm[i] = String.valueOf(i+1);
        }

        ArrayAdapter<String> spArrayTF = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tf);
        ArrayAdapter<String> spArrayQNM = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, qnm);

        spArrayTF.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spArrayQNM.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        tf_spin.setAdapter(spArrayTF);
        spinner_num.setAdapter(spArrayQNM);

        qnumimp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                qcountmax = Integer.parseInt((qnumimp.getText().toString()));
                qnum.setText("Question " + qcount);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void onClickNext(View view) {
        if (qcount <= qcountmax) {
            qcount += 1;
            String tp = qtype_inp.getText().toString();

            switch (tp) {
                case "1":
                    qst = inp_quest.getText().toString();
                    answ = inp_answ.getText().toString();
                    break;
                case "2":
                    qst = tf_quest.getText().toString();
                    answ = tf_spin.getSelectedItem().toString();
                case "3":
                    qst = mlt_quest.getText().toString();
                    answ = mlt_answ1.getText().toString();
                    op2 = mlt_answ2.getText().toString();
                    op3 = mlt_answ3.getText().toString();
                    op4 = mlt_answ4.getText().toString();
                default:
                    throw new IllegalStateException("Unexpected value: " + tp);
            }
            getQuizQuestion(quizArrayArrayList);

            qnum.setText("Question " + qcount);
        }

        else {
            Toast.makeText(this, "Error: maximum number of questions reached!", Toast.LENGTH_SHORT).show();
        }
    }

    private void getQuizQuestion(ArrayList<QuizArray> quizArrayArrayList) {
        quizArrayArrayList.add(new QuizArray(' '+tp, ' '+qst, ' '+answ, ' '+ answ, ' '+op2, ' '+ op3, ' '+ op4 ));
    }

}