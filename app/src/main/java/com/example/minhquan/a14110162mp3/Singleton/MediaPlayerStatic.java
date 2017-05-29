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
    public static final int PLAYER_IDLE = -1;
    public static final int PLAYER_PLAY = 1;
    public static final int PLAYER_PAUSE = 2;

    public static int check;
    static boolean checkEnd;
    public static int duration;


    public MediaPlayerStatic() {


    }

    public static void modify(String path)
    {
        mediaPlayer = new MediaPlayer();
        try {
            check = PLAYER_IDLE;
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
        MediaPlayerStatic.check = PLAYER_IDLE;
    }
    public static int checkRunning(){return check;}
    public static  void play() {
        if (check == PLAYER_IDLE || check == PLAYER_PAUSE) {
            check = PLAYER_PLAY;
            mediaPlayer.start();
        }
    }
    public  static  void stop() {
        if (check == PLAYER_PLAY || check == PLAYER_PAUSE) {
            check = PLAYER_IDLE;
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    public static void pause() {
        if (check == PLAYER_PLAY) {
            mediaPlayer.pause();
            check = PLAYER_PAUSE;
        }
    }

    public static int getTimeCurrent() {
        if (check != PLAYER_IDLE) {
            return mediaPlayer.getCurrentPosition() ;
        } else
            return 0;
    }
    public interface OnCompletionListener{
        void OnEndMusic();
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
            onCompletionListener.OnEndMusic();
           // checkEnd = false;
        }

    }
    public static void setOnCompletionListener(MediaPlayerStatic.OnCompletionListener onCompletionListener){
        MediaPlayerStatic.onCompletionListener = onCompletionListener;
    }
}
