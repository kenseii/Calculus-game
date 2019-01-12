package com.example.oasis.braintrainer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // initiate all the global variables

    Button goButton;

    // arraylist to hold possible answers
    ArrayList<Integer> answers = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goButton = findViewById(R.id.goButton);

        // answer buttons
        Button button0 = findViewById(R.id.button0);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);

        TextView sumText = findViewById(R.id.SumText);

        // random instance
        Random rand = new Random();

        // Numbers to add
        int firstNumber = rand.nextInt(21);
        int secondNumber = rand.nextInt(21);

        sumText.setText(Integer.toString(firstNumber) + " + " + Integer.toString(secondNumber));

        // index to contain the answer

        int locationOfCorrectAnswer = rand.nextInt(4);

        // populate the answers list
        // add the correct answer to the list if the index is the same as the rand index to
        // hold the answer
        // else fill in fake answers 0-40
        // in the case that the fake answer maybe equal to the

        for (int i = 0; i < 4; i++) {
            if (locationOfCorrectAnswer == i) {
                answers.add(firstNumber + secondNumber);
            } else {
                int wrongAnswer = rand.nextInt(41);
                while (wrongAnswer == firstNumber + secondNumber) {
                    wrongAnswer = rand.nextInt(41);
                }
                answers.add(wrongAnswer);
            }
        }

        // set the buttons text values

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));




    }

    public void startGame(View view) {
        // method to launch the game
        goButton.setVisibility(View.INVISIBLE);
    }

    // method to choose the answer from the given one

    public void chooseAnswer(View view) {

    }
}
