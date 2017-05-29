package com.example.minhquan.a14110162mp3.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.minhquan.a14110162mp3.Adapter.SongAdapter;
import com.example.minhquan.a14110162mp3.Model.Song;
import com.example.minhquan.a14110162mp3.R;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;

public class LocalSongActivity extends AppCompatActivity {
    private ArrayList<Song> songList;
    private ListView songView;
    private SongAdapter songAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_song);

        /*
        songView = (ListView)findViewById(R.id.song_list);
        songList = new ArrayList<>();
        getSongList();
        songAdapter = new SongAdapter(songList,this,R.layout.item_song);

        songView.setAdapter(songAdapter);

        songView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent myIntent = new Intent(view.getContext(),PlaySongActivity.class);
                myIntent.putExtra("songList",songList);
                myIntent.putExtra("position",position);
                startActivity(myIntent);
            }
        });

        */
    }

    public void getSongList() {
        //Get playlist in SD card
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Download";

        File file = new File(path);
        File[] files = file.listFiles(); // get all file
        Log.d("----Kt-----", String.valueOf(files.length));
        for (int i = 0; i < files.length; i++) {
            String name = files[i].getName();

            //check type
            if (name.endsWith(".mp3")) // accept only mp3
            {
                String filePath = files[i].getAbsolutePath();
                //Hỗ trợ trao đổi dữ liệu với đa phương tiện với các thiết bị khác.
                MediaMetadataRetriever retriever = new MediaMetadataRetriever();
                Song song = new Song();
                retriever.setDataSource(filePath);

                //modify
                song.setUrl(filePath);
                song.setTitle(retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE));
                song.setArtist(retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST));
                MediaMetadataRetriever metadataRetriever = new MediaMetadataRetriever();
                byte[] bytes = metadataRetriever.getEmbeddedPicture();

                if (bytes != null) {
                    InputStream is = new ByteArrayInputStream(metadataRetriever.getEmbeddedPicture());
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    song.setThumnbail(bitmap);
                } else {
                    song.setThumnbail(null);
                }
                metadataRetriever.release();
                retriever.release();
                songList.add(song);


            }
        }
    }
}
