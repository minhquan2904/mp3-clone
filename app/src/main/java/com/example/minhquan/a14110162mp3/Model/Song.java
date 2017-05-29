package com.example.minhquan.a14110162mp3.Model;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by MinhQuan on 5/28/2017.
 */

public class Song implements Serializable {
    private String Title;
    private String Artist;
    private String Url;
    private Bitmap Thumnbail;

    public Song() {

    }

    public Song(String title, String artist, String url, Bitmap thumnbail) {
        Title = title;
        Artist = artist;
        Url = url;
        Thumnbail = thumnbail;
    }

    public Bitmap getThumnbail() {
        return Thumnbail;
    }

    public void setThumnbail(Bitmap thumnbail) {
        Thumnbail = thumnbail;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getArtist() {
        return Artist;
    }

    public void setArtist(String artist) {
        Artist = artist;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
}
