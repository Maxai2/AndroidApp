package com.example.notificationmusicplayer;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

public class MainActivity extends AppCompatActivity {

    static final String FAVORITE = "set_unset_favorite";
    static final String NEXTMUSIC = "next_music";
    static final String PREVMUSIC = "prev_music";
    static final String PLAYPAUSEMUSIC = "next_pause_music";

    static NotificationManager manager;
    static NotificationCompat.Builder builder;
    static RemoteViews notiflay;

    static Button favBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        favBtn = findViewById(R.id.favUnfav);

        CustomReciever rf = new CustomReciever();
        IntentFilter iff = new IntentFilter(FAVORITE);
        registerReceiver(rf, iff);

//        CustomReciever rn = new CustomReciever();
//        IntentFilter in = new IntentFilter(NEXTMUSIC);
//        registerReceiver(rn, in);
//
//        CustomReciever rp = new CustomReciever();
//        IntentFilter ip = new IntentFilter(PREVMUSIC);
//        registerReceiver(rp, ip);
//
//        CustomReciever rpp = new CustomReciever();
//        IntentFilter ipp = new IntentFilter(PLAYPAUSEMUSIC);
//        registerReceiver(rpp, ipp);
    }

    public void open(View view) {
        manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        Context context = getApplicationContext();

        notiflay = new RemoteViews(getPackageName(), R.layout.notification_m_p);
//        RemoteViews notiflaySmall = new RemoteViews(getPackageName(), R.layout.notification_m_p_small);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            String CHANNEL_ID = "MusicPlayer";

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "MusicPlayer",
                    NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("MusicPlayer channel description");

            channel.setSound(null, null);

            manager.createNotificationChannel(channel);

            setRemoteViews(notiflay);



            builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                    .setSmallIcon(R.drawable.vinil)
                    .setStyle(null)
                  .setCustomContentView(notiflay)
                    .setCustomBigContentView(notiflay)
                    .setAutoCancel(true);

//            setRemoteViews(notiflaySmall);

        } else {
            builder = new NotificationCompat.Builder(context)
                    .setSmallIcon(R.drawable.vinil)
                    .setStyle(null);
//                    .setCustomContentView(notiflaySmall);

//            setRemoteViews(notiflaySmall);
        }

        manager.notify(105, builder.build());
    }

    private void setRemoteViews(RemoteViews remoteViews) {
        Intent favoriteNotification = new Intent(FAVORITE);
        Intent playPauseIntent = new Intent(PLAYPAUSEMUSIC);
        Intent previousIntent = new Intent(PREVMUSIC);
        Intent nextIntent = new Intent(NEXTMUSIC);

        PendingIntent pendingFavoriteIntent = PendingIntent.getBroadcast(this, 0, favoriteNotification, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pendingPlayPauseIntent = PendingIntent.getBroadcast(this, 0, playPauseIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pendingPreviousIntent = PendingIntent.getBroadcast(this, 0, previousIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pendingNextIntent = PendingIntent.getBroadcast(this, 0, nextIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        remoteViews.setOnClickPendingIntent(R.id.favUnfav, pendingFavoriteIntent);
        remoteViews.setOnClickPendingIntent(R.id.playPause, pendingPlayPauseIntent);
        remoteViews.setOnClickPendingIntent(R.id.next, pendingNextIntent);
        remoteViews.setOnClickPendingIntent(R.id.prev, pendingPreviousIntent);
    }

}
