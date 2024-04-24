package com.example.androidprojectcollection;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class MenuExercise extends AppCompatActivity {
    private static final int[] COLORS = {Color.BLACK, Color.RED, Color.GREEN,Color.BLUE,Color.YELLOW,Color.MAGENTA,Color.GRAY};
    private static final int COLORSCOUNT = COLORS.length;
    private static int defaultNumber, defaultColor;
    private static float defaultSize, defaultPosX, defaultPosY;
    private static Drawable defaultBackground;
    Button btnChanger;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_exercise);

        btnChanger = findViewById(R.id.btnTransformingButton);
        try { defaultNumber = Integer.getInteger(btnChanger.getText().toString());
        } catch (Exception e) { defaultNumber = 0;}
        defaultBackground = btnChanger.getBackground();
        defaultColor = Color.parseColor("#6200EE");
        btnChanger.setBackgroundColor(defaultColor);
        defaultSize = (float)((btnChanger.getScaleX() + btnChanger.getScaleY())/2.0);
        defaultPosX = btnChanger.getTranslationX();
        defaultPosY = btnChanger.getTranslationY();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.choice_menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        Button btn = btnChanger;
        if(item.getItemId()==R.id.mItemChange) {
            Toast.makeText(this, "Edit Object Item is clicked", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId()==R.id.mItemReset) {
            Toast.makeText(this, "Reset Object Item is clicked", Toast.LENGTH_SHORT).show();
            btn.setText(""+defaultNumber);
            btn.setScaleX(defaultSize);
            btn.setScaleY(defaultSize);
            btn.setBackground(defaultBackground);
            btn.setBackgroundColor(defaultColor);
            btn.setTranslationX(defaultPosX);
            btn.setTranslationY(defaultPosY);
            btn.setRotation(0f);
        } else if (item.getItemId()==R.id.mItemColor) {
            Toast.makeText(this, "Changed Button Color", Toast.LENGTH_SHORT).show();
            btn.setBackgroundColor(COLORS[((int)(Math.random()*1000))%COLORSCOUNT]);
        } else if (item.getItemId()==R.id.mItemNumber) {
            Toast.makeText(this, "Changed Button Number", Toast.LENGTH_SHORT).show();
            btn.setText(""+(int)((Math.random()*1000)%100));
        } else if (item.getItemId()==R.id.mItemSize) {
            Toast.makeText(this, "Changed Button Size", Toast.LENGTH_SHORT).show();
            float scale = (float)(Math.random()*1.5) + 0.5f;
            btn.setScaleX(scale);
            btn.setScaleY(scale);
        } else if (item.getItemId()==R.id.mItemPosition) {
            Toast.makeText(this, "Changed Button Position", Toast.LENGTH_SHORT).show();
            float transX, transY;
            transX = (float)(Math.random()*1000) - 500f;
            transY = (float)(Math.random()*1000) - 500f;
            btn.setTranslationX(transX);
            btn.setTranslationY(transY);
        } else if (item.getItemId()==R.id.mItemRotate) {
            Toast.makeText(this, "Changed Button Rotation", Toast.LENGTH_SHORT).show();
            btn.setRotation((float)((Math.random()*1009)%360));
        } else if (item.getItemId()==R.id.mItemExit)
            finish();
        return true;
    }
}