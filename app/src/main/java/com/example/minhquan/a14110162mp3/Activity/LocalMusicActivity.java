package com.example.minhquan.a14110162mp3.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.minhquan.a14110162mp3.Adapter.SongAdapter;
import com.example.minhquan.a14110162mp3.Model.Song;
import com.example.minhquan.a14110162mp3.R;
import com.example.minhquan.a14110162mp3.Static.Playlist;

import java.util.ArrayList;

public class LocalMusicActivity extends AppCompatActivity {

    private ListView songView;
    private SongAdapter songAdapter;
    private ArrayList<Song> songList;
    Toolbar tbName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_music);
        songView = (ListView) findViewById(R.id.song_list);
        songList = new ArrayList<>();
        Playlist.getLocalSongList("/Music",songList);
        songAdapter = new SongAdapter(songList,this,R.layout.item_song);
        songView.setAdapter(songAdapter);
        songView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent myIntent = new Intent(LocalMusicActivity.this,PlaySongActivity.class);
                myIntent.putExtra("songList",songList);
                myIntent.putExtra("position",position);
                startActivity(myIntent);
            }
        });

        tbName = (Toolbar) findViewById(R.id.tbName);
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
}
