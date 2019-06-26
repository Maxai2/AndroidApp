package com.example.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView expression;
    TextView curNumber;
    int ans = 0;
    String ansStr = "";
    String exp = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expression = (TextView)findViewById(R.id.expression);
        curNumber = (TextView)findViewById(R.id.curNumber);
        curNumber.setText("0");
    }

    public void calcAct(View view) {
        Button bt = (Button)view;

        switch (bt.getId()) {
            case R.id.clear:
                expression.setText("");
                curNumber.setText("0");
                ans = 0;
                break;
            case R.id.backspace:
                ansStr = ansStr.substring(0, ansStr.length() - 1);
                break;
            case R.id.divide:
                break;
            case R.id.multiple:
                break;
            case R.id.minus:
                break;
            case R.id.plus:
                break;
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
                break;
            case R.id.equal:
                break;
            case R.id.radical:
                break;
            case R.id.dot:
                break;
        }

        curNumber.setText(ansStr);
//        expression.setText(expression.getText() + ansStr);
    }
}
