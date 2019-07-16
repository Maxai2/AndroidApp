package com.example.a15numbers;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AlertDialog;
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

    Timer timer;
    TimerTask timerTask;
    boolean start = true;
    MediaPlayer mediaPlayer;

    ArrayList<Integer> winNumbersField = new ArrayList<Integer>();
    //-----------------------------------------------------
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        relatLay = findViewById(R.id.relatLay);

        relatLay.setOnTouchListener(new OnSwipeTouchListener(this) {

            public void onSwipeTop() {
                if (start) {
                    timer.scheduleAtFixedRate(createTimerTask(), 0, 1000);
                    start = false;
                }

                int index = numbersField.indexOf(0);

                top(index);
                mediaPlayer.start();
                win();
            }

            public void onSwipeRight() {
                if (start) {
                    timer.scheduleAtFixedRate(createTimerTask(), 0, 1000);
                    start = false;
                }

                int index = numbersField.indexOf(0);

                if (index == 4 || index == 8 || index == 12) {
                    return;
                }

                right(index);
                mediaPlayer.start();
                win();
            }

            public void onSwipeLeft() {
                if (start) {
                    timer.scheduleAtFixedRate(createTimerTask(), 0, 1000);
                    start = false;
                }

                int index = numbersField.indexOf(0);

                if (index == 3 || index == 7 || index == 11) {
                    return;
                }

                left(index);
                mediaPlayer.start();
                win();
            }

            public void onSwipeBottom() {
                if (start) {
                    timer.scheduleAtFixedRate(createTimerTask(), 0, 1000);
                    start = false;
                }

                int index = numbersField.indexOf(0);

                bottom(index);
                mediaPlayer.start();
                win();
            }
        });

        field = findViewById(R.id.field);
        timeLeft = findViewById(R.id.timeLeft);
        timeLeft.setText("00:00");

        mediaPlayer = MediaPlayer.create(this, R.raw.swap);

//        final Handler handler = new Handler();
        timer = new Timer(false);
//        timerTask = new TimerTask() {
//            @Override
//            public void run() {
////                handler.post(new Runnable() {
////                    @Override
////                    public void run() {
////                        timeLeft.setText(timeCorrect(count++));
////                    }
////                });
//            }
//        };

        gridField();
        fillNumber();
        randomMe();
    }
    //-----------------------------------------------------
    private TimerTask createTimerTask() {
        return new TimerTask() {
            @Override
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        timeLeft.setText(timeCorrect(count++));
                    }
                });
            }
        };
    }
    //-----------------------------------------------------
    private Activity getActivity() {
        return this;
    }
    //-----------------------------------------------------
    void win() {
        for (int i = 0; i < 16; ++i) {
            if (winNumbersField.get(i) != numbersField.get(i)) {
                return;
            }
        }

        new AlertDialog.Builder(this)
                .setTitle("Congratulation!")
                .setMessage("U WIN!!!")
                .show();

        timer.cancel();
    }
    //-----------------------------------------------------
    private void fillNumber() {
        for (int i = 0; i < 15; ++i) {
            ((FrameLayout)field.getChildAt(i)).setBackgroundResource(numbers[i]);
            numbersField.add(i + 1);
            winNumbersField.add(i + 1);
        }

        ((FrameLayout)field.getChildAt(15)).setBackground(null);
        numbersField.add(0);
        winNumbersField.add(0);
    }
    //-----------------------------------------------------
    private void top(int index) {
        View view = field.getChildAt(index + 4);

        if (view == null) {
            return;
        }

        Drawable res = view.getBackground();

        field.getChildAt(index).setBackground(res);
        numbersField.set(index, numbersField.get(index + 4));

        field.getChildAt(index + 4).setBackground(null);
        numbersField.set(index  + 4, 0);
    }
    //-----------------------------------------------------
    private void right(int index) {
        View view = field.getChildAt(index - 1);

        if (view == null) {
            return;
        }

        Drawable res = view.getBackground();

        field.getChildAt(index).setBackground(res);
        numbersField.set(index, numbersField.get(index - 1));

        field.getChildAt(index - 1).setBackground(null);
        numbersField.set(index - 1, 0);
    }
    //-----------------------------------------------------
    private void left(int index) {
        View view = field.getChildAt(index + 1);

        if (view == null) {
            return;
        }

        Drawable res = view.getBackground();

        field.getChildAt(index).setBackground(res);
        numbersField.set(index, numbersField.get(index + 1));

        field.getChildAt(index + 1).setBackground(null);
        numbersField.set(index + 1, 0);
    }
    //-----------------------------------------------------
    private void bottom(int index) {
        View view = field.getChildAt(index - 4);

        if (view == null) {
            return;
        }

        Drawable res = view.getBackground();

        field.getChildAt(index).setBackground(res);
        numbersField.set(index, numbersField.get(index - 4));

        field.getChildAt(index - 4).setBackground(null);
        numbersField.set(index - 4, 0);
    }
    //-----------------------------------------------------
    private void randomMe() {
        Random rand = new Random();
        int index = 0;
        boolean repeat;
        for (int i = 0; i < 100; ++i) {

            repeat = true;
            while(repeat) {
                switch (rand.nextInt(3)) {
                    case 0: // top
                        index = numbersField.indexOf(0);

                        top(index);
                        repeat = false;
                        break;
                    case 1: // right
                        index = numbersField.indexOf(0);

//                        if (index == 4 || index == 8 || index == 12) {
//                            continue;
//                        }

                        right(index);
                        repeat = false;
                        break;
                    case 2: // bottom
                        index = numbersField.indexOf(0);

                        bottom(index);
                        repeat = false;
                        break;
                    case 3: // left
                        index = numbersField.indexOf(0);

//                        if (index == 3 || index == 7 || index == 11) {
//                            continue;
//                        }

                        left(index);
                        repeat = false;
                        break;
                    default:
                        break;
                }
            }
        }
    }
    //-----------------------------------------------------
    String timeCorrect(int num) {

        int resH = (int)(num / 3600);
        num -= resH * 3600;
        int resM = (int)(num / 60);
        num -= resM * 60;
        int resS = (int)num;

        return String.format("%02d:%02d", resM, resS);
    }
    //-----------------------------------------------------
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
    //-----------------------------------------------------
    public void reset(View view) {
        numbersField = new ArrayList<>();
        fillNumber();
        randomMe();
        count = 0;
        timeLeft.setText("00:00");
        timer.cancel();
        timer.purge();
        timer = new Timer(false);
        start = true;
    }
    //-----------------------------------------------------
}
