package com.example.minhquan.a14110162mp3.Activity.Fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.minhquan.a14110162mp3.Activity.PlaySongActivity;
import com.example.minhquan.a14110162mp3.Adapter.SongAdapter;
import com.example.minhquan.a14110162mp3.Model.Song;
import com.example.minhquan.a14110162mp3.R;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;

public class FragmentLocal extends Fragment {
    private ArrayList<Song> songList;
    private ListView songView;
    private SongAdapter songAdapter;
    public FragmentLocal() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_local, container, false);

        songView = (ListView) view.findViewById(R.id.song_list);
        songList = new ArrayList<>();
        getSongList();
        songAdapter = new SongAdapter(songList,getContext(),R.layout.item_song);

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

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
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
