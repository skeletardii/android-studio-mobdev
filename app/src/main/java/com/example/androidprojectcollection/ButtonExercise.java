package com.example.androidprojectcollection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class ButtonExercise extends AppCompatActivity {
    private int getRandomColor(){
        int r = (int)(Math.random() * 255);
        int g = (int)(Math.random() * 255);
        int b = (int)(Math.random() * 255);
        return Color.argb(1,r,g,b);
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_exercise);

        Button btnClose = findViewById(R.id.btnClose);
        Button btnToast = findViewById(R.id.btnToast);
        Button btnChangeBG = findViewById(R.id.btnChangeBG);
        Button btnChangeButtonBG = findViewById(R.id.btnChangeButtonBG);
        Button btnDisappear = findViewById(R.id.btnDisappear);
        LinearLayout layout = findViewById(R.id.linLayoutButtonExercise);

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intents = new Intent(
                        ButtonExercise.this,
                        ButtonExerciseReturn.class
                );
                startActivity(intents);
            }
        });

        btnToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ButtonExercise.this,"This is a Toast ! ! !",Toast.LENGTH_SHORT).show();
            }
        });

        btnChangeBG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout.setBackgroundColor(Color.BLUE);
            }
        });

        btnChangeButtonBG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnChangeButtonBG.setBackgroundColor(getRandomColor());
            }
        });

        btnDisappear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnDisappear.setVisibility(View.INVISIBLE);
            }
        });
    }
}