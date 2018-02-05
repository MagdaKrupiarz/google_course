package com.example.android.volleyball;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private int scoreTeamA =0;
    private int scoreTeamB =0;
    private int timeoutTeamA =0;
    private int timeoutTeamB =0;
    private int wonSetsTeamA = 0;
    private int wonSetsTeamB = 0;
    private int setNumber = 1;
    private static final int SET_BALL = 25; //dodalem final
    private static final int MATCH_BALL = 15; // dodalem final
    Button timeoutButton;
    Button timeoutButtonB;
    Button scoreButton;
    Button scoreButtonB;
    Button wonSetsButton;
    Button wonSetsButtonB;
    private static final String MESSAGE = "PLAY BAAALL!!"; //pozmienialem zgodnie z konwencja



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView setView = (TextView) findViewById(R.id.win_message);
        setView.setText(MESSAGE);
        timeoutButton = (Button) findViewById(R.id.timeout_button);
        timeoutButton.setClickable(true);
        scoreButton = (Button) findViewById(R.id.score_button);
        scoreButton.setClickable(true);
        wonSetsButton = (Button) findViewById(R.id.won_sets_button);
        wonSetsButton.setClickable(true);
        timeoutButtonB = (Button) findViewById(R.id.timeout_buttonB);
        timeoutButtonB.setClickable(true);
        scoreButtonB = (Button) findViewById(R.id.score_buttonB);
        scoreButtonB.setClickable(true);
        wonSetsButtonB = (Button) findViewById(R.id.won_sets_buttonB);
        wonSetsButtonB.setClickable(true);
    }

    public void displayWinMessage(String message ){
        TextView setView = (TextView) findViewById(R.id.win_message);
        setView.setText(message);
    }

//displaying set number
    public void displaySetNumber(int set) {
        TextView setView = (TextView) findViewById(R.id.set_number);
        setView.setText(String.valueOf(set));
    }

// reset of scores and timeouts after winning set
    public void resetPart(View view){
        displayForTeamB(scoreTeamA=0);
        displayForTeamA(scoreTeamB=0);
        displayTimeoutTeamA(timeoutTeamA=0);
        displayTimeoutTeamB(timeoutTeamB=0);
        displaySetNumber(setNumber);
        timeoutButton.setClickable(true);
    }

// reset all app
    public void resetAll(View view) {
        displayWonSetsTeamA(wonSetsTeamA=0);
        displayWonSetsTeamB(wonSetsTeamB=0);
        displaySetNumber(setNumber = 1);
        resetPart(view);
        displayWinMessage(MESSAGE);
        scoreButton.setClickable(true);
        wonSetsButton.setClickable(true);
        scoreButtonB.setClickable(true);
        wonSetsButtonB.setClickable(true);
    }

//cheking number of sets if was the last one it deactivate buttons - exept RESET button
    public void checkNumberOfSets(View view) {
        if ((wonSetsTeamA==3)||(setNumber > 5)||(wonSetsTeamB==3)) {
            timeoutButton = (Button) findViewById(R.id.timeout_button);
            timeoutButton.setClickable(false);
            scoreButton = (Button) findViewById(R.id.score_button);
            scoreButton.setClickable(false);
            wonSetsButton = (Button) findViewById(R.id.won_sets_button);
            wonSetsButton.setClickable(false);
            timeoutButtonB = (Button) findViewById(R.id.timeout_buttonB);
            timeoutButtonB.setClickable(false);
            scoreButtonB = (Button) findViewById(R.id.score_buttonB);
            scoreButtonB.setClickable(false);
            wonSetsButtonB = (Button) findViewById(R.id.won_sets_buttonB);
            wonSetsButtonB.setClickable(false);
        }
    }

// team A table
// team A scores displays
    public void displayForTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(score));
    }
//team A timeouts displays
    public void displayTimeoutTeamA(int timeoutTeamA) {
        TextView timeoutView = (TextView) findViewById(R.id.team_a_timeout);
        timeoutView.setText(String.valueOf(timeoutTeamA));
    }
//team A won sets display
    public void displayWonSetsTeamA (int wonSetsTeamA) {
        TextView wonSetsView = (TextView) findViewById(R.id.team_a_won_sets);
        wonSetsView.setText(String.valueOf(wonSetsTeamA));
    }
//counting scores for team A
    public void addScoreTeamA(View view) {
        scoreTeamA = scoreTeamA + 1;
        if ((setNumber==5)&&(scoreTeamA >= MATCH_BALL) && (scoreTeamA - scoreTeamB >= 2)) {
            wonSetsTeamA = wonSetsTeamA + 1;
            displayWonSetsTeamA(wonSetsTeamA);
            displayWinMessage("TEAM A WIN");
            checkNumberOfSets(view);
        }
        else if ((scoreTeamA >= SET_BALL) && (scoreTeamA - scoreTeamB >=2) ) {
            wonSetsTeamA = wonSetsTeamA + 1;
            setNumber = setNumber + 1;
            displayWonSetsTeamA(wonSetsTeamA);
            resetPart(view);
        } else {
            displayForTeamA(scoreTeamA);
        }
    }
//counting timeoutsfor team A
    public void addTimeoutTeamA(View view) {
        timeoutTeamA = timeoutTeamA +1;
        if ((timeoutTeamA == 0)||(timeoutTeamA < 3)) {
            displayTimeoutTeamA(timeoutTeamA);
        } else {
           timeoutButton.setClickable(false);
        }
    }

//counting won sets team A
    public void addWonSetTeamA (View view){
        wonSetsTeamA = wonSetsTeamA +1;
        setNumber = setNumber +1;
        displayWonSetsTeamA(wonSetsTeamA);
        checkNumberOfSets(view);
        resetPart(view);
        if (wonSetsTeamA >= 3){
            displayWinMessage("TEAM A WIN");
        } else {
            displayWinMessage(MESSAGE);
       }
    }

// team B table
// team B scores displays
public void displayForTeamB(int score) {
    TextView scoreView = (TextView) findViewById(R.id.team_b_score);
    scoreView.setText(String.valueOf(score));
}
    //team B timeouts displays
    public void displayTimeoutTeamB(int timeoutTeamB) {
        TextView timeoutView = (TextView) findViewById(R.id.team_b_timeout);
        timeoutView.setText(String.valueOf(timeoutTeamB));
    }
    //team B won sets display
    public void displayWonSetsTeamB (int wonSetsTeamB) {
        TextView wonSetsView = (TextView) findViewById(R.id.team_b_won_sets);
        wonSetsView.setText(String.valueOf(wonSetsTeamB));
    }
    //counting scores for team B
    public void addScoreTeamB(View view) {
        scoreTeamB = scoreTeamB + 1;
        if ((setNumber==5)&&(scoreTeamB >= MATCH_BALL) && (scoreTeamB - scoreTeamA >=2)) {
            wonSetsTeamB = wonSetsTeamB + 1;
            displayWonSetsTeamB(wonSetsTeamB);
            displayWinMessage("TEAM B WIN");
            checkNumberOfSets(view);
        }
        else if ((scoreTeamB >= SET_BALL) && (scoreTeamB - scoreTeamA >=2) ) {
            wonSetsTeamB = wonSetsTeamB + 1;
            setNumber = setNumber + 1;
            displayWonSetsTeamB(wonSetsTeamB);
            resetPart(view);
        } else {
            displayForTeamB(scoreTeamB);
        }

    }

    //counting timeoutsfor team B
    public void addTimeoutTeamB(View view) {
        timeoutTeamB = timeoutTeamB +1;
        if ((timeoutTeamB == 0)||(timeoutTeamB < 3)) {
            displayTimeoutTeamB(timeoutTeamB);
        } else {
            timeoutButtonB.setClickable(false);
        }
    }

    //counting won sets team B
    public void addWonSetTeamB (View view){
        wonSetsTeamB = wonSetsTeamB +1;
        setNumber = setNumber +1;
        displayWonSetsTeamB(wonSetsTeamB);
        checkNumberOfSets(view);
        resetPart(view);
        if (wonSetsTeamB >= 3){
            displayWinMessage("TEAM B WIN");
        } else {
            displayWinMessage(MESSAGE);
        }
    }

}
