package com.example.notificationmusicplayer;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RemoteViews;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void open(View view) {

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Notification builder;

        Context context = getApplicationContext();

        RemoteViews notiflay = new RemoteViews(getPackageName(), R.layout.notification_m_p);
        RemoteViews notiflaySmall = new RemoteViews(getPackageName(), R.layout.notification_m_p_small);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            String CHANNEL_ID = "MusicPlayer";

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "MusicPlayer",
                    NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("MusicPlayer channel description");

            channel.setSound(null, null);

            manager.createNotificationChannel(channel);

            builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                    .setSmallIcon(R.drawable.vinil)
                    .setStyle(null)
                    .setCustomContentView(notiflaySmall)
                    .setCustomBigContentView(notiflay)
                    .build();
        } else {
            builder = new NotificationCompat.Builder(context)
                    .setSmallIcon(R.drawable.vinil)
                    .setStyle(null)
                    .setCustomContentView(notiflaySmall)
                    .setCustomBigContentView(notiflay)
                    .build();
        }

//        PendingIntent action = PendingIntent.getActivity(context,
//                0, new Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/Prosto_Amiran")),
//                PendingIntent.FLAG_CANCEL_CURRENT);

        manager.notify(101, builder);
    }

    public void notifAct(View view) {
        switch (view.getId()) {
            case R.id.prev:
                break;
            case R.id.playPause:
                view.setBackground(getResources().getDrawable(R.drawable.pause_music));
                break;
            case R.id.next:
                break;
            case R.id.favUnfav:
                break;
        }
    }
}
