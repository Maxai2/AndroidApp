package com.example.a15numbers;

import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    GridLayout field;
    TextView timeLeft;
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        field = findViewById(R.id.field);
        timeLeft = findViewById(R.id.timeLeft);
        timeLeft.setText("00:00");

        final Handler handler = new Handler();
        Timer timer = new Timer(false);
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        timeLeft.setText(String.valueOf(count++));
                    }
                });
            }
        };

        timer.scheduleAtFixedRate(timerTask, 1000, 1000);

        gridField();
    }

    void gridField() {

    }

    public void reset(View view) {

    }
}
