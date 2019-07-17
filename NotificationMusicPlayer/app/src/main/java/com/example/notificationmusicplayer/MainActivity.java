package com.example.notificationmusicplayer;

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
import android.widget.RemoteViews;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    static final String FAVORITE = "set_unset_favorite";
    static final String NEXTMUSIC = "next_music";
    static final String PREVMUSIC = "prev_music";
    static final String PLAYPAUSEMUSIC = "next_pause_music";

    static NotificationManager manager;
    static NotificationCompat.Builder builder;
    static RemoteViews notiflay;
    static RemoteViews notiflaySmall;

    static ArrayList<Song> songs = new ArrayList<>();

    static int curIndex;

    static boolean isPlay = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CustomReciever r = new CustomReciever();
        IntentFilter i = new IntentFilter();
        i.addAction(FAVORITE);
        i.addAction(NEXTMUSIC);
        i.addAction(PREVMUSIC);
        i.addAction(PLAYPAUSEMUSIC);
        registerReceiver(r, i);

        songs.add(new Song(R.drawable.skillet_album, "The Last Night", "Skillet"));
        songs.add(new Song(R.drawable.maroon5_album, "Girls Like You", "Marron 5 ft. Cardi B."));
        songs.add(new Song(R.drawable.elvis_presley_album, "Heartbreak Hotel", "Elvis Presley"));
        songs.add(new Song(R.drawable.michael_jackson_album, "You Are Not Alone", "Michael Jackson"));
        songs.add(new Song(R.drawable.red_album, "Hymn For The Missing", "Red"));
    }

    public void open(View view) {
        manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        Context context = getApplicationContext();

        notiflay = new RemoteViews(getPackageName(), R.layout.notification_m_p);
        notiflaySmall = new RemoteViews(getPackageName(), R.layout.notification_m_p_small);

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
                    .setOngoing(true)
                    .setAutoCancel(true);

            setRemoteViews(notiflay);
            setRemoteViews(notiflaySmall);

        } else {
            builder = new NotificationCompat.Builder(context)
                    .setSmallIcon(R.drawable.vinil)
                    .setStyle(null)
                    .setCustomContentView(notiflaySmall)
                    .setOngoing(true)
                    .setAutoCancel(true);
//                    .setCustomContentView(notiflaySmall);

            setRemoteViews(notiflaySmall);
        }

        Random rand = new Random();
        curIndex = rand.nextInt(4);
        Song s = songs.get(curIndex);
        notiflay.setImageViewResource(R.id.album, s.albumId);
        notiflay.setTextViewText(R.id.song, s.name);
        notiflay.setTextViewText(R.id.artist, s.artist);

        notiflaySmall.setImageViewResource(R.id.album, s.albumId);
        notiflaySmall.setTextViewText(R.id.song, s.name);
        notiflaySmall.setTextViewText(R.id.artist, s.artist);

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
