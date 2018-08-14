package com.teetoh.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameScreenActivity extends AppCompatActivity {

    private int current_player;
    private int[][] gridState = new int[3][3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        Intent intent = getIntent();
        String player1 = intent.getStringExtra(MainActivity.PLAYER1);
        String player2 = intent.getStringExtra(MainActivity.PLAYER2);

        // Capture the layout's TextView and set the string as its text
        current_player=1;
        TextView textView = findViewById(R.id.currentPlayer);
        textView.setText("Its "+player1+"'s turn");

        setSquare();
    }


    public void setSquare(View view){

        if(8)
        String value = current_player==1?"X":"0";
        button.setHint(value);
    }
    public void winner(){

    }
}
