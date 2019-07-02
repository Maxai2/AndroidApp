package com.example.minesweeper;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.TextView;

public class FieldLvl extends AppCompatActivity {

    String lvl;
    int row;
    int col;

    TextView minesCount;
    TextView timesLeft;

    GridLayout minesField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.field);

        minesCount = (TextView)findViewById(R.id.minesCount);
        timesLeft = (TextView)findViewById(R.id.timeLeft);
        timesLeft.setText("000");

        minesField = (GridLayout)findViewById(R.id.mineField);

        Intent intent = getIntent();

        if (intent != null) {
            lvl = intent.getStringExtra("lvl");

            switch (lvl) {
                case "easy":
                    row = 10;
                    col = 10;
                    minesCount.setText("10");
                    break;
                case "middle":
                    row = 20;
                    col = 20;
                    minesCount.setText("40");
                    break;
                case "hard":
                    row = 30;
                    col = 30;
                    minesCount.setText("90");
                    break;
                case "skull":
                    row = 10;
                    col = 10;
                    minesCount.setText("80");
                    break;
            }

            gridFill();

        }

    }

    private void gridFill() {
        minesField.setRowCount(row);
        minesField.setColumnCount(col);

        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {

                ImageButton ib = new ImageButton(this);
                minesField.addView(ib);

                //GridLayout.LayoutParams param = new GridLayout.LayoutParams(GridLayout.spec(i,1, 1f), GridLayout.spec(j,1, 1f));
                GridLayout.LayoutParams param = (GridLayout.LayoutParams)ib.getLayoutParams();
                param.width = 0;
                param.height = 0;
                param.columnSpec = GridLayout.spec(i,1,1f);
                param.rowSpec = GridLayout.spec(j,1,1f);
//                param.setGravity(Gravity.FILL);
                param.setMargins(0, 0, 0, 0);
                ib.setLayoutParams(param);
                ib.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        check(v);
                    }
                });
                ib.setBackgroundResource(R.drawable.flag);

            }
        }
    }

    private void check(View v) {
    }
}
