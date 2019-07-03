package com.example.minesweeper;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.view.CollapsibleActionView;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class FieldLvl extends AppCompatActivity {

    String lvl;
    int row;
    int col;

    TextView minesCount;
    TextView timesLeft;

    GridLayout minesField;

    boolean flagMine = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.field);

        minesCount = (TextView)findViewById(R.id.minesCount);
        timesLeft = (TextView)findViewById(R.id.timeLeft);
        timesLeft.setText("000");

        minesField = (GridLayout)findViewById(R.id.mineField);

        getSupportActionBar().hide();

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

                final FrameLayout fl = new FrameLayout(this);

                Button b = new Button(this, null, R.style.Widget_AppCompat_Button_Borderless);
                fl.addView(b);

                FrameLayout.LayoutParams paramB = (FrameLayout.LayoutParams)b.getLayoutParams();
                paramB.height = paramB.width = FrameLayout.LayoutParams.MATCH_PARENT;
                paramB.setMargins(1, 1, 1, 1);
                b.setLayoutParams(paramB);

                b.setTooltipText(i + " " + j);
                b.setBackgroundColor(Color.rgb(192, 193, 220));
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

                fl.setTooltipText(i + " " + j + "b");
                fl.setLayoutParams(paramFl);
                fl.setBackgroundColor(Color.rgb(192, 192, 192));
                fl.setPadding(0, 0, 0, 0);

            }
        }
    }

    private void check(View v) {
        Toast.makeText(this, String.valueOf(v.getTooltipText()), Toast.LENGTH_SHORT).show();
    }

    public void flagMine(View view) {
        ToggleButton tb = (ToggleButton)view;

        if (flagMine) {
            tb.setBackground(ContextCompat.getDrawable(this, R.drawable.flag));
        } else {
            tb.setBackground(ContextCompat.getDrawable(this, R.drawable.mine));
        }

        flagMine = !flagMine;
    }
}
