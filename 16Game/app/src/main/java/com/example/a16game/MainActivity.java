package com.example.a16game;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.TimerCustom;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ProgressBar;
import android.widget.QuickContactBadge;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ArrayList<Integer> numbers = new ArrayList<Integer>();
    int tempNum;
    long totalTime = 60000;
    TimerCustom timer;
    String timerShow = "01:00";
    boolean timerOn = false;
    ProgressBar progV;
    ProgressBar progT;
    int progVal = 0;
    GridLayout gb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gb = (GridLayout)findViewById(R.id.gb);
        progV = (ProgressBar)findViewById(R.id.progVal);
        progT = (ProgressBar)findViewById(R.id.progTime);

        getSupportActionBar().setTitle(timerShow);

        gridFill();

        timer = new TimerCustom(totalTime, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long tempNum = millisUntilFinished / 1000;
                timerShow = "00:" + (tempNum < 10 ? ("0" + tempNum) : tempNum);
                getSupportActionBar().setTitle(timerShow);
                progT.setProgress((int)tempNum, true);
            }

            @Override
            public void onFinish() {
                if (progVal != 16) {
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Game over!")
                            .setMessage("YOU LOSE...")
                            .setPositiveButton("Again", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    restAct();
                                }
                            })
                            .show();

                } else {

                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Game over!")
                            .setMessage("YOU WIN! CONGRATS!!!\nTime: 00:" + (59 - Integer.parseInt(timerShow.substring(timerShow.indexOf(":") + 1))))
                            .setPositiveButton("Again", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    restAct();
                                }
                            })
                            .show();
                }
            }
        };
    }

    public void check(View view) {
        if (!timerOn) {
            timer.start();
            timerOn = true;
            findViewById(R.id.restart).setEnabled(true);
        }

        Button b = (Button)view;

        if (numbers.get(progVal).toString() != b.getText()) {
            timer.subTime(1000);
            b.setBackgroundColor(Color.rgb(255, 0, 0));

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    b.setBackgroundColor(Color.rgb(205, 206, 206));
                }
            }, 500);

        } else {
            progV.setProgress(++progVal, true);
            b.setEnabled(false);
            b.setBackgroundColor(Color.rgb(0, 255, 0));

            if (progVal == 16) {
                timer.cancel();
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Game over!")
                        .setMessage("YOU WIN! CONGRATS!!!\nTime: 00:" + (59 - Integer.parseInt(timerShow.substring(timerShow.indexOf(":") + 1))))
                        .setPositiveButton("Again", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                restAct();
                            }
                        })
                        .show();

            }
        }
    }

    public void restart(View view) {
        restAct();
    }

    void restAct() {
        numbers = new ArrayList<Integer>();
        totalTime = 60000;
        timerShow = "01:00";
        timer.cancel();
        getSupportActionBar().setTitle(timerShow);
        timerOn = false;
        progV.setProgress(0, true);
        progT.setProgress(60, true);
        progVal = 0;

        gridFill();
        findViewById(R.id.restart).setEnabled(false);

    }

    private void gridFill() {
        if (gb.getChildCount() > 0) {
            gb.removeAllViews();
        }

        for (int i = 0; i < 16; i++) {
            Button b = new Button(this);

            GridLayout.LayoutParams param = new GridLayout.LayoutParams(GridLayout.spec(GridLayout.UNDEFINED, 1f), GridLayout.spec(GridLayout.UNDEFINED, 1f));
            param.height = 300;
            param.setMargins(7, 7, 7, 7);
            b.setLayoutParams(param);

            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    check(v);
                }
            });

            while (true) {
                tempNum = new Random().nextInt(100);

                if (!numbers.contains(tempNum)) {
                    numbers.add(tempNum);
                    break;
                }
            }

            b.setText(String.valueOf(tempNum));
            gb.addView(b);
        }
        Collections.sort(numbers);
    }
}
