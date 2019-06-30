package com.example.rgb;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar r;
    SeekBar g;
    SeekBar b;
    TextView curR;
    TextView curG;
    TextView curB;
    RelativeLayout rl;
    int rC = 0;
    int gC = 0;
    int bC = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        rl = (RelativeLayout)findViewById(R.id.mainBack);
        changeBack();

        curR = (TextView)findViewById(R.id.curR);
        curG = (TextView)findViewById(R.id.curG);
        curB = (TextView)findViewById(R.id.curB);

        r = (SeekBar)findViewById(R.id.R);
        g = (SeekBar)findViewById(R.id.G);
        b = (SeekBar)findViewById(R.id.B);

        r.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                rC = progress;
                curR.setText(String.valueOf(rC));
                changeBack();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });


        g.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                gC = progress;
                curG.setText(String.valueOf(gC));
                changeBack();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });


        b.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                bC = progress;
                curB.setText(String.valueOf(bC));
                changeBack();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

    }

    void changeBack() {
        rl.setBackgroundColor(Color.rgb(rC, gC, bC));
    }
}
