package com.example.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView expression;
    TextView curNumber;
    float ansF = 0;
    float ansS = 0;
    String ansStr = "0";
    String exp = "";
    boolean dotIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expression = (TextView)findViewById(R.id.expression);
        curNumber = (TextView)findViewById(R.id.curNumber);
        curNumber.setText(ansStr);
    }

    public void calcNum(View view) {
        Button bt = (Button)view;

        if (ansStr == "0") {
            ansStr = "";
        }

        switch (bt.getId()) {
            case R.id.one:
                ansStr += "1";
                break;
            case R.id.two:
                ansStr += "2";
                break;
            case R.id.three:
                ansStr += "3";
                break;
            case R.id.four:
                ansStr += "4";
                break;
            case R.id.five:
                ansStr += "5";
                break;
            case R.id.six:
                ansStr += "6";
                break;
            case R.id.seven:
                ansStr += "7";
                break;
            case R.id.eight:
                ansStr += "8";
                break;
            case R.id.nine:
                ansStr += "9";
                break;
            case R.id.zero:
                if (!ansStr.equals("0"))
                    ansStr += "0";
                break;
            case R.id.dot:
                if (!dotIn) {
                    if (ansStr.equals("")) {
                        ansStr += "0.";
                    } else {
                        ansStr += ".";
                    }
                    dotIn = true;
                }
                break;
        }

        curNumber.setText(ansStr);
    }

    public void clearNum(View view) {
        Button bt = (Button)view;

        switch (bt.getId()) {
            case R.id.clear:
                ansStr = "0";
                dotIn = false;
                break;
            case R.id.backspace:
                if (ansStr.length() == 1) {
                    ansStr = "0";
                } else {
                    if (ansStr.substring(ansStr.length() - 1).equals("."))
                        dotIn = false;

                    ansStr = ansStr.substring(0, ansStr.length() - 1);
                }
                break;
        }

        curNumber.setText(ansStr);
    }

    public void numExp(View view) {
        Button bt = (Button)view;

        if (ansS != 0)
            ansF = ansS;

        ansS = Float.parseFloat(ansStr);

        String oldS = expression.getText().toString();

        if (oldS.equals("")) {
            oldS = ansStr;
            ansStr = "";
        } else {
            oldS.substring(oldS.length() - 1).equals(".")
        }

        curNumber.setText(ansStr);

        switch (bt.getId()) {
            case R.id.divide:
                oldS += '÷';
                break;
            case R.id.multiple:
                oldS += '*';
                break;
            case R.id.minus:
                oldS += '-';
                break;
            case R.id.plus:
                oldS += '+';
                break;
        }

        expression.setText(oldS);
    }

    public void totalEq(View view) {

    }

    public void plusMinAdd(View view) {
//        if (ansStr.equals("0") || ansStr.equals("0."))
    }
}
