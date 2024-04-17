package com.example.androidprojectcollection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Match3 extends AppCompatActivity {
    public static final int[] COLORS = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW};
    public static int[][] tiles;
    public static int[] selected = new int[]{-1,-1};
    private LinearLayout mainContainer;
    private View canvasView;
    private TextView scoreDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match3);

        tiles = new int[5][5];
        randomizeTiles();
        mainContainer = findViewById(R.id.mainContainer);
        scoreDisplay = findViewById(R.id.scoreDisplay);
        canvasView = new CanvasView(this);
        mainContainer.addView(canvasView);
        canvasView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getActionMasked();
                double posX = (100.0 * motionEvent.getX() / canvasView.getWidth());
                double posY = (100.0 * motionEvent.getY() / canvasView.getHeight());
                int selX = (int)(posX / 20 );
                int selY = (int)(posY / 20 );
                scoreDisplay.setText("posX:"+ posX + "\tposY:"+posY+"\nselX:"+selX+"\tselY:"+selY);

        

                if(selected[0] == -1){
                    selected[0] = selX;
                    selected[1] = selY;
                } else {

                }

                canvasView.invalidate();
                return false;
            }
        });
    }
    private void randomizeTiles(){
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                tiles[i][j] = COLORS[((int)(Math.random()*100)) % 4];
            }
        }

    }
}
class CanvasView extends View{
    public CanvasView(Context context){
        super(context);
    }
    @Override
   protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        Paint p = new Paint();
        p.setColor(Color.GREEN);
        p.setStyle(Paint.Style.FILL);
       float tileW = (float)(getWidth()/5);
       float tileH = (float)(getHeight()/5);
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                p.setColor(Match3.tiles[i][j]);
                canvas.drawRect(tileW * j, tileH * i, tileW*j+tileW, tileH*i+tileH,p);
                switch(Match3.tiles[i][j]){
                    case Color.RED:
                        p.setColor(Color.rgb(255,200,200));
                        canvas.drawRect(tileW*j + tileW/3, tileH*i+ tileH/3, tileW*j+tileW - tileW/3 , tileH*i+tileH - tileH/3, p);
                        break;
                    case Color.GREEN:
                        p.setColor(Color.rgb(0,0,0));
//                        //canvas.drawCircle(tileW*j + tileW/2, tileH*i+ tileH/2, tileW/3, p);
//                        canvas.drawText("Hello", tileW * j, tileH * i, p);
//                        p.setFontFeatureSettings("fontSize: 30px");
                        break;
                    case Color.BLUE:
                        p.setColor(Color.CYAN);
                        canvas.drawCircle(tileW*j + tileW/2, tileH*i+ tileH/2, tileW/3, p);
                        break;
                    case Color.YELLOW:

                        break;
                }
            }
        }

   }
}