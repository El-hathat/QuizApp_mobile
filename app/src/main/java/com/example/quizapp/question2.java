package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class question2 extends AppCompatActivity {

    int score=0;

    private void checkAnswer(int index) {
        RadioGroup optionsGroup = findViewById(R.id.idRadioGroup);
        int selectedId = optionsGroup.getCheckedRadioButtonId();

        if (selectedId != -1) {
            RadioButton[] optionButtons = {
                    findViewById(R.id.idQcm1),
                    findViewById(R.id.idQcm2),
                    findViewById(R.id.idQcm3),
                    findViewById(R.id.idQcm4)
            };

            int selectedIndex = -1;
            for (int i = 0; i < optionButtons.length; i++) {
                if (optionButtons[i].getId() == selectedId) {
                    selectedIndex = i;
                    break;
                }
            }


            if (selectedIndex == index) {
                score += 10;
            }
        } else {
            Toast.makeText(this, "Veuillez sélectionner une réponse !", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_question2);
        Button next=findViewById(R.id.nextbtn);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i1 = new Intent(question2.this, question3.class);
                score=+i1.getIntExtra("score",0);
                checkAnswer(1);
                i1.putExtra("score", score);
                i1.putExtra("login",getIntent().getStringExtra("login"));
                i1.putExtra("login",getIntent().getStringExtra("login"));
                startActivity(i1);
                finish();

            }
        });
    }
}