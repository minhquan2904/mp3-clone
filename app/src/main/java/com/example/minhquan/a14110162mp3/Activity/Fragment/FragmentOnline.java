package com.example.minhquan.a14110162mp3.Activity.Fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.minhquan.a14110162mp3.Activity.HomeActivity;
import com.example.minhquan.a14110162mp3.Activity.LoginActivity;
import com.example.minhquan.a14110162mp3.Activity.OnlineMusicActivity;
import com.example.minhquan.a14110162mp3.Activity.OnlinePlaySongActivity;
import com.example.minhquan.a14110162mp3.Model.OnlineSong;
import com.example.minhquan.a14110162mp3.R;

import java.util.ArrayList;

public class FragmentOnline extends Fragment {


    public FragmentOnline() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    Button btn_demo;
    ArrayList<OnlineSong> onlineSongs;
    LinearLayout lnAllMusic;
    RelativeLayout lnLogin;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_fragment_online, container, false);
        btn_demo = (Button) view.findViewById(R.id.btn_demo);
        onlineSongs = new ArrayList<>();

//        btn_demo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String url = onlineSongs.get(0).getUrl();
//                MediaPlayer mediaPlayer = new MediaPlayer();
//                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//                try {
//                    mediaPlayer.setDataSource(url);
//                    mediaPlayer.prepareAsync();
//                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//                        @Override
//                        public void onPrepared(MediaPlayer mp) {
//                            mp.start();
//                        }
//                    });
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        });

        //all music
        lnAllMusic = (LinearLayout) view.findViewById(R.id.lnAllMusic);
        lnAllMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getContext(), OnlineMusicActivity.class);
                startActivity(myIntent);
            }
        });

        lnLogin = (RelativeLayout) view.findViewById(R.id.lnLogin);
        lnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getContext(), LoginActivity.class);
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
