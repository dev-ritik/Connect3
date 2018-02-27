package com.example.android.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int act = 0;
    String winner_animal = "FISH WINS!!";
    boolean gameActive = true;
    ImageView winnerimage;

    int[] gamestate = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] win = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    //0=cat,1=fish,2=neutral
    LinearLayout layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout = (LinearLayout) findViewById(R.id.playAgainLayout);
        winnerimage =(ImageView) findViewById(R.id.winnerimage);

    }

    public void playAgain(View view) {
        layout.setVisibility(View.INVISIBLE);
        act = 0;
        winner_animal = "FISH WINS!!";
        for (int i = 0; i < 9; i++) gamestate[i] = 2;
        GridLayout grid = (GridLayout) findViewById(R.id.grid);
        gameActive = true;
        for (int j = 0; j < 9; j++) {
            ((ImageView) grid.getChildAt(j)).setImageResource(0);
        }
    }

    public void dropIn(View view) {
        Log.i("contentyy","bbbib");
        ImageView counter = (ImageView) view;
        int tappedcounter = Integer.parseInt(counter.getTag().toString());
        if (gamestate[tappedcounter - 1] == 2 && gameActive) {
            gamestate[tappedcounter - 1] = act;
            counter.setTranslationY(-1200f);
            if (act == 0) {
                counter.setImageResource(R.drawable.cat);
                act = 1;
            } else {
                counter.setImageResource(R.drawable.mouse);
                act = 0;
            }
            counter.animate().translationYBy(1200f).rotation(720).setDuration(700);
            for (int[] win_animal : win) {
                if ((gamestate[win_animal[0]] == gamestate[win_animal[1]]) &&
                        gamestate[win_animal[1]] == gamestate[win_animal[2]] &&
                        gamestate[win_animal[0]] != 2) {
                    //someone has one
                    gameActive = false;
                    TextView winner = (TextView) findViewById(R.id.winner);
                    if (gamestate[win_animal[0]] == 0) {winner_animal = "CAT WINS!!";
                    winnerimage.setImageResource(R.drawable.cat);
                    }
                    else{winner_animal = "FISh WINS!!";
                        winnerimage.setImageResource(R.drawable.mouse);}
                    winner.setText(winner_animal);
                    layout.setVisibility(View.VISIBLE);
                } else {
                    boolean gameIsDraw = true;
                    for (int check : gamestate) {
                        if (check == 2) {
                            gameIsDraw = false;
                            break;
                        }
                    }
                    if (gameIsDraw) {
                        winnerimage.setImageResource(R.drawable.grid);

                        TextView winner = (TextView) findViewById(R.id.winner);
                        winner.setText("ITS A TIE :-(");
                        layout.setVisibility(View.VISIBLE);
                    }

                }
            }
        }
    }
}