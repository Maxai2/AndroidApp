package com.example.reciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class CustomReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
//        Toast.makeText(context, intent.getStringExtra("someData"), Toast.LENGTH_LONG).show();
        if (intent.getStringExtra("someData") != null) {
            MainActivity.textEdit.setText(intent.getStringExtra("someData"));
            MainActivity.textEdit.setSelection(MainActivity.textEdit.getText().length());
        }
    }
}
