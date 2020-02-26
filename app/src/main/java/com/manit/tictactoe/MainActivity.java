package com.manit.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // 0:cross, 1:tick, -1: empty
    int[] gameState={-1,-1,-1,-1,-1,-1,-1,-1,-1};
    int [][] winningPositions={{0,1,2},{3,4,5},{6,7,8},{1,4,7},{0,3,6},{2,5,8},{2,4,6},{0,4,8}};
    int activePlayer=0;
    boolean gameActive=true;
    public void dropIn(View view){
        if(gameActive) {
            ImageView counter = (ImageView) view;
            counter.setTranslationY(-1500);

            int tappedTag = Integer.parseInt(counter.getTag().toString());
            if (gameState[tappedTag] == -1) {

                gameState[tappedTag] = activePlayer;

                if (activePlayer == 0) {
                    counter.setImageResource(R.drawable.cross);
                    activePlayer = 1;
                } else {
                    counter.setImageResource((R.drawable.tick));
                    activePlayer = 0;
                }

                counter.animate().translationYBy(1500).rotation(3600).setDuration(300);

                for (int[] winningPosition : winningPositions) {
                    if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != -1) {
                        String winner="";

                        TextView textView=(TextView) findViewById(R.id.textView);

                        if (activePlayer == 1) {
                            winner="Red";
                            textView.setTextColor(getResources().getColor(R.color.red));
                        }
                        else {
                            winner="Green";
                            textView.setTextColor(getResources().getColor(R.color.green));
                        }
                        winner+=" has Won!";

                        textView.setText(winner);
                        textView.setVisibility(View.VISIBLE);

                        Button playAgainButton=(Button)findViewById(R.id.playAgainButton);
                        playAgainButton.setVisibility(View.VISIBLE);

                    }
                }
            }
        }
    }
    public void playAgain(View view){
        TextView textView=(TextView) findViewById(R.id.textView);
        textView.setVisibility(View.INVISIBLE);
        Button playAgainButton=(Button)findViewById(R.id.playAgainButton);
        playAgainButton.setVisibility(View.INVISIBLE);
        GridLayout gridLayout=(GridLayout)findViewById(R.id.gridLayout);
        for(int i =0;i<gridLayout.getChildCount();i++){
            ImageView counter =(ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
        for(int j=0;j<9;j++){
            gameState[j]=-1;
        }
        activePlayer=0;
        gameActive=true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
