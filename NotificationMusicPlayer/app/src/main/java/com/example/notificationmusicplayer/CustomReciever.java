package com.example.notificationmusicplayer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.time.chrono.MinguoChronology;
import java.util.MissingFormatArgumentException;

public class CustomReciever extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        switch (intent.getAction()) {
            case MainActivity.FAVORITE:
                boolean l = MainActivity.songs.get(MainActivity.curIndex).isLike;

                if (l) {
                    MainActivity.notiflay.setImageViewResource(R.id.favUnfav, R.drawable.unlike);
                    MainActivity.notiflaySmall.setImageViewResource(R.id.favUnfav, R.drawable.unlike);
                } else {
                    MainActivity.notiflay.setImageViewResource(R.id.favUnfav, R.drawable.like);
                    MainActivity.notiflaySmall.setImageViewResource(R.id.favUnfav, R.drawable.like);
                }

                MainActivity.songs.get(MainActivity.curIndex).isLike = !l;
                break;
            case MainActivity.NEXTMUSIC:
                if (MainActivity.curIndex < 4) {
                    MainActivity.curIndex++;
                } else {
                    MainActivity.curIndex = 0;
                }

                changeSound(MainActivity.curIndex);
                break;
            case MainActivity.PREVMUSIC:
                if (MainActivity.curIndex > 0) {
                    MainActivity.curIndex--;
                } else {
                    MainActivity.curIndex = 4;
                }

                changeSound(MainActivity.curIndex);
                break;
            case MainActivity.PLAYPAUSEMUSIC:
                boolean p = MainActivity.isPlay;

                if (p) {
                    MainActivity.notiflay.setImageViewResource(R.id.playPause, R.drawable.play_music);
                    MainActivity.notiflaySmall.setImageViewResource(R.id.playPause, R.drawable.play_music);
                } else {
                    MainActivity.notiflay.setImageViewResource(R.id.playPause, R.drawable.pause_music);
                    MainActivity.notiflaySmall.setImageViewResource(R.id.playPause, R.drawable.pause_music);
                }

                MainActivity.isPlay = !p;
                break;
        }

        MainActivity.manager.notify(105, MainActivity.builder.build());
    }

    void changeSound(int index) {
        Song s = MainActivity.songs.get(index);
        MainActivity.notiflay.setImageViewResource(R.id.album, s.albumId);
        MainActivity.notiflay.setTextViewText(R.id.song, s.name);
        MainActivity.notiflay.setTextViewText(R.id.artist, s.artist);

        MainActivity.notiflaySmall.setImageViewResource(R.id.album, s.albumId);
        MainActivity.notiflaySmall.setTextViewText(R.id.song, s.name);
        MainActivity.notiflaySmall.setTextViewText(R.id.artist, s.artist);

        if (s.isLike) {
            MainActivity.notiflay.setImageViewResource(R.id.favUnfav, R.drawable.like);
            MainActivity.notiflaySmall.setImageViewResource(R.id.favUnfav, R.drawable.like);
        } else {
            MainActivity.notiflay.setImageViewResource(R.id.favUnfav, R.drawable.unlike);
            MainActivity.notiflaySmall.setImageViewResource(R.id.favUnfav, R.drawable.unlike);
        }

        if (MainActivity.isPlay) {
            MainActivity.notiflay.setImageViewResource(R.id.playPause, R.drawable.pause_music);
            MainActivity.notiflaySmall.setImageViewResource(R.id.playPause, R.drawable.pause_music);
        } else {
            MainActivity.notiflay.setImageViewResource(R.id.playPause, R.drawable.play_music);
            MainActivity.notiflaySmall.setImageViewResource(R.id.playPause, R.drawable.play_music);
        }
    }
}
