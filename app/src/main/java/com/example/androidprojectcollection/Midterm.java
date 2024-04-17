package com.example.androidprojectcollection;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Midterm extends AppCompatActivity {
    char[][] cells = {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
    boolean isPlayer1;
    TextView turnDisplay;
    View colorBackground;

    Button btnReset;
    Button btnExit;
    Button[][] btns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_midterm);

        turnDisplay = findViewById(R.id.turnDisplay);
        colorBackground = findViewById(R.id.colorBackground);
        btnReset = findViewById(R.id.btnReset);
        btnExit = findViewById(R.id.btnExit);
        btns = new Button[][]{
                {findViewById(R.id.btn11), findViewById(R.id.btn12), findViewById(R.id.btn13)},
                {findViewById(R.id.btn21), findViewById(R.id.btn22), findViewById(R.id.btn23)},
                {findViewById(R.id.btn31), findViewById(R.id.btn32), findViewById(R.id.btn33)},
        };


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int r = i, c = j;
                btns[i][j].setOnClickListener(new View.OnClickListener() {
                    final int row = r;
                    final int col = c;

                    @Override
                    public void onClick(View view) {
                        if (cells[row][col] == ' ') {
                            if (isPlayer1) {
                                cells[row][col] = 'O';
                                btns[row][col].setText("O");
                                turnDisplay.setText("Player X's turn");
                                colorBackground.setBackgroundColor(Color.RED);
                            } else {
                                cells[row][col] = 'X';
                                btns[row][col].setText("X");
                                turnDisplay.setText("Player O's turn");
                                colorBackground.setBackgroundColor(Color.BLUE);
                            }

                            isPlayer1 = !isPlayer1;
                            checkWin();
                        }
                    }
                });
            }
        }

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restart();
            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Exiting Game...", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        isPlayer1 = true;
        turnDisplay.setText("Player O's turn");
        colorBackground.setBackgroundColor(Color.BLUE);
    }

    void checkWin() {
        char mark = ' ';
        if (checkDiagonals() != ' ') mark = checkDiagonals();
        for (int i = 0; i < 3; i++) if (checkRow(i) != ' ') mark = checkRow(i);
        for (int i = 0; i < 3; i++) if (checkCol(i) != ' ') mark = checkCol(i);
        if (mark != ' ') {
            boolean p1win = (mark == 'O');
            if (p1win) {
                turnDisplay.setText("Player O wins !");
                Toast.makeText(getApplicationContext(), "Player 0 wins !", Toast.LENGTH_SHORT).show();
                colorBackground.setBackgroundColor(Color.BLUE);
            } else {
                turnDisplay.setText("Player X wins !");
                Toast.makeText(getApplicationContext(), "Player X wins !", Toast.LENGTH_SHORT).show();
                colorBackground.setBackgroundColor(Color.RED);
            }
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (cells[i][j] == ' ')
                        cells[i][j] = '\t';
                }
            }
        } else if (allMarked()) {
            turnDisplay.setText("Stalemate");
            Toast.makeText(getApplicationContext(), "Stalemate", Toast.LENGTH_SHORT).show();
            colorBackground.setBackgroundColor(Color.GRAY);
        }
    }

    char checkDiagonals() {
        char mark = cells[1][1];
        if (cells[0][0] == mark && cells[2][2] == mark)
            return mark;
        if (cells[0][2] == mark && cells[2][0] == mark)
            return mark;
        return ' ';
    }

    char checkRow(int row) {
        char mark = cells[row][0];
        if (cells[row][1] == mark && cells[row][2] == mark)
            return mark;
        return ' ';
    }

    char checkCol(int col) {
        char mark = cells[0][col];
        if (cells[1][col] == mark && cells[2][col] == mark)
            return mark;
        return ' ';
    }

    boolean allMarked() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (cells[i][j] == ' ')
                    return false;
            }
        }
        return true;
    }

    void restart() {
        isPlayer1 = true;
        turnDisplay.setText("Player O's turn");
        colorBackground.setBackgroundColor(Color.BLUE);
        Toast.makeText(getApplicationContext(), "Game has been restarted", Toast.LENGTH_SHORT).show();
        cells = new char[][]{{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                btns[i][j].setText(" ");
            }
        }
    }
}
