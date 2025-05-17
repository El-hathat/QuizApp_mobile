package com.example.quizapp;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

public class Menu extends AppCompatActivity {

    void setImg( ImageView stcimg){
        Glide.with(this)
                .load("https://images.unsplash.com/photo-1568515387631-8b650bbcdb90?q=80&w=640")
                .into(stcimg);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu);

        LinearLayout l2=findViewById(R.id.lytignore1);
        LinearLayout l3=findViewById(R.id.lytignore2);

        LinearLayout l1=findViewById(R.id.stlytvsbl);


        TextView dynamictxt=findViewById(R.id.dynamiclyt);
        TextView statctxt=findViewById(R.id.staticlyt);

        ImageView img1=findViewById(R.id.l1image);
        ImageView img2=findViewById(R.id.l2image);
        ImageView stcimg=findViewById(R.id.stimg);
        TextView sttxtimg=findViewById(R.id.stimgtxt);

        setImg(stcimg);

          Glide.with(this)
                .load("https://images.unsplash.com/photo-1629904853893-c2c8981a1dc5?w=640&auto=format")
                .into(img1);

          Glide.with(this)
                .load("https://images.unsplash.com/photo-1589998059171-988d887df646?ixlib=rb-4.0.3&q=85&fm=jpg&crop=entropy&cs=srgb&w=640")
                .into(img2);

Intent ii1=getIntent();

        statctxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                l2.setVisibility(GONE);
                l3.setVisibility(GONE);
                stcimg.setImageResource(R.drawable.img_3);
                sttxtimg.setText("Morocco Culture");
                dynamictxt.setBackgroundResource(R.drawable.roud_bg1);
                statctxt.setBackgroundResource(R.drawable.roud_bg2);
            }
        });



        dynamictxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                l2.setVisibility(VISIBLE);
                l3.setVisibility(VISIBLE);
                dynamictxt.setBackgroundResource(R.drawable.roud_bg2);
                statctxt.setBackgroundResource(R.drawable.roud_bg1);
                setImg(stcimg);
            }
        });



        //layout click

        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(l2.getVisibility()!=VISIBLE){
                    Intent i1 = new Intent(Menu.this, question1.class);
                   i1.putExtra("category", "Maroc_culture");
                   i1.putExtra("login", ii1.getStringExtra("login"));
                    startActivity(i1);
                    finish();
                }else{
                    Intent i1 = new Intent(Menu.this, DynamicActivity.class);
                     i1.putExtra("CATEGORY_KEY", "culture_generale");
                    i1.putExtra("login", ii1.getStringExtra("login"));
                    startActivity(i1);
                    finish();
                }

            }
        });


        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Intent i1 = new Intent(Menu.this, DynamicActivity.class);
                    i1.putExtra("CATEGORY_KEY", "sciences");
                i1.putExtra("login", ii1.getStringExtra("login"));
                    startActivity(i1);
                    finish();


            }
        });


        l3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i1 = new Intent(Menu.this, DynamicActivity.class);
                i1.putExtra("CATEGORY_KEY", "histoire");
                i1.putExtra("login", ii1.getStringExtra("login"));
                startActivity(i1);
                finish();


            }
        });


    }
}