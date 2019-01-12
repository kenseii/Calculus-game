package com.example.oasis.braintrainer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // initiate all the global variables

    Button goButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goButton = findViewById(R.id.goButton);
        TextView sumText = findViewById(R.id.SumText);

        // random instance
        Random rand = new Random();

        // Numbers to add
        int firstNumber = rand.nextInt(21);
        int secondNumber = rand.nextInt(21);

        sumText.setText(Integer.toString(firstNumber) + " + " + Integer.toString(secondNumber));


    }

    public void startGame(View view) {
        // method to launch the game
        goButton.setVisibility(View.INVISIBLE);
    }

    // method to choose the answer from the given one

    public void chooseAnswer(View view) {

    }
}
