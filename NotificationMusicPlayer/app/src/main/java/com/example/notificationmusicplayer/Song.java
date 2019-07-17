package com.example.notificationmusicplayer;

public class Song {
    int albumId;
    String name;
    String artist;
    boolean isLike;

    Song (int albumId, String name, String artist) {
        this.albumId = albumId;
        this.name = name;
        this.artist = artist;
        this.isLike = false;
    }
}
