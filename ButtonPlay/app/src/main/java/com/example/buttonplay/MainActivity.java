package com.example.buttonplay;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Random random = new Random();
    ArrayList<String> fontsArr = new ArrayList<>();
    int count = 0;

    long startTimeInMillis = 2700000; // 45 min
    CountDownTimer countDownTimer;
    long mtimeLeftInMillis = startTimeInMillis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fontsArr.add("alfa_slab_one");
        fontsArr.add("arizonia");
        fontsArr.add("bonbon");
        fontsArr.add("butcherman");
        fontsArr.add("sacramento");

        countDownTimer = new CountDownTimer(mtimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mtimeLeftInMillis = millisUntilFinished;
                getActionBar().setTitle(millisUntilFinished);
            }

            @Override
            public void onFinish() {

            }
        }
    }

    public void onClick(View view) {
        Button btn = (Button)view;
//        btn.setWidth(btn.getWidth() + 25);

        btn.setWidth(this.random.nextInt((500 - 100) + 1) + 100);

        btn.setTextSize(this.random.nextInt((36 - 8) + 1) + 8);

        String fontStr = fontsArr.get(this.random.nextInt((4 - 0) + 1) + 0);
        Typeface font = Typeface.create(fontStr, Typeface.NORMAL);
        btn.setTypeface(font);

        int r = this.random.nextInt((255 - 0) + 1) + 0;
        int g = this.random.nextInt((255 - 0) + 1) + 0;
        int b = this.random.nextInt((255 - 0) + 1) + 0;
        btn.setTextColor(Color.rgb(r, g, b));

        int rb = this.random.nextInt((255 - 0) + 1) + 0;
        int gb = this.random.nextInt((255 - 0) + 1) + 0;
        int bb = this.random.nextInt((255 - 0) + 1) + 0;
        btn.setBackgroundColor(Color.rgb(rb, gb, bb));

        Toast.makeText(this, String.valueOf(++count), Toast.LENGTH_SHORT).show();

        if (count == 20) {
            btn.setText("Enough!");
            btn.setEnabled(false);
        }
     }
}
