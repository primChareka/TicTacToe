package com.teetoh.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public static final String PLAYER1 = "com.example.myfirstapp.PLAYER1";
    public static final String PLAYER2 = "com.example.myfirstapp.PLAYER2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Called when the user taps the Send button */
    public void sendNames(View view) {
        //Intent constructor parameters are the Context, and the class of the app component the system should deliver the Intent too
        Intent intent = new Intent(this, GameScreenActivity.class);


        EditText editText = findViewById(R.id.player1);
        String name1 = editText.getText().toString();
        intent.putExtra(PLAYER1, name1);

        editText = findViewById(R.id.player2);
        String name2 = editText.getText().toString();
        intent.putExtra(PLAYER2, name2);

        startActivity(intent);
    }
}
