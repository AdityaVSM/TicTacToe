package com.example.connect3game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //red = 0, yellow = 1
    int activePlayer = 0;
    int [] gamestate = {2,2,2,2,2,2,2,2,2};
    int[][] winningPos = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
    boolean active = true;

    public void dropIn(View view) {

        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gamestate[tappedCounter] == 2 && active) {
            gamestate[tappedCounter] = activePlayer;
            counter.setTranslationY(-1500);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.red);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1500).rotation(3600).setDuration(500);
            for (int[] pos : winningPos) {
                if (gamestate[pos[0]] == gamestate[pos[1]] && gamestate[pos[1]] == gamestate[pos[2]] && gamestate[pos[0]] != 2) {
                    active  = false;
                    String winner = "";
                    if (activePlayer == 1)
                        winner = "Red has won";
                    else
                        winner = "Yellow has won";

                    Button playAgain = (Button)findViewById(R.id.playAgainButton);
                    TextView winnerText = (TextView)findViewById(R.id.winnerTextView);
                    winnerText.setText(winner);
                    playAgain.setVisibility(View.VISIBLE);
                    winnerText.setVisibility(View.VISIBLE);
                }
            }
        }
    }
    public void restart(View view){
        Button playAgain = (Button)findViewById(R.id.playAgainButton);
        TextView winnerText = (TextView)findViewById(R.id.winnerTextView);
        playAgain.setVisibility(View.INVISIBLE);
        winnerText.setVisibility(View.INVISIBLE);
        GridLayout grid = (GridLayout)findViewById(R.id.gridLayout);

        for(int i=0; i<grid.getChildCount(); i++) {
            ImageView counter = (ImageView) grid.getChildAt(i);
            counter.setImageDrawable(null);
        }

        for(int i= 0;i<gamestate.length;i++)
            gamestate[i] = 2;
        activePlayer = 0;
        active = true;
        
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}