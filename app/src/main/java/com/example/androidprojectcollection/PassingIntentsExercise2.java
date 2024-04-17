package com.example.androidprojectcollection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PassingIntentsExercise2 extends AppCompatActivity {
    TextView output;
    Button btnReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passing_intents_exercise2);
        Intent i = this.getIntent();
        output = findViewById(R.id.formOutputText);
        String res = "STUDENT INFORMATION :\n\n";
        res += "First Name:\n\t  ";
        res += i.getStringExtra("fname");
        res += "\nLast Name:\n\t  ";
        res += i.getStringExtra("lname");
        res += "\nGender:\n\t  ";
        res += i.getStringExtra("gender");
        res += "\nBirth Date:\n\t  ";
        res += i.getStringExtra("birth");
        res += "\nPhone Number:\n\t  ";
        res += i.getStringExtra("phone");
        res += "\nEmail Address:\n\t  ";
        res += i.getStringExtra("email");
        res += "\nYear Level:\n\t  ";
        res += i.getStringExtra("year");
        res += "\nProgram:\n\t  ";
        res += i.getStringExtra("prog");
        res += "\nAdditional Information:\n";
        res += i.getStringExtra("info");
        output.setText(res);

        btnReturn = findViewById(R.id.formBtnReturn);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}