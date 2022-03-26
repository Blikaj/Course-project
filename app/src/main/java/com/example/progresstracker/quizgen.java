package com.example.progresstracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class quizgen extends AppCompatActivity {

    TextView quizname, qnum, inp_quest, inp_answ, tf_quest, mlt_quest, mlt_answ1, mlt_answ2, mlt_answ3, mlt_answ4, qtype_inp;
    Spinner spinner_num, tf_spin;
    Button btn_next, btn_fin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizgen);
        btn_next = findViewById(R.id.btn_next);
        btn_fin = findViewById(R.id.btn_fin);
        quizname = findViewById(R.id.quizname);
        qnum = findViewById(R.id.qnum);
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


    }

}