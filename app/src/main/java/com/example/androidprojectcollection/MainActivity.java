package com.example.androidprojectcollection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnLayoutExercise, btnOpenButtonExercise, btnOpenCalculator, btnOpenMidterm, btnOpenMatch3, btnOpenPassingIntents, btnOpenFragments,btnOpenMenus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLayoutExercise = findViewById(R.id.btnLayoutExercise);
        btnLayoutExercise.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) {startActivity(new Intent(MainActivity.this, LayoutExercise.class));}});

        btnOpenButtonExercise = findViewById(R.id.btnOpenButtonExercise);
        btnOpenButtonExercise.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) {startActivity(new Intent(MainActivity.this, ButtonExercise.class));}});

        btnOpenCalculator = findViewById(R.id.btnOpenCalculator);
        btnOpenCalculator.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) {startActivity(new Intent(MainActivity.this, Calculator.class));}});

        btnOpenMidterm = findViewById(R.id.btnOpenMidterm);
        btnOpenMidterm.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) {startActivity(new Intent(MainActivity.this, Midterm.class));}});

        btnOpenMatch3 = findViewById(R.id.btnOpenMatch3);
        btnOpenMatch3.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) {startActivity(new Intent(MainActivity.this, Match3.class));}});

        btnOpenPassingIntents = findViewById(R.id.btnOpenPassingIntents);
        btnOpenPassingIntents.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) {startActivity(new Intent(MainActivity.this, PassingIntentsExercise.class));}});

        btnOpenMenus = findViewById(R.id.btnOpenMenus);
        btnOpenMenus.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) {startActivity(new Intent(MainActivity.this, MenuExercise.class));}});

    }

}