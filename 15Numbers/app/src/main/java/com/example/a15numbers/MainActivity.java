package com.example.a15numbers;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    GridLayout field;
    TextView timeLeft;
    int count;

    int[] numbers = new int[]{
            R.drawable.one,
            R.drawable.two,
            R.drawable.three,
            R.drawable.four,
            R.drawable.five,
            R.drawable.six,
            R.drawable.seven,
            R.drawable.eight,
            R.drawable.nine,
            R.drawable.ten,
            R.drawable.eleven,
            R.drawable.twelve,
            R.drawable.thirteen,
            R.drawable.fourteen,
            R.drawable.fiveteen
    };

    ArrayList<Integer>

    RelativeLayout relatLay;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        relatLay = findViewById(R.id.relatLay);

        relatLay.setOnTouchListener(new OnSwipeTouchListener(this) {

            public void onSwipeTop() {
                Toast.makeText(MainActivity.this, "top", Toast.LENGTH_SHORT).show();
            }

            public void onSwipeRight() {
                Toast.makeText(MainActivity.this, "right", Toast.LENGTH_SHORT).show();
            }

            public void onSwipeLeft() {
                Toast.makeText(MainActivity.this, "left", Toast.LENGTH_SHORT).show();
            }

            public void onSwipeBottom() {
                Toast.makeText(MainActivity.this, "bottom", Toast.LENGTH_SHORT).show();
            }
        });

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
        for (int i = 0; i < 15; ++i) {
            ((FrameLayout)field.getChildAt(i)).setBackgroundResource(numbers[i]);
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
                paramFl.columnSpec = GridLayout.spec(j,1,1f);
                paramFl.rowSpec = GridLayout.spec(i,1,1f);

                fl.setLayoutParams(paramFl);
                fl.setBackgroundColor(Color.rgb(192, 192, 192));
                fl.setPadding(5, 5, 5, 5);
            }
        }
    }

    public void reset(View view) {

    }
}
