package com.example.tranciever;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public String MY_MESSAGE = "com.example.tranciever.id1";
    static EditText textEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textEdit = findViewById(R.id.textEdit);

        textEdit.addTextChangedListener(new TextWatcher() {
            String beforeText;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                beforeText = s.toString();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String sendT = s.toString();
                if (sendT.equals(beforeText)) {
                    return;
                }

                if (sendT == null || sendT.equals("")) {
                    return;
                }

                Intent intent = new Intent();
                intent.setAction(MY_MESSAGE);
                intent.putExtra("someData", sendT);
                // начиная с Android 3.0, в целях безопасности сообщения игнорируются
                // остановленными приложениями. Поэтому следует добавлять дополнительный флаг,
                // разрешающий запуск активности
                intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                sendBroadcast(intent);
            }
        });

        // for android oreo
        // MyReceiver r = new MyReceiver();
        // IntentFilter i = new IntentFilter(MY_MESSAGE);
        // registerReceiver(r, i);
    }

//    public void send(View v) {
//        Intent intent = new Intent();
//        intent.setAction(MY_MESSAGE);
//        intent.putExtra("someData", text);
//        // начиная с Android 3.0, в целях безопасности сообщения игнорируются
//        // остановленными приложениями. Поэтому следует добавлять дополнительный флаг,
//        // разрешающий запуск активности
//        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
//        sendBroadcast(intent);
//    }
}
