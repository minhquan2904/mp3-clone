package com.example.minhquan.a14110162mp3.Static;

import android.media.AudioManager;
import android.media.MediaPlayer;

import com.example.minhquan.a14110162mp3.Singleton.MediaPlayerStatic;

import java.io.IOException;

/**
 * Created by MinhQuan on 5/30/2017.
 */

public class LocalMP extends MediaPlayerStatic {

    public static void modify(String path)
    {
        mediaPlayer = new MediaPlayer();
        try {
            check = STOP;
            mediaPlayer.setDataSource(path);
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.prepare();
            setDuration(mediaPlayer.getDuration());
            // duration = mediaPlayer.getDuration();
            // mediaPlayer.release();
            mediaPlayer.setOnCompletionListener(MediaPlayerStatic.getInstance());
            checkEnd = true;

        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}
