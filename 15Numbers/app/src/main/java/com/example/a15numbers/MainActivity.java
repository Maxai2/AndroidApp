package com.example.a15numbers;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
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
                        timeLeft.setText(timeCorrect(count++));
                    }
                });
            }
        };

//        timer.scheduleAtFixedRate(timerTask, 1000, 1000);

        gridField();
        fillNumber();
    }

    private void fillNumber() {
        for (int i = 0; i< 16; ++i) {
            ((FrameLayout)field.getChildAt(i)).setBackgroundResource(R.drawable.one);
        }
    }

    String timeCorrect(int num) {

        int resH = (int)(num / 3600);
        num -= resH * 3600;
        int resM = (int)(num / 60);
        num -= resM * 60;
        int resS = (int)num;

        return String.format("%02d:%02d", resM, resS);
    }

    void gridField() {
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {

                FrameLayout fl = new FrameLayout(this);

                field.addView(fl);
                GridLayout.LayoutParams paramFl = (GridLayout.LayoutParams)fl.getLayoutParams();

                paramFl.width = 0;
                paramFl.height = 0;
                paramFl.columnSpec = GridLayout.spec(i,1,1f);
                paramFl.rowSpec = GridLayout.spec(j,1,1f);

                fl.setLayoutParams(paramFl);
                fl.setBackgroundColor(Color.rgb(192, 192, 192));
                fl.setPadding(5, 5, 5, 5);
            }
        }
    }

    public void reset(View view) {

    }
}
