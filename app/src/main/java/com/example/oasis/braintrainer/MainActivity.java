package com.example.oasis.braintrainer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // initiate all the global variables

    Button goButton;
    int locationOfCorrectAnswer;
    TextView resultTextView;
    TextView scoreTextView;
    TextView timerTextView;
    TextView sumText;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgainButton;
    ConstraintLayout gameLayout;
    // random instance
    Random rand = new Random();

    // arraylist to hold possible answers
    ArrayList<Integer> answers = new ArrayList<Integer>();

    // variable to hold the user's score
    int score = 0;
    // number of questions asked to the user
    int numberOfQuestions = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goButton = findViewById(R.id.goButton);
        goButton.setVisibility(View.VISIBLE);

        // answer buttons
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);

        //play again button
        playAgainButton = findViewById(R.id.playAgain);
        gameLayout = findViewById(R.id.gameLayout);
        gameLayout.setVisibility(View.INVISIBLE);

        sumText = findViewById(R.id.SumText);
        resultTextView = findViewById(R.id.resultTextView);
        scoreTextView = findViewById(R.id.marksText);
        timerTextView = findViewById(R.id.timerText);

        newQuestion();




    }


    //method to reset the game
    public void playAgain(View view) {
        score = 0;
        numberOfQuestions = 0;
        // reset the text to 30s
        timerTextView.setText("30s");
        // reset the text view
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));


        newQuestion();
        button0.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        playAgainButton.setVisibility(View.INVISIBLE);
        resultTextView.setText("");

        // start countdown timer

        new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {

                // set the result text to done/finish the session
                resultTextView.setText("Done!!!!");
                // show the play again button
                playAgainButton.setVisibility(View.VISIBLE);

                // set grid unclickable

                button0.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);

            }
        }.start();

    }


    public void startGame(View view) {
        // method to launch the game
        goButton.setVisibility(View.INVISIBLE);
        // the method expects a view that's why the timer text is passed in
        playAgain(timerTextView);
        //show game layout
        gameLayout.setVisibility(View.VISIBLE);
    }

    // this method is returns a new question once it is called
    public void newQuestion() {

        // Numbers to add
        int firstNumber = rand.nextInt(21);
        int secondNumber = rand.nextInt(21);

        sumText.setText(Integer.toString(firstNumber) + " + " + Integer.toString(secondNumber));

        // index to contain the answer

        locationOfCorrectAnswer = rand.nextInt(4);
        // clean the list before adding new data
        answers.clear();
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

    // method to choose the answer by clicking on one of the buttons
    // it also verifies that the answer with the correct one by comparing
    // the index of the correct answer and the button's tag

    public void chooseAnswer(View view) {

        if (Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())) {
            resultTextView.setText("Correct");

            // add the score
            score++;

        } else {
            resultTextView.setText("Wrong :(");
        }
        // always increase the number of questions
        numberOfQuestions++;
        // update the score text with score/number of questions

        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));

        newQuestion();
    }
}
