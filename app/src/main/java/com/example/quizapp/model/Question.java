package com.example.quizapp.model;

import java.util.List;

public class Question {
    private String qst;
    private List<String> options;
    private int optionCorrect;
    private String image;

    // Required empty constructor for Firebase
    public Question() {
    }

    public Question(String qst, List<String> options, int optionCorrect, String image) {
        this.qst = qst;
        this.options = options;
        this.optionCorrect = optionCorrect;
        this.image = image;
    }

    // Getters and setters
    public String getQst() {
        return qst;
    }

    public void setQst(String qst) {
        this.qst = qst;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public int getOptionCorrect() {
        return optionCorrect;
    }

    public void setOptionCorrect(int optionCorrect) {
        this.optionCorrect = optionCorrect;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}