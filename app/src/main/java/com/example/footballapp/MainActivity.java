package com.example.footballapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.CountDownTimer;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final long START_TIME_IN_MILLIS =600000;
    private Button mButtonStartPause;
    private TextView mTextViewCounter;

    private CountDownTimer CounterTimes;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;


    int score = 0;
    int scoreTeamB = 0;

    int shotsA = 0;
    int cornersA = 0;

    int shotsB = 0;
    int cornersB = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextViewCounter = (TextView) findViewById(R.id.timer_text);
        mButtonStartPause = (Button) findViewById(R.id.button_start);

        mButtonStartPause.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(mTimerRunning){
                    pauseTimer();
                } else{
                    startTimer();
                }
            }
        });
        updateCountDownText();

    }

    public void cornersA(View view) {
        score = score + 1;
        displayForTeamAcorners(score);
    }

    public void shotsA(View view) {
        shotsA = shotsA + 1;
        displayForTeamAshots(shotsA);
    }

    public void goalA(View view) {
        cornersA = cornersA + 1;
        displayForTeamA(cornersA);

    }

    //team B
    public void cornersB(View view) {
        cornersB = cornersB + 1;
        displayForTeamBcorners(cornersB);
    }

    public void shotsB(View view) {
        shotsB = shotsB + 1;
        displayForTeamBshots(shotsB);
    }

    public void goalsB(View view) {
        scoreTeamB = scoreTeamB + 1;
        displayForTeamB(scoreTeamB);

    }

    //display team A
    public void displayForTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(score));
    }

    public void displayForTeamAshots(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_shots);
        scoreView.setText(String.valueOf(score));
    }

    public void displayForTeamAcorners(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_corners);
        scoreView.setText(String.valueOf(score));
    }

    //display tema B

    public void displayForTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(score));
    }

    public void displayForTeamBshots(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_shots);
        scoreView.setText(String.valueOf(score));
    }

    public void displayForTeamBcorners(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_corners);
        scoreView.setText(String.valueOf(score));
    }

    //reset

    public void resetButton(View v) {
        score = 0;
        scoreTeamB = 0;

        score = 0;
        scoreTeamB = 0;

        shotsA = 0;
        cornersA = 0;

        shotsB = 0;
        cornersB = 0;


        displayForTeamA(score);
        displayForTeamB(scoreTeamB);

        displayForTeamAshots(shotsA);
        displayForTeamAcorners(cornersA);

        displayForTeamBshots(shotsB);
        displayForTeamBcorners(cornersB);

        resetTimer();
    }

    public void startTimer(){
        mButtonStartPause.setText("Pause");
        CounterTimes = new CountDownTimer(mTimeLeftInMillis, 1000){
            public void onTick(long millisUnitlFinished){
                mTimeLeftInMillis = millisUnitlFinished;
                updateCountDownText();
            }

            public void onFinish(){
                mTimerRunning = false;
                mButtonStartPause.setText("Start");
            }
        }.start();
        mTimerRunning=true;
    }

    public void pauseTimer(){
        CounterTimes.cancel();
        mTimerRunning=false;
        mButtonStartPause.setText("Start");

    }

    public void resetTimer(){
        mTimeLeftInMillis = START_TIME_IN_MILLIS;
        updateCountDownText();
        mButtonStartPause.setText("Start");

    }

    private void updateCountDownText(){
        int min = (int) (mTimeLeftInMillis / 1000) /60;
        int sec = (int) (mTimeLeftInMillis / 1000) %60;

        String timeLeftForm = String.format(Locale.getDefault(),"%02d:%02d", min, sec);

        mTextViewCounter.setText(timeLeftForm);
    }

}