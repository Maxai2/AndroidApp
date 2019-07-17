package com.example.notificationmusicplayer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.Toast;


public class CustomReciever extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        switch (intent.getAction()) {
            case MainActivity.FAVORITE:
                Toast.makeText(context, "fav", Toast.LENGTH_SHORT).show();
                MainActivity.notiflay.setImageViewResource(R.id.favUnfav,R.drawable.like);
                break;
            case MainActivity.NEXTMUSIC:
                Toast.makeText(context, "next", Toast.LENGTH_SHORT).show();
                break;
            case MainActivity.PREVMUSIC:
                Toast.makeText(context, "prev", Toast.LENGTH_SHORT).show();
                break;
            case MainActivity.PLAYPAUSEMUSIC:
                Toast.makeText(context, "play", Toast.LENGTH_SHORT).show();
                break;
        }

        MainActivity.manager.notify(105, MainActivity.builder.build());
    }
}
