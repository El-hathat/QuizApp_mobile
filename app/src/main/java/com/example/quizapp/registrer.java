package com.example.quizapp;

import static android.app.ProgressDialog.show;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;



public class registrer extends AppCompatActivity {

    EditText etName, etMail, etPassword, etPassword1;
    Button bRegister;
    FirebaseAuth myAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(getApplicationContext());
        setContentView(R.layout.activity_registrer);
        etMail=(EditText) findViewById(R.id.email);
        etName=(EditText) findViewById(R.id.name);
        etPassword=(EditText) findViewById(R.id.password1);
        etPassword1=(EditText)findViewById(R.id.password2);
        bRegister=(Button)findViewById(R.id.btnRegister);
        myAuth=FirebaseAuth.getInstance();
        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail=etMail.getText().toString();
                String password=etPassword.getText().toString();
                String password1=etPassword1.getText().toString();
                String name =etName.getText().toString();
                if(TextUtils.isEmpty(mail)){
                    Toast.makeText(getApplicationContext(),"Please fill in the required fields",
                    Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(name)){
                    Toast.makeText(getApplicationContext(),"Please fill in the required fields",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(getApplicationContext(),"Please fill in the required fields",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password1)){
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
                if(!password.equals(password1)){
                    Toast.makeText(getApplicationContext(),
                            "Please enter correct password",
                    Toast.LENGTH_SHORT).show();
                    return;
                }
                signUp(mail,password);
            }
        });
    }
    public void signUp(String mail, String password){
        myAuth.createUserWithEmailAndPassword(mail,password)
                .addOnCompleteListener(this, new
                        OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),
                            "Registration Successful! inscription réussi! التسجيل ناجح! ",
                    Toast.LENGTH_LONG).show();
                    startActivity(new
                            Intent(registrer.this,MainActivity.class));
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(),
                            "Sign up failed" + task.getException().getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}