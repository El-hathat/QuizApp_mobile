package com.example.quizapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.quizapp.DAO.AppDatabase;
import com.example.quizapp.model.HistoriqueAdapter;
import com.example.quizapp.model.Historique;


import java.util.List;

public class Historiques extends AppCompatActivity {

    RecyclerView recyclerView;
    HistoriqueAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_historique);
        recyclerView = findViewById(R.id.recyclerViewHistorique);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        TextView userName=findViewById(R.id.textView1);
        userName.setText(getIntent().getStringExtra("login"));

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "historique_db").allowMainThreadQueries().build();

        List<Historique> historiqueList = db.historiqueDao().getHistoriqueForUser(getIntent().getStringExtra("login"));

        HistoriqueAdapter adapter = new HistoriqueAdapter(historiqueList);
        recyclerView.setAdapter(adapter);





    }
}