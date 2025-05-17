package com.example.quizapp.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.quizapp.model.Historique;

import java.util.List;

@Dao
public interface HistoriqueDao {

    @Insert
    void insert(Historique historique);

    @Query("SELECT * FROM historique WHERE userId = :userId ORDER BY id DESC")
    List<Historique> getHistoriqueForUser(String userId);



}
