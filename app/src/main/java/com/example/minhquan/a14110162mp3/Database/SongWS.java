package com.example.minhquan.a14110162mp3.Database;

import android.util.Log;

import com.example.minhquan.a14110162mp3.Model.OnlineSong;
import com.example.minhquan.a14110162mp3.Model.Song;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by MinhQuan on 5/29/2017.
 */

public class SongWS {

    private Webservices webservices;

    public SongWS(int page)
    {
        webservices = new Webservices("/song/get-all/"+page);
    }

    public ArrayList<OnlineSong> getSong(){

        webservices.execute();

        ArrayList<OnlineSong> songs = new ArrayList<>();
        try {
            String data = webservices.get();
            JSONArray array = new JSONArray(data);
            int count = array.length();

            for(int i =0;i<count;i++)
            {
                JSONObject object = array.getJSONObject(i);

                OnlineSong song = new OnlineSong();

                song.setArtist(object.getString("artist"));
                song.setTitle(object.getString("title"));
                song.setUrl(Webservices.getURL()+"music/"+object.getString("source")+".mp3");
                song.setAvt(Webservices.getURL() +"image/"+object.getString("thumbnail")+".jpg");
                Log.d("URL -----",song.getUrl());
                songs.add(song);

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return songs;
    }
}
