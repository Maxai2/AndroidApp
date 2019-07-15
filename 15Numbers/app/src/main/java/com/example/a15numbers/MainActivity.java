package com.example.a15numbers;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.DrawableRes;
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
import java.util.Random;
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

    ArrayList<Integer> numbersField = new ArrayList<>();

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
                int index = numbersField.indexOf(0);

                top(index);
            }

            public void onSwipeRight() {
                Toast.makeText(MainActivity.this, "right", Toast.LENGTH_SHORT).show();
                int index = numbersField.indexOf(0);

                if (index == 4 || index == 8 || index == 12) {
                    return;
                }

                right(index);
            }

            public void onSwipeLeft() {
                Toast.makeText(MainActivity.this, "left", Toast.LENGTH_SHORT).show();
                int index = numbersField.indexOf(0);

                if (index == 3 || index == 7 || index == 11) {
                    return;
                }

                left(index);
            }

            public void onSwipeBottom() {
                Toast.makeText(MainActivity.this, "bottom", Toast.LENGTH_SHORT).show();
                int index = numbersField.indexOf(0);

                bottom(index);
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
        randomMe();
    }

    private void top(int index) {
        Drawable res = field.getChildAt(index + 4).getBackground();

        field.getChildAt(index).setBackground(res);
        numbersField.set(index, numbersField.get(index + 4));

        field.getChildAt(index + 4).setBackground(null);
        numbersField.set(index  + 4, 0);
    }

    private void right(int index) {
        Drawable res = field.getChildAt(index - 1).getBackground();

        field.getChildAt(index).setBackground(res);
        numbersField.set(index, numbersField.get(index - 1));

        field.getChildAt(index - 1).setBackground(null);
        numbersField.set(index - 1, 0);
    }

    private void left(int index) {
        Drawable res = field.getChildAt(index + 1).getBackground();

        field.getChildAt(index).setBackground(res);
        numbersField.set(index, numbersField.get(index + 1));

        field.getChildAt(index + 1).setBackground(null);
        numbersField.set(index + 1, 0);
    }

    private void bottom(int index) {
        Drawable res = field.getChildAt(index - 4).getBackground();

        field.getChildAt(index).setBackground(res);
        numbersField.set(index, numbersField.get(index - 4));

        field.getChildAt(index - 4).setBackground(null);
        numbersField.set(index - 4, 0);
    }

    private void fillNumber() {
        for (int i = 0; i < 15; ++i) {
            ((FrameLayout)field.getChildAt(i)).setBackgroundResource(numbers[i]);
            numbersField.add(i + 1);
        }

        ((FrameLayout)field.getChildAt(15)).setBackground(null);
        numbersField.add(0);
    }

    private void randomMe() {
        Random rand = new Random();
        int index = 0;
        for (int i = 0; i < 1000; ++i) {

            switch (rand.nextInt(3)) {
                case 0: // top
                    index = numbersField.indexOf(0);
                    top(index);
                    break;
                case 1: // right
                    index = numbersField.indexOf(0);

                    if (index == 4 || index == 8 || index == 12) {
                        continue;
                    }

                    right(index);
                    break;
                case 2: // bottom
                    index = numbersField.indexOf(0);

                    bottom(index);
                    break;
                case 3: // left
                    index = numbersField.indexOf(0);

                    if (index == 3 || index == 7 || index == 11) {
                        continue;
                    }

                    left(index);
                    break;

                default:
                    break;
            }
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
        numbersField = new ArrayList<>();
        fillNumber();
    }
}
