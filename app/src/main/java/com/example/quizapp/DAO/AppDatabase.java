package com.example.quizapp.DAO;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.quizapp.model.Historique;

@Database(entities = {Historique.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract HistoriqueDao historiqueDao();
}

