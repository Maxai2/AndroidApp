package com.example.speedmatch;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

//    private GifImageView gifImageView;

    ArrayList pics = new ArrayList();
    ImageView iv;
    Random random = new Random();

    long startTimeInMillis = 60000; // 1 min
    CountDownTimer countDownTimer;
    long mtimeLeftInMillis = startTimeInMillis;

    Button start;
    Button reset;

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

        iv.setImageResource((int)pics.get(generateRandomIntIntRange(0, 6)));

        start = findViewById(R.id.start);
        reset = findViewById(R.id.reset);

        reset.setEnabled(false);

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

    }

    public static int generateRandomIntIntRange(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public void answer(View view) {
        Button bt = (Button)view;

        switch (bt.getId()) {
            case R.id.correct:
                break;
            case R.id.inCorrect:
                break;
        }
    }

    public void remote(View view) {
        Button bt = (Button)view;

        switch (bt.getId()) {
            case R.id.start:
                countDownTimer = new CountDownTimer(mtimeLeftInMillis, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        mtimeLeftInMillis = millisUntilFinished;
                    }

                    @Override
                    public void onFinish() {

                    }
                }.start();
                break;
            case R.id.reset:
                break;
        }
    }
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

