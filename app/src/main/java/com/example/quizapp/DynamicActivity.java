/*package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.quizapp.model.Question;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DynamicActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private List<Question> questionList;
    private int incr = 0;
    private int score = 0;
    private TextView title;
    private Button btnNext;

    private void checkAnswer(int index, List<Question> qst) {
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

            Question question = qst.get(index);
            if (selectedIndex == question.getOptionCorrect()) {
                score += 10;
            }
        } else {
            Toast.makeText(this, "Please select an answer!", Toast.LENGTH_SHORT).show();
            // Prevent advancing to next question if nothing is selected
            incr--; // Undo the increment that will happen in onClick
        }
    }

    private void fetchQuestions() {
        mDatabase.child("questions").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                questionList.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Question question = snapshot.getValue(Question.class);
                    if (question != null) {
                        questionList.add(question);
                        Log.d("Firebase", "Added question: " + question.getQst());
                    }
                }

                // Debugging log
                Log.d("Firebase", "Total questions loaded: " + questionList.size());

                // Now that we have the data, load the first question
                if (questionList.size() > 0) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            title.setText("Question 1");
                            loadQuestion(0, questionList);
                            // Reset counters in case this is a refresh
                            incr = 0;
                            score = 0;
                            Toast.makeText(DynamicActivity.this, "Questions loaded: " + questionList.size(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(DynamicActivity.this, "No questions available", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("Firebase", "Error fetching questions: " + databaseError.getMessage());
                Toast.makeText(DynamicActivity.this, "Failed to load questions", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadQuestion(int index, List<Question> questionList) {
        Question question = questionList.get(index);
        TextView questionTextView = findViewById(R.id.qst);
        questionTextView.setText(question.getQst());

        List<String> options = question.getOptions();
        RadioGroup optionsGroup = findViewById(R.id.idRadioGroup);
        optionsGroup.clearCheck();
        RadioButton[] optionButtons = {
                findViewById(R.id.idQcm1),
                findViewById(R.id.idQcm2),
                findViewById(R.id.idQcm3),
                findViewById(R.id.idQcm4)
        };

        for (int i = 0; i < optionButtons.length && i < options.size(); i++) {
            optionButtons[i].setText(options.get(i));
        }

        ImageView questionImage = findViewById(R.id.questionImg);
        if (question.getImage() != null && !question.getImage().isEmpty()) {
            Glide.with(this)
                    .load(question.getImage())
                    .into(questionImage);
            questionImage.setVisibility(View.VISIBLE);
        } else {
            questionImage.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dynamic);

        // Initialize Firebase with logging
        mDatabase = FirebaseDatabase.getInstance().getReference();
        Log.d("Firebase", "Database reference initialized: " + mDatabase.toString());

        // Rest of your code...

        // Test Firebase connection
        DatabaseReference connectedRef = FirebaseDatabase.getInstance().getReference(".info/connected");
        connectedRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean connected = snapshot.getValue(Boolean.class);
                Log.d("Firebase", "Connection status: " + (connected ? "Connected" : "Disconnected"));
                Toast.makeText(DynamicActivity.this,
                        "Firebase: " + (connected ? "Connected" : "Disconnected"),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Firebase", "Connection check cancelled: " + error.getMessage());
            }
        });

        // Initialize UI elements
        title = findViewById(R.id.titleText);
        title.setText("Question 1");
        questionList = new ArrayList<>();

        btnNext = findViewById(R.id.btnNext);
        // Disable button until questions are loaded


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (incr < questionList.size()) {
                    checkAnswer(incr, questionList);
                    incr++;

                    if (incr < questionList.size()) {
                        title.setText("Question " + (incr + 1));
                        loadQuestion(incr, questionList);
                    } else {
                        // All questions finished, show score
                        Intent i1 = new Intent(DynamicActivity.this, score.class);
                        i1.putExtra("score", (score * 100) / (questionList.size()*10));
                        startActivity(i1);
                        finish(); // Close this activity
                    }
                }
            }
        });

        // Fetch questions from Firebase
        fetchQuestions();
    }
}*/



package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.quizapp.model.Question;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DynamicActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private List<Question> questionList;
    private int incr = 0;
    private int score = 0;
    private TextView title;
    private Button btnNext;
    private String categoryKey;

    private void checkAnswer(int index, List<Question> qst) {
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

            Question question = qst.get(index);
            if (selectedIndex == question.getOptionCorrect()) {
                score += 10;
            }
        } else {
            Toast.makeText(this, "Veuillez sélectionner une réponse !", Toast.LENGTH_SHORT).show();
            // Prevent advancing to next question if nothing is selected
            incr--; // Undo the increment that will happen in onClick
        }
    }

    private void fetchQuestions() {
        // Get the category from intent
        Intent intent = getIntent();
        categoryKey = intent.getStringExtra("CATEGORY_KEY");
        if (categoryKey == null) {
            categoryKey = "culture_generale"; // Default category
        }

        Log.d("Firebase", "Fetching questions for category: " + categoryKey);

        // Reference to the questions for the specific category
        mDatabase.child("quiz_categories").child(categoryKey).child("questions")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        questionList.clear();

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            try {
                                Question question = new Question();
                                question.setQst(snapshot.child("qst").getValue(String.class));

                                // Handle options as List
                                List<String> options = new ArrayList<>();
                                for (DataSnapshot optionSnapshot : snapshot.child("options").getChildren()) {
                                    options.add(optionSnapshot.getValue(String.class));
                                }
                                question.setOptions(options);

                                // Get option correct and image
                                question.setOptionCorrect(snapshot.child("optionCorrect").getValue(Integer.class));
                                question.setImage(snapshot.child("image").getValue(String.class));

                                questionList.add(question);
                                Log.d("Firebase", "Added question: " + question.getQst());
                            } catch (Exception e) {
                                Log.e("Firebase", "Error parsing question: " + e.getMessage());
                            }
                        }

                        // Debugging log
                        Log.d("Firebase", "Total questions loaded: " + questionList.size());

                        // Now that we have the data, load the first question
                        if (questionList.size() > 0) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    title.setText("Question 1");
                                    loadQuestion(0, questionList);
                                    // Reset counters in case this is a refresh
                                    incr = 0;
                                    score = 0;
                                    Toast.makeText(DynamicActivity.this, "Questions chargées: " + questionList.size(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else {
                            Toast.makeText(DynamicActivity.this, "Aucune question disponible", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.e("Firebase", "Error fetching questions: " + databaseError.getMessage());
                        Toast.makeText(DynamicActivity.this, "Échec du chargement des questions", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void loadQuestion(int index, List<Question> questionList) {
        Question question = questionList.get(index);
        TextView questionTextView = findViewById(R.id.qst);
        questionTextView.setText(question.getQst());

        List<String> options = question.getOptions();
        RadioGroup optionsGroup = findViewById(R.id.idRadioGroup);
        optionsGroup.clearCheck();
        RadioButton[] optionButtons = {
                findViewById(R.id.idQcm1),
                findViewById(R.id.idQcm2),
                findViewById(R.id.idQcm3),
                findViewById(R.id.idQcm4)
        };

        for (int i = 0; i < optionButtons.length && i < options.size(); i++) {
            optionButtons[i].setText(options.get(i));
        }

        ImageView questionImage = findViewById(R.id.questionImg);
        if (question.getImage() != null && !question.getImage().isEmpty()) {
            Glide.with(this)
                    .load(question.getImage())
                    .into(questionImage);
            questionImage.setVisibility(View.VISIBLE);
        } else {
            questionImage.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dynamic);

        // Initialize Firebase with logging
        mDatabase = FirebaseDatabase.getInstance().getReference();
        Log.d("Firebase", "Database reference initialized: " + mDatabase.toString());

        //recuperer les donnees d'intent
        Intent ii1=getIntent();


        // Test Firebase connection
        DatabaseReference connectedRef = FirebaseDatabase.getInstance().getReference(".info/connected");
        connectedRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean connected = snapshot.getValue(Boolean.class);
                Log.d("Firebase", "Connection status: " + (connected ? "Connected" : "Disconnected"));
                Toast.makeText(DynamicActivity.this,
                        "Firebase: " + (connected ? "Connecté" : "Déconnecté"),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Firebase", "Connection check cancelled: " + error.getMessage());
            }
        });

        // Initialize UI elements
        title = findViewById(R.id.titleText);
        title.setText("Question 1");
        questionList = new ArrayList<>();

        btnNext = findViewById(R.id.btnNext);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (incr < questionList.size()) {
                    checkAnswer(incr, questionList);
                    incr++;

                    if (incr < questionList.size()) {
                        title.setText("Question " + (incr + 1));
                        loadQuestion(incr, questionList);
                    } else {
                        // All questions finished, show score
                        Intent i1 = new Intent(DynamicActivity.this, score.class);
                        i1.putExtra("score", (score * 100) / (questionList.size()*10));
                        i1.putExtra("category", categoryKey); // Pass category to score screen
                        i1.putExtra("login",ii1.getStringExtra("login"));
                        startActivity(i1);
                        finish(); // Close this activity
                    }
                }
            }
        });

        // Fetch questions from Firebase
        fetchQuestions();
    }
}