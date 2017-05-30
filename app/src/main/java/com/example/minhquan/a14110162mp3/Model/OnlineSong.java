package com.example.minhquan.a14110162mp3.Model;

import android.graphics.Bitmap;

/**
 * Created by MinhQuan on 5/30/2017.
 */

public class OnlineSong extends Song {
    private String avt;
    public OnlineSong() {

    }
    public OnlineSong(String avt) {
        this.avt = avt;
    }

    public String getAvt() {
        return avt;
    }

    public void setAvt(String avt) {
        this.avt = avt;
    }
}
