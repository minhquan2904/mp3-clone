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
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.minhquan.a14110162mp3.Activity.LocalDownloadActivity;
import com.example.minhquan.a14110162mp3.Activity.LocalMusicActivity;
import com.example.minhquan.a14110162mp3.Activity.PlaySongActivity;
import com.example.minhquan.a14110162mp3.Adapter.SongAdapter;
import com.example.minhquan.a14110162mp3.Model.Song;
import com.example.minhquan.a14110162mp3.R;
import com.example.minhquan.a14110162mp3.Static.Playlist;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;

public class FragmentLocal extends Fragment {

    public FragmentLocal() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    LinearLayout lnDownload,lnMusic;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_fragment_local, container, false);

        //set ln download
        lnDownload = (LinearLayout) view.findViewById(R.id.lnDownload);
        lnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(view.getContext(), LocalDownloadActivity.class);
                startActivity(myIntent);
            }
        });

        //set ln music
        lnMusic = (LinearLayout) view.findViewById(R.id.lnMusic);
        lnMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(view.getContext(), LocalMusicActivity.class);
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


}
