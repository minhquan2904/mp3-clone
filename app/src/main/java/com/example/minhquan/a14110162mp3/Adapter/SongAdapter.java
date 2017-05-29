package com.example.minhquan.a14110162mp3.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.minhquan.a14110162mp3.Model.Song;
import com.example.minhquan.a14110162mp3.R;

import java.util.ArrayList;

/**
 * Created by MinhQuan on 5/28/2017.
 */

public class SongAdapter extends BaseAdapter {
    private ArrayList<Song> songs ;
    private Context mContext;
    private int layout;

    public SongAdapter(ArrayList<Song> songs, Context mContext, int layout) {
        this.songs = songs;
        this.mContext = mContext;
        this.layout = layout;
    }
    private class ViewHolder {
        TextView txtTitle;
        TextView txtArtist;
        ImageView imgAvt;
    }
    @Override
    public int getCount() {
        return songs.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(convertView == null)
        {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_song,parent,false);

            holder.txtTitle = (TextView) convertView.findViewById(R.id.txtTitle);
            holder.txtArtist = (TextView) convertView.findViewById(R.id.txtArtist);
            holder.imgAvt = (ImageView) convertView.findViewById(R.id.imgAvt);

            convertView.setTag(holder);
        }
        else
        {
            holder = (SongAdapter.ViewHolder) convertView.getTag();
        }
        Song songToDisplay = songs.get(position);
        holder.txtTitle.setText(songToDisplay.getTitle());
        holder.txtArtist.setText(songToDisplay.getArtist());

        if(songToDisplay.getThumnbail()!= null)
        {
            holder.imgAvt.setImageBitmap(songToDisplay.getThumnbail());
        }
        else
        {
            holder.imgAvt.setImageResource(R.drawable.ic_launcher);
        }
        return convertView;



//        Song songToDisplay = songs.get(position);
//        View listItem;
//
//        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//        if(convertView == null)
//        {
//            listItem = inflater.inflate(R.layout.item_song,parent,false);
//        }
//        else
//        {
//            listItem = convertView;
//        }
//        //
//        TextView txtTitle = (TextView) listItem.findViewById(R.id.txtTitle);
//        TextView txtArtist = (TextView) listItem.findViewById(R.id.txtArtist);
//        CircleImageView imgAvt = (CircleImageView) listItem.findViewById(R.id.imgAvt);
//
//        //
//        txtTitle.setText(songToDisplay.getTitle());
//
//        txtArtist.setText(songToDisplay.getArtist());
//
//        if(songToDisplay.getThumnbail() != null)
//        {
//            imgAvt.setImageBitmap(songToDisplay.getThumnbail());
//        }
//        else
//        {
//            imgAvt.setImageResource(R.drawable.ic_launcher);
//        }

    }
}
