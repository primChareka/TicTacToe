package com.teetoh.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

public class GameScreenActivity extends AppCompatActivity {

    String player1 = null;
    String player2 = null;
    private char current_player = 0;
    private int base_id = 0;
    private HashMap<Integer, Character> gridState = new HashMap<Integer, Character>();

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
        textView.setText("Its " + player1 + "'s turn");


        TextView display = findViewById(R.id.display);
        String id_string = null;
        int id_int = 0;

        base_id = getResources().getIdentifier("button_00", "id", getPackageName());

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                id_string = "button_" + i + j;
                id_int = getResources().getIdentifier(id_string, "id", getPackageName());
                gridState.put(Integer.valueOf(id_int), null);
            }
        }
        display.setText(String.valueOf(base_id));
        //textView.setText(Integer.valueOf(id_int).toString());
        //setSquare();
    }


    public void setSquare(View view) {

        Integer id = view.getId();
        if (isValid(id)) {
            TextView display = findViewById(R.id.display);
            display.setText("valid");

            gridState.put(id, current_player);

            Button button = findViewById(id.intValue());
            button.setText(String.valueOf(current_player));
            changePlayer();
        } else {
            TextView display = findViewById(R.id.display);
            display.setText("false");
        }
        //String value = current_player==1?"X":"0";


    }

    public void winner() {

    }

    boolean isValid(Integer id) {
        if (gridState.get(id) == null) {
            return true;
        } else {
            return false;
        }
    }

    public void changePlayer() {
        TextView textView = findViewById(R.id.currentPlayer);
        current_player = current_player == 'X' ? 'O' : 'X';
        if (current_player == 'X') {
            textView.setText("Its " + player1 + "'s turn");
        } else {
            textView.setText("Its " + player2 + "'s turn");
        }
    }
}
