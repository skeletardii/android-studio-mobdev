package com.example.androidprojectcollection;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Stack;

public class Calculator extends AppCompatActivity {
    private double result = 0.0;
    private String toCalc = "";
    int i=0;
    private String curr = "";

    TextView display;
    TextView display2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        display  = findViewById(R.id.txvDisplay);
        display2 = findViewById(R.id.txvDisplay2);
        Button[] btnNums = {
                findViewById(R.id.btnNum0),
                findViewById(R.id.btnNum1),
                findViewById(R.id.btnNum2),
                findViewById(R.id.btnNum3),
                findViewById(R.id.btnNum4),
                findViewById(R.id.btnNum5),
                findViewById(R.id.btnNum6),
                findViewById(R.id.btnNum7),
                findViewById(R.id.btnNum8),
                findViewById(R.id.btnNum9),
                findViewById(R.id.btnDot),
                findViewById(R.id.btnAdd),
                findViewById(R.id.btnSubtract),
                findViewById(R.id.btnMultiply),
                findViewById(R.id.btnDivide),
                findViewById(R.id.btnParLeft),
                findViewById(R.id.btnParRight)
        };
        for(Button btn : btnNums){
            btn.setOnClickListener(new View.OnClickListener() {
                private final char curra = btn.getText().charAt(0);
                @Override
                public void onClick(View view) {
                    String space = "";
                    if(!Character.isDigit(curra) && curra!='.')
                        space = "";
                    updateDisplay(space+curra+space);
                    updateSequential();
                }
            });
        }

        Button btnEquals = findViewById(R.id.btnEquals);
        btnEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    display.setText("");
                    display2.setText("= " + evaluate(toCalc,0));
                    display2.setTextColor(Color.BLACK);
                } catch (Exception e){
                    e.printStackTrace();
                    display2.setText("INVALID");
                    display2.setTextColor(Color.RED);
                }
                toCalc = "";
            }
        });
        Button btnClear = findViewById(R.id.btnClear);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toCalc = "";
                display.setText("");
                display2.setText("");
            }
        });
        Button btnErase = findViewById(R.id.btnErase);
        btnErase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuilder sb = new StringBuilder();
                sb.append(toCalc);
                sb.deleteCharAt(toCalc.length()-1);
                toCalc = sb.toString();
                display.setText(toCalc);
                updateSequential();
            }
        });
    }
    private void updateDisplay(String add){
        toCalc += add;
        display.setText(toCalc);
        try{
            double ass = evaluate(toCalc);
            System.out.println("assl" +ass);
        } catch (Exception e){}
    }
    private static double evaluate(String input){
        String[] starr =  input.split("((?<=[+-])|(?=[+-]))");
        Stack<String> stack = new Stack<>();
        starr = minusCheck(starr);
        for(int i=starr.length-1; i>=0; i--){
            //System.out.println(starr[i]);
            if(starr[i].matches("ass"))
                continue;
            if(!(starr[i].charAt(0)=='-' || starr[i].charAt(0)=='+')){
                Stack<String> mini = new Stack<>();
                String[] starr2 = starr[i].trim().split(" ");
                for (int j = starr2.length - 1; j >= 0; j--) {
                    //System.out.println("  "+starr2[j]);
                    mini.push(starr2[j]);
                }
                while (mini.size() > 1) {
                    double num1 = Double.parseDouble(mini.pop());
                    char ex = mini.pop().charAt(0);
                    double num2 = Double.parseDouble(mini.pop());
                    if (ex == '/')
                        num1 /= num2;
                    else
                        num1 *= num2;
                    mini.push("" + num1);
                }
                starr[i] = mini.pop();
            }
            stack.push(starr[i]);
        }
        while(stack.size()>1){
            double num1 = Double.parseDouble(stack.pop());
            char ex = stack.pop().charAt(0);
            double num2 = Double.parseDouble(stack.pop());
            if(ex=='-')
                num1-=num2;
            else
                num1+=num2;
            stack.push(""+num1);
        }
        return Double.parseDouble(stack.pop());
    }
    private double sequential = 0.0;
    private void updateSequential(){
        try {
            sequential = sequential(toCalc);
            display2.setText(""+sequential);
            display2.setTextColor(Color.BLACK);
        } catch (Exception e){
            display2.setTextColor(Color.RED);
        }
    }
    private static boolean isOp(String op){
        if(op.trim().length()==1){
            char opchar = op.trim().charAt(0);
            if(opchar=='/' || opchar=='+' || opchar=='-' || opchar=='*'){
                return true;
            }
        }
        return false;
    }
    private static boolean isOp(char op){
        return (op == '/' || op == '+' || op == '-' || op == '*');
    }
    private static String[] minusCheck(String[] starr){ // 5/-5
        for(int i=0; i<starr.length-1; i++){
            if(starr[i].trim().length()==1 && starr[i].trim().charAt(0)=='-' && starr[i-1]!=null && (isOp(starr[i-1]) || isOp(starr[i-1].trim().charAt(starr[i-1].trim().length()-1)))){
                starr[i+1] = ""+(0-Double.parseDouble(starr[i+1]));
                starr[i] = "ass";
            }
        }
        return starr;
    }
    private static double sequential(String input){
        Stack<String> stack = new Stack<>();
        String[] starr =  input.split("((?<=[/*+-])|(?=[/*+-]))");
        starr = minusCheck(starr);
        for(int i=starr.length-1; i>=0; i--){
            if(!starr[i].matches("ass"))
                stack.push(starr[i]);
        }
        while(stack.size()>1){
            double num1 = Double.parseDouble(stack.pop());
            char ex = stack.pop().charAt(0);
            double num2 = Double.parseDouble(stack.pop());
            switch(ex){
                case '+':
                    num1 += num2;break;
                case '-':
                    num1 -= num2;break;
                case '*':
                    num1 *= num2;break;
                case '/':
                    num1 /= num2;break;
            }
            stack.push(""+num1);
        }
        return Double.parseDouble(stack.pop());
    }
    private static double parsePar(String input){
        return 0.0;
    }
    private static double evaluate(String input, int mode){
        Stack<String> stack = new Stack<>();
        final String[] modeChars = {"+-","*/"};
        String[] starr =  input.trim().split("((?<=["+modeChars[mode]+"])|(?=["+modeChars[mode]+"]))");
        starr=minusCheck(starr);
        for(int i=0; i< starr.length-1; i++){
            System.out.println(starr[i]);
        }
        for(int i=starr.length-1; i>=0; i--){
            if(starr[i].matches("ass"))
                continue;

            if(     starr[i].length()>=1 &&
                    !(starr[i].charAt(0) == modeChars[mode].charAt(0) ||
                    starr[i].charAt(0) == modeChars[mode].charAt(1))
                    && mode!=1){
                starr[i] = ""+evaluate(starr[i].trim(),mode+1);
            }
            stack.push(starr[i]);
        }
        while(stack.size()>1){
            double num1 = Double.parseDouble(stack.pop());
            char ex = stack.pop().charAt(0);
            double num2 = Double.parseDouble(stack.pop());
            if(mode==0){
                if (ex == '-')
                    num1 -= num2;
                else
                    num1 += num2;
            } else if(mode == 1){
                if (ex == '/')
                    num1 /= num2;
                else
                    num1 *= num2;
            }
            stack.push(""+num1);
        }
        return Double.parseDouble(stack.pop());
    }
}