package com.example.quizapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class Top5 extends AppCompatActivity {

    private ListView listView;
    private ArrayList<String> leaderboardList = new ArrayList<>();
    private DatabaseReference dbRef;

    private static class PlayerAvg {
        String email;
        double average;

        PlayerAvg(String email, double average) {
            this.email = email;
            this.average = average;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top5);

        listView = findViewById(R.id.listView);
        dbRef = FirebaseDatabase.getInstance().getReference("scores");

        loadLeaderboard();
    }

    private void loadLeaderboard() {
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<PlayerAvg> playerList = new ArrayList<>();

                for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                    String email = userSnapshot.getKey().replace("_", ".");
                    DataSnapshot scoresSnapshot = userSnapshot.child("scores");

                    int total = 0;
                    int count = 0;
                    for (DataSnapshot scoreSnap : scoresSnapshot.getChildren()) {
                        Integer score = scoreSnap.getValue(Integer.class);
                        if (score != null) {
                            total += score;
                            count++;
                        }
                    }

                    if (count > 0) {
                        double avg = (double) total / count;
                        playerList.add(new PlayerAvg(email, avg));
                    }
                }

                // Trier par moyenne décroissante
                Collections.sort(playerList, (p1, p2) -> Double.compare(p2.average, p1.average));

                leaderboardList.clear();
                for (int i = 0; i < Math.min(3, playerList.size()); i++) {
                    PlayerAvg p = playerList.get(i);
                    leaderboardList.add((i + 1) + " - " + p.email + " : " + String.format("%.1f", p.average) + " pts");
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<>(Top5.this, android.R.layout.simple_list_item_1, leaderboardList);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle errors
            }
        });
    }
}