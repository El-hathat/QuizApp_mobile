package com.example.quizapp.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "historique")
public class Historique {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String userId; // ID de l'utilisateur
    public String quizName;
    public int score;
    public String date;

    public Historique(String userId, String quizName,int score) {
        this.userId = userId;
        this.quizName = quizName;
        this.score=score;
        this.date = new Date().toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

