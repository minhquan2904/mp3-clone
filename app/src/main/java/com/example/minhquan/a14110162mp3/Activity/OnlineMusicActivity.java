package com.example.minhquan.a14110162mp3.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.minhquan.a14110162mp3.Adapter.OnlineSongAdapter;
import com.example.minhquan.a14110162mp3.Database.SongWS;
import com.example.minhquan.a14110162mp3.Model.OnlineSong;
import com.example.minhquan.a14110162mp3.R;

import java.util.ArrayList;

public class OnlineMusicActivity extends AppCompatActivity implements AbsListView.OnScrollListener {

    ArrayList<OnlineSong> onlineSongs;
    ListView lvOnlineMusic;
    Toolbar tbName;
    SongWS songWS;
    OnlineSongAdapter onlineSongAdapter;
    int page = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_music);

        init();

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

        onlineSongs = new ArrayList<>();
        songWS = new SongWS(page);
        onlineSongs = songWS.getSong();

        onlineSongAdapter = new OnlineSongAdapter(onlineSongs,this);
        lvOnlineMusic.setAdapter(onlineSongAdapter);

        lvOnlineMusic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent myIntent = new Intent(OnlineMusicActivity.this,OnlinePlaySongActivity.class);
                myIntent.putExtra("onlineSongs",onlineSongs);
                myIntent.putExtra("position",position);
                startActivity(myIntent);
            }
        });

        lvOnlineMusic.setOnScrollListener(this);

    }

    private void init() {
        tbName          = (Toolbar) findViewById(R.id.tbName);
        lvOnlineMusic   = (ListView) findViewById(R.id.lvOnlineMusic);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if(firstVisibleItem +visibleItemCount == totalItemCount && totalItemCount != 0)
        {
            page ++;
            songWS = new SongWS(page);
            onlineSongs.addAll(songWS.getSong());

        }
    }
}
