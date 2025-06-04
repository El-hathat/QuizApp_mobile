package com.example.quizapp;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import androidx.room.Room;

import com.example.quizapp.DAO.AppDatabase;
import com.example.quizapp.DAO.HistoriqueDao;
import com.example.quizapp.model.Historique;
import com.github.lzyzsd.circleprogress.DonutProgress;

public class score extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_score);

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "historique_db").allowMainThreadQueries().build(); // pour test rapide

        HistoriqueDao dao = db.historiqueDao();
        dao.insert(new Historique(getIntent().getStringExtra("login"), getIntent().getStringExtra("category"), getIntent().getIntExtra("score",0)));


        ImageView logout=findViewById(R.id.logout);
        ImageView retry=findViewById(R.id.tryagain);
        ImageView hist=findViewById(R.id.hist);
        ImageView top5=findViewById(R.id.top5);

logout.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent i1= new Intent(score.this,MainActivity.class);
        startActivity(i1);
        finish();
    }
});

        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1= new Intent(score.this,Menu.class);
                i1.putExtra("login",getIntent().getStringExtra("login"));
                startActivity(i1);

            }
        });


        hist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1= new Intent(score.this, Historiques.class);
                i1.putExtra("login",getIntent().getStringExtra("login"));
                startActivity(i1);
            }
        });



        top5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1= new Intent(score.this,Top5.class);
                startActivity(i1);

            }
        });






        Intent intent = getIntent();
        int score = intent.getIntExtra("score", 0);


        DonutProgress progress = findViewById(R.id.progressScore);

        ValueAnimator animator = ValueAnimator.ofInt(0, score);
        animator.setDuration(1000);
        animator.addUpdateListener(animation -> {
            int value = (int) animation.getAnimatedValue();
            progress.setProgress(value);
            progress.setText(value + "%");
        });
        animator.start();

    }



}