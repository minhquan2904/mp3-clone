package com.example.minhquan.a14110162mp3.Activity;

import android.media.AudioManager;
import android.media.MediaMetadata;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.minhquan.a14110162mp3.Model.Song;
import com.example.minhquan.a14110162mp3.R;

import com.example.minhquan.a14110162mp3.Static.LocalMP;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

public class PlaySongActivity extends AppCompatActivity implements View.OnClickListener,SeekBar.OnSeekBarChangeListener {
    ArrayList<Song> songList;
    Animation anim;
    int position,cur;
    TextView txtTitle,txtArtist,txtTimeTotal,txtTimeSong;
    Song songToDisplay;
    LocalMP LocalMP;
    ImageButton btnPlay,btnPrev,btnNext,btn_shuffle,btn_repeat;
    ImageView imgDisc;
    Toolbar tbName;
    SeekBar skBar;
    public int isUpdate = 1;
    public boolean isRunning,isShuffle = false,isRepeat = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_song);

        songList = (ArrayList<Song>) getIntent().getSerializableExtra("songList");
        position = (int) getIntent().getSerializableExtra("position");
        songToDisplay = songList.get(position);
        LocalMP.setOnCompletionListener(new LocalMP.OnCompletionListener() {
            @Override
            public void OnComplete() {

                next();
            }
        });
        init();
        initSong();
       // LocalMP.setOnCompletionListener(this);
        //Set back arrow
        setSupportActionBar(tbName);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        tbName.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });






    }

    public void initSong() {

        //LocalMP.reset();
       // LocalMP.setOnCompletionListener(this);
        txtTitle.setText(songToDisplay.getTitle());
        txtArtist.setText(songToDisplay.getArtist());
        String path = songToDisplay.getUrl();
        //set bitmap
        if(songToDisplay.getThumnbail() != null)
        {
            imgDisc.setImageBitmap(songToDisplay.getThumnbail());
        }
        else
        {
            imgDisc.setImageResource(R.drawable.ic_launcher);
        }
        //Play music
        if(LocalMP.checkRunning() == 1)
        {
            LocalMP.stop();
        }// is Running another song ==> stop

        LocalMP.modify(path);
        LocalMP.play();


        //set timetotal
        setTimeTotal();
        isRunning = true;

        anim = AnimationUtils.loadAnimation(this,R.anim.rotate_anim);
        imgDisc.setAnimation(anim);
        //update time Song
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (isRunning)
                {
                    Message message = new Message();
                    message.what = isUpdate;
                    handler.sendMessage(message);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        //cur = LocalMP.getTimeCurrent();


    }
    public void init()
    {
        txtTitle        = (TextView) findViewById(R.id.txtTitle);
        txtArtist       = (TextView) findViewById(R.id.txtArtist);
        txtTimeTotal    = (TextView) findViewById(R.id.txtTimeTotal);
        txtTimeSong     = (TextView) findViewById(R.id.txtTimeSong);
        btnPlay         = (ImageButton) findViewById(R.id.btnPlay);
        btn_shuffle     = (ImageButton) findViewById(R.id.btn_shuffle);
        btn_repeat      = (ImageButton) findViewById(R.id.btn_repeat);
        btnNext         = (ImageButton) findViewById(R.id.btnNext);
        btnPrev         = (ImageButton) findViewById(R.id.btnPrev);
        tbName          = (Toolbar) findViewById(R.id.tbName);
        skBar           = (SeekBar) findViewById(R.id.skBar);
        imgDisc         = (ImageView) findViewById(R.id.imgDisc);

        btnPlay.setOnClickListener(this);
        btn_shuffle.setOnClickListener(this);
        btn_repeat.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        btnPrev.setOnClickListener(this);
        skBar.setOnSeekBarChangeListener(this);


    }
    //handler
    public Handler handler = new Handler()
    {
        @Override
        public String toString() {
            return super.toString();
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 1)
            {
                SimpleDateFormat formatTime = new SimpleDateFormat("mm:ss");
                txtTimeSong.setText(formatTime.format(LocalMP.getTimeCurrent()));
                skBar.setProgress(LocalMP.getTimeCurrent());
            }
        }

        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
        }

        @Override
        public String getMessageName(Message message) {
            return super.getMessageName(message);
        }

        @Override
        public boolean sendMessageAtTime(Message msg, long uptimeMillis) {
            return super.sendMessageAtTime(msg, uptimeMillis);
        }
    };
    public void setTimeTotal() {
        SimpleDateFormat formatTime = new SimpleDateFormat("mm:ss");
        txtTimeTotal.setText(formatTime.format(LocalMP.getDuration()));
        skBar.setMax(LocalMP.getDuration());

    }





    @Override
    public void onClick(View v) {
        switch (v.getId()){
            // check button
            case R.id.btnPlay:
                if(LocalMP.checkRunning() == 1) // isRunning ==> pause
                {
                    btnPlay.setImageResource(R.drawable.ic_pause);
                    LocalMP.pause();
                    imgDisc.clearAnimation();
                }
                else
                {
                    btnPlay.setImageResource(R.drawable.ic_play);
                    LocalMP.play();
                    imgDisc.setAnimation(anim);
                }
                break;
            case R.id.btn_shuffle:
                btn_shuffle.setSelected(!btn_shuffle.isSelected());
                if(btn_repeat.isSelected() == true)
                {
                    btn_repeat.setSelected(!btn_repeat.isSelected());
                }
                if(btn_shuffle.isSelected())
                {
                    isShuffle = true;
                    isRepeat = false;
                }
                else {
                    isShuffle= false;
                }
                break;
            case R.id.btn_repeat:
                btn_repeat.setSelected(!btn_repeat.isSelected());
                if(btn_shuffle.isSelected() == true)
                {
                    btn_shuffle.setSelected(!btn_shuffle.isSelected());
                }
                if(btn_repeat.isSelected())
                {

                    isShuffle = false;
                    isRepeat = true;

                }
                else
                {
                    isRepeat = false;
                }
                break;
            case R.id.btnNext:
                next();
                break;
            case R.id.btnPrev:
                previous();
                break;
        }



    }
    public void next()
    {
        if(isShuffle)
        {
            Random random = new Random();
            position = random.nextInt(songList.size()-1);
        }
        else
        {

            if(isRepeat)
            {
                position --;
            }
            position ++;
            if(position > songList.size() -1)
            {
                position = 0;
            }
        }
        songToDisplay = songList.get(position);
        initSong();

    }
    public void previous()
    {
        if(isShuffle)
        {
            Random random = new Random();
            position = random.nextInt(songList.size()-1);
        }
        else
        {
            position -- ;
            if(position < 0)
            {
                position = songList.size() - 1;
            }

        }
        songToDisplay = songList.get(position);
        initSong();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//        cur = LocalMP.getTimeCurrent();
//        if(cur!= progress && cur!= 0)
//            LocalMP.seekTo(skBar.getProgress());
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

           LocalMP.seekTo(skBar.getProgress());
    }


}
