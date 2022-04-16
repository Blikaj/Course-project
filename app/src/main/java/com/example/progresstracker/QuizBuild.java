package com.example.progresstracker;

import java.util.ArrayList;

public class QuizBuild {

    private String name;
    private Integer numofquestions;
    private ArrayList<QuizArray> quizArray;

    public QuizBuild() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumofquestions() {
        return numofquestions;
    }

    public void setNumofquestions(Integer numofquestions) {
        this.numofquestions = numofquestions;
    }

    public ArrayList<QuizArray> getQuizArray() {
        return quizArray;
    }

    public void setQuizArray(ArrayList<QuizArray> quizArray) { this.quizArray = quizArray; }
}
