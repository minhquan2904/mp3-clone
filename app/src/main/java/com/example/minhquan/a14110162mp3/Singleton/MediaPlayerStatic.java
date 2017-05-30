package com.example.minhquan.a14110162mp3.Singleton;

import android.media.AudioManager;
import android.media.MediaPlayer;

import java.io.IOException;

/**
 * Created by MinhQuan on 5/28/2017.
 */

public class MediaPlayerStatic implements MediaPlayer.OnCompletionListener {
    private static final MediaPlayerStatic ourInstance = new MediaPlayerStatic();
    public static MediaPlayerStatic.OnCompletionListener onCompletionListener;
    public static MediaPlayer mediaPlayer;
    public static String path;
    public static final int STOP = -1;
    public static final int PLAYING = 1;
    public static final int PAUSE = 2;

    public static int check;
    protected static boolean checkEnd;
    public static int duration;


    public MediaPlayerStatic() {


    }


    public static MediaPlayerStatic getInstance() {
        return ourInstance;
    }
    public static void setDuration(int duration) {
        MediaPlayerStatic.duration = duration;
    }
    public static int getDuration()
    {
        return duration;
    }
    public static void reset()
    {
        mediaPlayer.reset();
    }
    public static void setRepeat()
    {
        MediaPlayerStatic.check = STOP;
    }
    public static int checkRunning(){return check;}
    public static  void play() {
        if (check == STOP || check == PAUSE) {
            check = PLAYING;
            mediaPlayer.start();
        }
    }
    public  static  void stop() {
        if (check == PLAYING || check == PAUSE) {
            check = STOP;
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    public static void pause() {
        if (check == PLAYING) {
            mediaPlayer.pause();
            check = PAUSE;
        }
    }

    public static int getTimeCurrent() {
        if (check != STOP) {
            return mediaPlayer.getCurrentPosition() ;
        } else
            return 0;
    }
    public interface OnCompletionListener{
        void OnComplete();
    }
    public static void seekTo(int time)
    {
        mediaPlayer.seekTo(time);
    }
    @Override
    public void onCompletion(MediaPlayer mp) {
        //kết thúc bài hát
        if(checkEnd)
        {
            onCompletionListener.OnComplete();
           //checkEnd = false;
        }

    }

    public static void setOnCompletionListener(MediaPlayerStatic.OnCompletionListener onCompletionListener){
        MediaPlayerStatic.onCompletionListener = onCompletionListener;
    }
}
