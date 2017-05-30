package com.example.minhquan.a14110162mp3.Static;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.util.Log;

import com.example.minhquan.a14110162mp3.Singleton.MediaPlayerStatic;

import java.io.IOException;

/**
 * Created by MinhQuan on 5/30/2017.
 */

public class OnlineMP extends MediaPlayerStatic implements MediaPlayer.OnCompletionListener {
    public static int onlDuration = 0;
    public static void onlineModify(String path)
    {
        check = STOP;
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mediaPlayer.setDataSource(path);
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnCompletionListener(MediaPlayerStatic.getInstance());

            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    setTimeDuration(mediaPlayer.getDuration());
                    mp.start();

                    if(check == STOP || check == PAUSE)
                    {
                        check=PLAYING;

                    }
                    checkEnd = true;


                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void setTimeDuration(int time)
    {
        onlDuration = time;
        Log.d("DURATION ---","Time :"+onlDuration);
    }

    public static int getTimeDuration()
    {
        return OnlineMP.onlDuration;
    }

}
