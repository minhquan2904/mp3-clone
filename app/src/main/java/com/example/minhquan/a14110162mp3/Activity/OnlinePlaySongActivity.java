package com.example.minhquan.a14110162mp3.Activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.minhquan.a14110162mp3.Database.DownloadTask;
import com.example.minhquan.a14110162mp3.Model.OnlineSong;
import com.example.minhquan.a14110162mp3.Model.Song;
import com.example.minhquan.a14110162mp3.R;
import com.example.minhquan.a14110162mp3.Singleton.MediaPlayerStatic;
import com.example.minhquan.a14110162mp3.Static.OnlineMP;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

public class OnlinePlaySongActivity extends AppCompatActivity implements View.OnClickListener,SeekBar.OnSeekBarChangeListener {
    ArrayList<OnlineSong> songList;
    Animation anim;
    int position,cur;
    TextView txtTitle,txtArtist,txtTimeTotal,txtTimeSong;
    OnlineSong songToDisplay;
    OnlineMP onlineMP;
    ImageButton btnPlay,btnPrev,btnNext,btn_shuffle,btn_repeat,btn_download;
    ImageView imgDisc;
    Toolbar tbName;
    SeekBar skBar;

    public int isUpdate = 1;
    public boolean isRunning,isShuffle = false,isRepeat = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_play_song);

        songList = (ArrayList<OnlineSong>) getIntent().getSerializableExtra("onlineSongs");
        position = (int) getIntent().getSerializableExtra("position");
        onlineMP = new OnlineMP();
        songToDisplay = songList.get(position);
        onlineMP.setOnCompletionListener(new OnlineMP.OnCompletionListener() {
            @Override
            public void OnComplete() {
                next();
            }
        });
        init();
        initSong();
        // OnlineMP.setOnCompletionListener(this);
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

        //OnlineMP.reset();
        // OnlineMP.setOnCompletionListener(this);
        txtTitle.setText(songToDisplay.getTitle());
        txtArtist.setText(songToDisplay.getArtist());
        String path = songToDisplay.getUrl();
        //set bitmap
        if(songToDisplay.getAvt() != null)
        {
            Picasso.with(this).load(songToDisplay.getAvt()).fit().into(imgDisc);
        }
        else
        {
            imgDisc.setImageResource(R.drawable.ic_launcher);
        }
        //Play music
        if(onlineMP.checkRunning() == 1)
        {
            OnlineMP.stop();
        }// is Running another song ==> stop

        onlineMP.onlineModify(path);
      //  OnlineMP.play();


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
        //cur = OnlineMP.getTimeCurrent();


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
        btn_download    = (ImageButton) findViewById(R.id.btn_download) ;
        tbName          = (Toolbar) findViewById(R.id.tbName);
        skBar           = (SeekBar) findViewById(R.id.skBar);
        imgDisc         = (ImageView) findViewById(R.id.imgDisc);

        btnPlay.setOnClickListener(this);
        btn_shuffle.setOnClickListener(this);
        btn_repeat.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        btnPrev.setOnClickListener(this);
        btn_download.setOnClickListener(this);
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
                txtTimeSong.setText(formatTime.format(onlineMP.getTimeCurrent()));
                skBar.setProgress(onlineMP.getTimeCurrent());
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

        int time = onlineMP.getTimeDuration();
        txtTimeTotal.setText(formatTime.format(time));
        skBar.setMax(time);

    }





    @Override
    public void onClick(View v) {
        switch (v.getId()){
            // check button
            case R.id.btnPlay:
                if(OnlineMP.checkRunning() == 1) // isRunning ==> pause
                {
                    btnPlay.setImageResource(R.drawable.ic_pause);
                    OnlineMP.pause();
                    imgDisc.clearAnimation();
                }
                else
                {
                    btnPlay.setImageResource(R.drawable.ic_play);
                    OnlineMP.play();
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
            case R.id.btn_download:
                DownloadSong();
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
//        cur = OnlineMP.getTimeCurrent();
//        if(cur!= progress && cur!= 0)
//            OnlineMP.seekTo(skBar.getProgress());
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

        OnlineMP.seekTo(skBar.getProgress());
    }

    private void DownloadSong()
    {
        ProgressDialog mProgressDialog;

        // instantiate it within the onCreate method
        mProgressDialog = new ProgressDialog(OnlinePlaySongActivity.this);
        mProgressDialog.setMessage("Downloading song");
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.setCancelable(true);

        final DownloadTask downloadTask = new DownloadTask(OnlinePlaySongActivity.this,mProgressDialog,songToDisplay.getTitle());

        downloadTask.execute(songToDisplay.getUrl());

        mProgressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                downloadTask.cancel(true);
            }
        });


    }
//    @Override
//    public void OnEndMusic() {
////        if(isRepeat)
////        {
////            position --;
////            isRepeat = true;
////        }
////        OnlineMP.reset();
//        next();
//    }
}
