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
    boolean firstClick = true;

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
                case "kinder":
                    row = 5;
                    col = 5;
                    minesCountText.setText("5");
                    minesCount = 1;
                    break;
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

                b.setTooltipText(i + " " + j);
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

                fl.setTooltipText(i + " " + j);
                fl.setLayoutParams(paramFl);
                fl.setBackgroundColor(Color.rgb(192, 192, 192));
                fl.setPadding(5, 5, 5, 5);
            }
        }
    }

    private void check(View v) {
        FrameLayout fl = (FrameLayout)v.getParent();

        fl.removeViewAt(0);

        String targetBt = String.valueOf(fl.getTooltipText());

        int row = Integer.parseInt(targetBt.substring(0, targetBt.indexOf(" ")));
        int col = Integer.parseInt(targetBt.substring(targetBt.indexOf(" ") + 1));

        if (firstClick) {
            fillBombs(row, col);
            fillNumber();

            firstClick = false;
        }

        wideSearch(row, col, 3);
    }

    void wideSearch(int rowS, int colS, int colRow) {

        int min_x = colS - colRow / 2;
        int max_x = colS + colRow / 2;
        int min_y = rowS - colRow / 2;
        int max_y = rowS + colRow / 2;
        int step = 0;

        if (min_x < 0) {
            min_x = 0;
        }

        if (max_x >= col) {
            max_x = col - 1;
        }

        if (min_y < 0) {
            min_y = 0;
        }

        if (max_y >= row) {
            max_y = row - 1;
        }

        for (int i = min_x; i <= max_x; ++i) {
            step = min_y * row + i;

            if (field[step].cell == Cell.bomb) {
                return;
            } else {
                FrameLayout fl = (FrameLayout) minesField.getChildAt(step);
                if (fl.getChildAt(0) != null) {
                    fl.removeViewAt(0);
                }
            }
        }

        for (int i = min_y + 1; i < max_y; ++i) {
            step = i * row + min_x;

            if (field[step].cell == Cell.bomb) {
                return;
            } else {
                FrameLayout fl = (FrameLayout) minesField.getChildAt(step);
                fl.removeViewAt(0);
            }

            step = i * row + max_x;

            if (field[step].cell == Cell.bomb) {
                return;
            } else {
                FrameLayout fl = (FrameLayout) minesField.getChildAt(step);
                fl.removeViewAt(0);
            }
        }

        for (int i = min_x; i <= max_x; ++i) {
            step = max_y * row + i;

            if (field[step].cell == Cell.bomb) {
                return;
            } else {
                FrameLayout fl = (FrameLayout) minesField.getChildAt(step);
                fl.removeViewAt(0);
            }
        }

        colRow += 2;
        wideSearch(rowS, colS, colRow);

//        int index = 0;
//        int stepCount = 8;
//        int step = 0;
//        ArrayList<Integer> nums;
//        boolean notFind = true;
//
//        while(notFind) {
//
//            nums = stepsFill(rowS * row + colS, stepCount);
//
//            for (int i = 0; i < stepCount; ++i) {
//
//                step = index + nums.get(i);
//
//                if (field[step].cell == Cell.bomb) {
//                    notFind = false;
//                    break;
//                } else {
//                    FrameLayout fl = (FrameLayout) minesField.getChildAt(step);
//                    fl.removeViewAt(0);
//                }
//            }
//
//            stepCount = stepCount * 2;
//        }
    }

//    ArrayList<Integer> stepsFill(int index, int stepCount) {
//        ArrayList<Integer> num = new ArrayList<>();
//
//        for (int i = 0; i < stepCount; ++i) {
//            num.add(index - );
//        }
//
//        return  num;
//    }

//    void justBFS(int v) {
//        boolean[] used = new boolean [row]; // массив пометок
//        int[] queue = new int [col]; // очередь
//
//        int qH = 0; // голова очереди
//        int qT = 0; // хвост
//
//        /* <обработка вершины v> */
//        used[v] = true; // помечаем исходную вершину
//        queue[qT++] = v; // помещаем ее в очередь
//
//        while (qH < qT) { // пока очередь не пуста
//                 v = queue[qH++]; // извлекаем текущую вершину
//
//                 for (int nv = 0; nv < row; nv++) { // перебираем вершины
////                     if (!used[nv] && graph[v][nv]) { // если nv не помечена и смежна с v
//                       if (!used[nv] && (field[v * row + nv].cell == Cell.bomb)) { // если nv не помечена и смежна с v
//
//                         /* <обработка вершины nv> */
//                         used[nv] = true; // помечаем ее
//                         queue[qT++] = nv; // и добавляем в очередь
//                }
//            }
//        }
//    }

    private void fillBombs(int rowU, int colU) {
        int rowTemp = 0;
        int colTemp = 0;
        int index = 0;

        for (int i = 0; i < minesCount; ++i) {
            while(true) {
                rowTemp = rand.nextInt(row);
                colTemp = rand.nextInt(col);

                if (rowU == rowTemp && colU == colTemp) {
                    continue;
                }

                index = rowTemp * row + colTemp;
                if (!randIndex.contains(index)) {
                    randIndex.add(index);
                    break;
                }
            }

            field[index].cell = Cell.bomb;

            FrameLayout fl = (FrameLayout) minesField.getChildAt(index);

            fl.setBackground(ContextCompat.getDrawable(this, R.drawable.mine));
//            fl.getChildAt(0).setBackground(ContextCompat.getDrawable(this, R.drawable.mine));
        }
    }

    private void fillNumber() {
        int rowF = 0;
        int colF = 0;
        int index = 0;

        for (Integer rIndex : randIndex) {
            rowF = field[rIndex].row;
            colF = field[rIndex].col;

            if (rowF != 0) {
                index = (rowF - 1) * row + colF; // -y x
                if (0 <= index && index < row * col) {
                    setNum(eightStep(rowF - 1, colF), index);
                }
            }

            if (rowF != 0 && colF != col - 1) {
                index = (rowF - 1) * row + colF + 1; // -y +x
                if (0 <= index && index < row * col) {
                    setNum(eightStep(rowF - 1, colF + 1), index);
                }
            }

            if (colF != col - 1) {
                index = rowF * row + colF + 1; // y +x
                if (0 <= index && index < row * col) {
                    setNum(eightStep(rowF, colF + 1), index);
                }
            }

            if (rowF != row - 1 && colF != col - 1) {
                index = (rowF + 1) * row + (colF + 1); // +y +x
                if (0 <= index && index < row * col) {
                    setNum(eightStep(rowF + 1, colF + 1), index);
                }
            }

            if (rowF != row - 1) {
                index = (rowF + 1) * row + colF; // +y x
                if (0 <= index && index < row * col) {
                    setNum(eightStep(rowF + 1, colF), index);
                }
            }

            if (rowF != row - 1 && colF != 0) {
                index = (rowF + 1) * row + (colF - 1); // +y -x
                if (0 <= index && index < row * col) {
                    setNum(eightStep(rowF + 1, (colF - 1)), index);
                }
            }

            if (colF != 0) {
                index = rowF * row + (colF - 1); // y -x
                if (0 <= index && index < row * col) {
                    setNum(eightStep(rowF, (colF - 1)), index);
                }
            }

            if (rowF != 0 && colF != 0) {
                index = (rowF - 1) * row + (colF - 1); // -y -x
                if (0 <= index && index < row * col) {
                    setNum(eightStep(rowF - 1, (colF - 1)), index);
                }
            }
        }
    }

    private void setNum(int num, int index) {
        if (field[index].cell == Cell.bomb)
            return;

        int id = 0;

        switch (num) {
            case 1:
                id = R.drawable.one;
                break;
            case 2:
                id = R.drawable.two;
                break;
            case 3:
                id = R.drawable.three;
                break;
            case 4:
                id = R.drawable.four;
                break;
            case 5:
                id = R.drawable.five;
                break;
            case 6:
                id = R.drawable.six;
                break;
            case 7:
                id = R.drawable.seven;
                break;
            case 8:
                id = R.drawable.eight;
                break;
        }

        FrameLayout fl = (FrameLayout) minesField.getChildAt(index);
//        fl.getChildAt(0).setBackground(ContextCompat.getDrawable(this, id));
        fl.setBackground(ContextCompat.getDrawable(this, id));
        field[index].cell = Cell.fill;
    }

    private int eightStep(int rowP, int colP) {
        int count = 0;

        if(checkNullBomb((rowP - 1) * row + colP)) { // -y x
            count++;
        }

        if(checkNullBomb ((rowP - 1) * row + (colP + 1))) { // -y +x
            count++;
        }

        if(checkNullBomb(rowP * row + (colP + 1))) { // y +x
            count++;
        }

        if(checkNullBomb((rowP + 1) * row + (colP + 1))) { // +y +x
            count++;
        }

        if(checkNullBomb((rowP + 1) * row + colP)) { // +y x
            count++;
        }

        if(checkNullBomb((rowP + 1) * row + (colP - 1))) { // +y -x
            count++;
        }

        if(checkNullBomb(rowP * row + (colP - 1))) { // y -x
            count++;
        }

        if(checkNullBomb((rowP - 1) * row + (colP - 1))) { // -y -x
            count++;
        }

        return count;
    }

    private boolean checkNullBomb(int index) {
        return 0 <= index && index < row * col && (field[index].cell == Cell.bomb);
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

    public void reset(View view) {
        timesLeftText.setText("000");
        firstClick = true;

        field = new CellItem[row * col];

        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                field[i * row + j] = new CellItem(Cell.empty, i, j);

            }
        }

        randIndex = new ArrayList<>();
        minesField.removeAllViews();

        gridFill();
    }
}
