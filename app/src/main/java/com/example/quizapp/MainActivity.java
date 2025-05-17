package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    Button btnLogin;
    EditText etMail,etPassword;
    TextView register;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mAuth = FirebaseAuth.getInstance();
        btnLogin=findViewById(R.id.loginBTN);
        register=findViewById(R.id.toRegister);
        etMail=findViewById(R.id.email);
        etPassword=findViewById(R.id.password);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1= new Intent(MainActivity.this,registrer.class);
                startActivity(i1);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail=etMail.getText().toString();
                String password=etPassword.getText().toString();
                if(TextUtils.isEmpty(mail)){
                    Toast.makeText(getApplicationContext(),"Please fill in the required fields",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                if(password.length()<6){
                    Toast.makeText(getApplicationContext(),
                            "Password must be at least 6 characters",

                            Toast.LENGTH_SHORT).show();
                    return;
                }
                signIn(mail,password);

            }
        });
    }


    public void signIn(String mail, String password){
        mAuth.signInWithEmailAndPassword(mail,password)
                .addOnCompleteListener(this, new
                        OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task)
                            {
                                if(task.isSuccessful()){
                                    Toast.makeText(getApplicationContext(),
                                            "Sign In Successful!",
                                            Toast.LENGTH_LONG).show();
                                    Intent i1=new Intent(MainActivity.this,Menu.class);
                                    i1.putExtra("login",mail);
                                    startActivity(i1);
                                    finish();
                                }else{
                                    Toast.makeText(getApplicationContext(),
                                            "Signin failed" + task.getException().getMessage(),
                                            Toast.LENGTH_LONG).show();
                                }
                            }
                        });
    }
}