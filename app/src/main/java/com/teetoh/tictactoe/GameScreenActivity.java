package com.teetoh.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class GameScreenActivity extends AppCompatActivity {

    String player1 = null;
    String player2 = null;
    int player1Score = 0;
    int player2Score = 0;
    private char current_player = 0;
    private int base_id = 0;
    private Character[] gridState = new Character[9];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        Intent intent = getIntent();
        player1 = intent.getStringExtra(MainActivity.PLAYER1);
        player2 = intent.getStringExtra(MainActivity.PLAYER2);

        // Capture the layout's TextView and set the string as its text
        current_player = 'X';
        TextView textView = findViewById(R.id.currentPlayer);
        textView.setText("It's " + player1 + "'s turn");

        TextView player1ScoreView = findViewById(R.id.player1Score);
        player1ScoreView.setText(player1+" "+player1Score);

        TextView player2ScoreView = findViewById(R.id.player2Score);
        player2ScoreView.setText(player2+" "+player2Score);

        base_id = getResources().getIdentifier("button_00", "id", getPackageName());

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gridState[i * 3 + j] = 0;
            }
        }
    }


    public void setSquare(View view) {
        int id_abs = view.getId();
        int id_rel = id_abs - base_id;

        //Make sure the square is empty and has not already been assigned a value
            //Add Move To GridState Array
            gridState[id_rel] = current_player;

            //Display On Activity Screen
            Button button = findViewById(id_abs);
            button.setText(String.valueOf(current_player));
            button.setClickable(false);

            //See if move resulted in 3-in-a-row
            if (winner(id_rel)) {
                TextView textView = findViewById(R.id.currentPlayer);
                Button restartButton = findViewById(R.id.restart);
                restartButton.setText("Play Again");
                if (current_player == 'X') {
                    textView.setText(player1 + " Won !");
                    player1Score++;

                } else {
                    textView.setText(player2 + " Won !");
                    player2Score++;
                }
                updateScores();
                deactivateButtons();
            } else {
                changePlayer();
            }
    }

    public void updateScores(){
        TextView player1ScoreView = findViewById(R.id.player1Score);
        player1ScoreView.setText(player1+" "+player1Score);

        TextView player2ScoreView = findViewById(R.id.player2Score);
        player2ScoreView.setText(player2+" "+player2Score);
    }

    public void restartGame(View view){
        TextView textView = findViewById(R.id.currentPlayer);
        Button restartButton = findViewById(R.id.restart);
        restartButton.setText("Restart Current Game");
        changePlayer();
        if (current_player == 'X') {
            textView.setText("It's " + player1 + "'s turn");
        } else {
            textView.setText("It's " + player2 + "'s turn");
        }

        for(int i=0;i<9;i++) {
            Button button = findViewById(base_id+i);
            button.setText("");
            button.setClickable(true);
            gridState[i]=0;
        }
    }

    public void resetMatch(View view){
        TextView textView = findViewById(R.id.currentPlayer);
        textView.setText("It's " + player1 + "'s turn");

        current_player = 'X';
        for(int i=0;i<9;i++) {
            Button button = findViewById(base_id+i);
            button.setText("");
            button.setClickable(true);
            gridState[i]=0;
        }

        player1Score = 0;
        player2Score = 0;

        updateScores();
    }

    public boolean winner(int id) {
        if (checkRow(id / 3) || checkColumn(id % 3)) {
            return true;
        }

        //CheckMainDiagonal will not be called if button is not on main-diagonal
        if ((id == 0 || id == 4 || id == 8) && checkMainDiagonal()) {
            return true;
        }

        //CheckAntiDiagonal will not be called if button is not on anti-diagonal
        if ((id == 2 || id == 4 || id == 6) && checkAntiDiagonal()) {
            return true;
        }

        return false;
    }

    /*Alternates current player and updates name on screen*/
    public void changePlayer() {
        TextView textView = findViewById(R.id.currentPlayer);
        current_player = current_player == 'X' ? 'O' : 'X';
        if (current_player == 'X') {
            textView.setText("It's " + player1 + "'s turn");
        } else {
            textView.setText("It's " + player2 + "'s turn");
        }
    }

    public void deactivateButtons() {
        for(int i=0;i<9;i++) {
            Button button = findViewById(base_id+i);
            button.setClickable(false);
        }
    }

    /*Checks to see if the three entries in the provided row match*/
    public boolean checkRow(int row) {
        return gridState[row * 3] == current_player && gridState[row * 3 + 1] == current_player && gridState[row * 3 + 2] == current_player;
    }

    /*Checks to see if the three entries in the provided column match*/
    public boolean checkColumn(int col) {
        return gridState[col] == current_player && gridState[col + 3] == current_player && gridState[col + 6] == current_player;
    }

    /*Main diagonal is top left to bottom right*/
    public boolean checkMainDiagonal() {
        return gridState[0] == current_player && gridState[4] == current_player && gridState[8] == current_player;
    }

    /*Anti diagonal is top right to bottom left*/
    public boolean checkAntiDiagonal() {
        return gridState[2] == current_player && gridState[4] == current_player && gridState[6] == current_player;
    }
}
