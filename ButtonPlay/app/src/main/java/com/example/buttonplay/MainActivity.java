package com.example.buttonplay;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Random random = new Random();
    ArrayList fontsArr = new ArrayList();
    int count = 0;

    ArrayList fLesson = new ArrayList();
    ArrayList sLesson = new ArrayList();

//    long startTimeInMillis = 2700000; // 45 min
//    CountDownTimer countDownTimer;
//    long mtimeLeftInMillis = startTimeInMillis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fontsArr.add(R.font.sacramento);
        fontsArr.add(R.font.butcherman);
        fontsArr.add(R.font.bonbon);
        fontsArr.add(R.font.arizonia);
        fontsArr.add(R.font.alfa_slab_one);
        fontsArr.add(R.font.caesar_dressing);

        fLesson.add(10);
        fLesson.add(20);
        fLesson.add(00);

        sLesson.add(11);
        sLesson.add(50);
        sLesson.add(00);

//        countDownTimer = new CountDownTimer(mtimeLeftInMillis, 1000) {
//            @Override
//            public void onTick(long millisUntilFinished) {
//                mtimeLeftInMillis = millisUntilFinished;
//            }
//
//            @Override
//            public void onFinish() {
//
//            }
//        }.start();
    }

    public void onClick(View view) {
       Button btn = (Button)view;
//       btn.setWidth(btn.getWidth() + 25);

       btn.setWidth(this.random.nextInt((1000 - 100) + 1) + 100);

       btn.setTextSize(this.random.nextInt((36 - 8) + 1) + 8);

       int fontIndex = (int)fontsArr.get(this.random.nextInt((5 - 0) + 1) + 0);

       Typeface font = ResourcesCompat.getFont(this, fontIndex);

       btn.setTypeface(font);

       int r = this.random.nextInt((255 - 0) + 1) + 0;
       int g = this.random.nextInt((255 - 0) + 1) + 0;
       int b = this.random.nextInt((255 - 0) + 1) + 0;
       btn.setTextColor(Color.rgb(r, g, b));

       r = this.random.nextInt((255 - 0) + 1) + 0;
       g = this.random.nextInt((255 - 0) + 1) + 0;
       b = this.random.nextInt((255 - 0) + 1) + 0;
       btn.setBackgroundColor(Color.rgb(r, g, b));

       Toast.makeText(this, String.valueOf(++count), Toast.LENGTH_SHORT).show();

//       getSupportActionBar().setTitle(timeFormat());
       getSupportActionBar().setTitle(timeFormat(new Time(System.currentTimeMillis()).getTime()));

       r = this.random.nextInt((255 - 0) + 1) + 0;
       g = this.random.nextInt((255 - 0) + 1) + 0;
       b = this.random.nextInt((255 - 0) + 1) + 0;
       getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.rgb(r, g, b)));

       if (count == 20) {
           btn.setText("Enough!");
           btn.setEnabled(false);
       }
    }

    public String timeFormat(long time) {
        int seconds = (int) (time / 1000) % 60 ;
        int minutes = (int) ((time / (1000 * 60)) % 60);
        int hours = (int) ((time / (1000 * 60 * 60)) % 24) + 4; // +4 for Baku

        if ((int)fLesson.get(0) == hours) {

        }

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}
