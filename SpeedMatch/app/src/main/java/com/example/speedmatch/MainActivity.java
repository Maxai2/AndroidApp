package com.example.speedmatch;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Delayed;

public class MainActivity extends AppCompatActivity {

//    private GifImageView gifImageView;

    ArrayList pics = new ArrayList();
    ImageView iv;
//    ImageView ivC;
//    ImageView ivW;
    Random random = new Random();
    TextView point;
    TextView time;
    int lastIndex;
    int currentIndex;
    int correctAns;
    int wrongAns;

    long startTimeInMillis = 60000; // 1 min
    CountDownTimer countDownTimer;
    long mtimeLeftInMillis = startTimeInMillis;

    Button start;
    Button reset;
    Button correct;
    Button inCorrect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pics.add(R.drawable.circle);
        pics.add(R.drawable.square);
        pics.add(R.drawable.romb);
        pics.add(R.drawable.triangle_left);
        pics.add(R.drawable.triangle_right);
        pics.add(R.drawable.triangle_leftdown);
        pics.add(R.drawable.triangle_rightdown);

        iv = (ImageView) findViewById(R.id.pic);
//        ivC = (ImageView) findViewById(R.id.picC);
//        ivW = (ImageView) findViewById(R.id.picW);

//        ivC.setVisibility(View.GONE);
//        ivW.setVisibility(View.GONE);

        start = findViewById(R.id.start);
        reset = findViewById(R.id.reset);
        point = findViewById(R.id.point);
        time = findViewById(R.id.time);
        correct = findViewById(R.id.correct);
        inCorrect = findViewById(R.id.inCorrect);

        reset.setEnabled(false);
        correct.setEnabled(false);
        inCorrect.setEnabled(false);

        countDownTimer = new CountDownTimer(mtimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                time.setText(String.valueOf((int)millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Game over!")
                    .setMessage("Your score: " + point.getText() +
                            "\nNumber of Correct Answer: " + correctAns +
                            "\nNumber of Wrong Answer: " + wrongAns)
                    .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            resetGame();
                            Intent intent = new Intent(Intent.ACTION_MAIN);
                            intent.addCategory(Intent.CATEGORY_HOME);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    })
                    .setPositiveButton("Play Again", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            resetGame();
                            startGame();
                        }
                    })
                    .show();
            }
        };
    }

    public static int generateRandomIntIntRange(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public void answer(View view) {
        Button bt = (Button)view;

        switch (bt.getId()) {
            case R.id.correct:
                if (lastIndex == currentIndex) {
                    equalNotEqual(true);
                } else {
                    equalNotEqual(false);
                }
                break;
            case R.id.inCorrect:
                if (lastIndex != currentIndex) {
                    equalNotEqual(true);
                } else {
                    equalNotEqual(false);
                }
                break;
        }

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                iv.setBackgroundColor(Color.TRANSPARENT);
                lastIndex = currentIndex;
                currentIndex = generateRandomIntIntRange(0, 6);
                iv.setImageResource((int)pics.get(currentIndex));
            }
        }, 500);
    }

    public void equalNotEqual(boolean equal) {
        iv.setImageDrawable(null);

        if (equal) {
            point.setText(String.valueOf(Integer.parseInt(point.getText().toString()) + 100));
            correctAns++;

            iv.setBackgroundColor(Color.rgb(0, 255, 0));
        } else {
            point.setText(String.valueOf(Integer.parseInt(point.getText().toString()) - 75));
            wrongAns++;

            iv.setBackgroundColor(Color.rgb(255, 0, 0));
        }

    }

    public void remote(View view) {
        Button bt = (Button)view;

        switch (bt.getId()) {
            case R.id.start: {
                startGame();

                break;
            }
            case R.id.reset:
                if (!time.getText().equals("0")) {
                    countDownTimer.cancel();

                    new AlertDialog.Builder(this)
                        .setTitle("Attention!")
                        .setMessage("Your progress will be lost, do u want it?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                resetGame();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                countDownTimer.start();
                            }
                        })
                        .show();
                } else {
                    resetGame();
                }
                break;
        }
    }

    public void resetGame() {
        point.setText("0");
        time.setText("60");

        correctAns = 0;
        wrongAns = 0;

        mtimeLeftInMillis = 60000;

        iv.setImageDrawable(null);
        iv.setBackgroundColor(Color.TRANSPARENT);

        correct.setEnabled(false);
        inCorrect.setEnabled(false);

        start.setEnabled(true);
        reset.setEnabled(false);
    }

    public void startGame() {

        countDownTimer.start();

        start.setEnabled(false);
        reset.setEnabled(true);

        lastIndex = generateRandomIntIntRange(0, 6);
        iv.setImageResource((int)pics.get(lastIndex));

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                currentIndex = generateRandomIntIntRange(0, 6);
                iv.setImageResource((int)pics.get(currentIndex));

                correct.setEnabled(true);
                inCorrect.setEnabled(true);
            }
        }, 1000);
    }

//    boolean test = false;
//
//    public void test(View view) {
//        Button bt = (Button)view;
//
//        if (bt.getId() == R.id.test) {
//            iv.setImageDrawable(null);
//            if (test) {
//                iv.setBackgroundColor(Color.rgb(5, 12, 123));
//            } else {
//                iv.setBackgroundColor(Color.TRANSPARENT);
//            }
//
//            test = !test;
//        }
//    }
}

//    @Override protected void onResume() {
//        super.onResume();
//        gifImageView.startAnimation();
//    }
//
//    @Override protected void onStop() {
//        super.onStop();
//        gifImageView.stopAnimation();
//    }
//
//    public void OnClick(View view) {
//        gifImageView.startAnimation();
//    }


//        gifImageView = findViewById(R.id.gifImageView);
//
//        Bitmap m = BitmapFactory.decodeResource(this.getResources(), R.drawable.kube);
//        int size  = m.getRowBytes() * m.getHeight();
//        ByteBuffer b = ByteBuffer.allocate(size);
//
//        m.copyPixelsToBuffer(b);
//
//        byte[] bytes = new byte[size];
//
//        try {
//            b.get(bytes, 0, bytes.length);
//        } catch (BufferUnderflowException e) {
//            // always happens
//        }
//
//        gifImageView.setBytes(bytes);