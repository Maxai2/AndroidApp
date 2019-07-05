package com.example.minesweeper;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class FieldLvl extends AppCompatActivity {

    String lvl;
    int row;
    int col;
    int minesCount;

    TextView minesCountText;
    TextView timesLeftText;

    GridLayout minesField;

    boolean isMine = false;

    CellItem[] field;
    ArrayList<Integer> randIndex;

    Random rand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.field);

        minesCountText = (TextView)findViewById(R.id.minesCount);
        timesLeftText = (TextView)findViewById(R.id.timeLeft);
        timesLeftText.setText("000");

        minesField = (GridLayout)findViewById(R.id.mineField);

        rand = new Random();
        getSupportActionBar().hide();

        Intent intent = getIntent();

        if (intent != null) {
            lvl = intent.getStringExtra("lvl");

            switch (lvl) {
                case "easy":
                    row = 10;
                    col = 10;
                    minesCountText.setText("10");
                    minesCount = 10;
                    break;
                case "middle":
                    row = 20;
                    col = 20;
                    minesCountText.setText("40");
                    minesCount = 40;
                    break;
                case "hard":
                    row = 30;
                    col = 30;
                    minesCountText.setText("90");
                    minesCount = 90;
                    break;
                case "skull":
                    row = 10;
                    col = 10;
                    minesCountText.setText("80");
                    minesCount = 80;
                    break;
            }

            field = new CellItem[row * col];

            for (int i = 0; i < row; ++i) {
                for (int j = 0; j < col; ++j) {
                    field[i * row + j] = new CellItem(Cell.empty, i, j);
                }
            }

            randIndex = new ArrayList<>();
            gridFill();
        }
    }

    private void gridFill() {
        minesField.setRowCount(row);
        minesField.setColumnCount(col);

        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {

                final FrameLayout fl = new FrameLayout(this);

                Button b = new Button(this, null, R.style.Widget_AppCompat_Button_Borderless);
                fl.addView(b);

                FrameLayout.LayoutParams paramB = (FrameLayout.LayoutParams)b.getLayoutParams();
                paramB.height = paramB.width = FrameLayout.LayoutParams.MATCH_PARENT;
                b.setLayoutParams(paramB);

                b.setTooltipText(i + "" + j);
                b.setBackgroundColor(Color.rgb(245, 245, 245));
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        check(v);
                    }
                });

                minesField.addView(fl);
                GridLayout.LayoutParams paramFl = (GridLayout.LayoutParams)fl.getLayoutParams();

                paramFl.width = 0;
                paramFl.height = 0;
                paramFl.columnSpec = GridLayout.spec(i,1,1f);
                paramFl.rowSpec = GridLayout.spec(j,1,1f);

                fl.setTooltipText(i + "" + j);
                fl.setLayoutParams(paramFl);
                fl.setBackgroundColor(Color.rgb(192, 192, 192));
                fl.setPadding(5, 5, 5, 5);

            }
        }
    }

    private void check(View v) {
        FrameLayout fl = (FrameLayout)v.getParent();

        Toast.makeText(this, String.valueOf(fl.getTooltipText()), Toast.LENGTH_SHORT).show();

        fillBombs();
    }

    private void fillBombs() {
        int rowTemp = 0;
        int colTemp = 0;
        int index = 0;

        for (int i = 0; i < minesCount; ++i) {
            while(true) {
                rowTemp = rand.nextInt(row);
                colTemp = rand.nextInt(col);

                index = rowTemp * row + colTemp;
                if (!randIndex.contains(index)) {
                    randIndex.add(index);
                    break;
                }
            }

            field[index] = new CellItem(Cell.bomb, rowTemp, colTemp);

            FrameLayout fl = (FrameLayout) minesField.getChildAt(index);

            fl.getChildAt(0).setBackground(ContextCompat.getDrawable(this, R.drawable.mine));
        }
    }

    private void fillNumber() {
        int rowF = 0;
        int colF = 0;
        int index = 0;

        for (Integer rIndex : randIndex) {
            rowF = field[rIndex].row;
            colF = field[rIndex].col;

        }
    }

    private int eightStep(int rowP, int colP) {
        int count = 1;



        return count;
    }

    public void flagMine(View view) {
        Button tb = (Button) view;

        if (!isMine) {
            tb.setBackground(ContextCompat.getDrawable(this, R.drawable.flag));
        } else {
            tb.setBackground(ContextCompat.getDrawable(this, R.drawable.mine));
        }

        isMine = !isMine;
    }
}
