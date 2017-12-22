package com.example.harsharora.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SeekBar timerSeekBar;
     TextView timerText;
    Button button;
    Boolean counterActive=false;
    CountDownTimer countDownTimer;
    public void updateTimer(int secondsLeft)
    {
        int minutes=secondsLeft/60;
        int seconds=secondsLeft-minutes*60;
        String secondStr = Integer.toString(seconds);
        if (seconds<=9)
        {
            secondStr="0"+ secondStr;
        }


        timerText.setText(Integer.toString(minutes)+":"+ secondStr);
    }

    public void controlTimer (View view)
    {
        if(counterActive==false) {
            counterActive = true;

            timerSeekBar.setEnabled(false);

            button.setText("Stop!");


           countDownTimer= new CountDownTimer(timerSeekBar.getProgress() * 1000 + 100, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {
                    updateTimer((int) millisUntilFinished / 1000);

                }

                @Override
                public void onFinish() {
                    timerText.setText("0:00");
                    MediaPlayer mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                    mPlayer.start();

                }
            }.start();
        } else
        {
            timerText.setText("0:30");
            timerSeekBar.setEnabled(true);
            timerSeekBar.setProgress(30);
            countDownTimer.cancel();
            button.setText("Go!");
            counterActive=false;



        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         button=(Button) findViewById(R.id.button);

         timerSeekBar =  (SeekBar) findViewById(R.id.seekBar);
         timerText= (TextView)findViewById(R.id.textView);
        timerSeekBar.setMax(600);
        timerSeekBar.setProgress(30);
        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

               updateTimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
