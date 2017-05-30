package com.example.minhquan.a14110162mp3.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.minhquan.a14110162mp3.Model.OnlineSong;
import com.example.minhquan.a14110162mp3.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by MinhQuan on 5/30/2017.
 */

public class OnlineSongAdapter extends BaseAdapter {

    private ArrayList<OnlineSong> onlineSongs;
    private Context mContext;
    OnlineSong songToDisplay;

    public OnlineSongAdapter(ArrayList<OnlineSong> onlineSongs, Context mContext) {
        this.onlineSongs = onlineSongs;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return onlineSongs.size();
    }

    @Override
    public Object getItem(int position) {
        return onlineSongs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        OnlineSongAdapter.ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(convertView == null)
        {
            holder = new OnlineSongAdapter.ViewHolder();
            convertView = inflater.inflate(R.layout.item_song,parent,false);

            holder.txtTitle = (TextView) convertView.findViewById(R.id.txtTitle);
            holder.txtArtist = (TextView) convertView.findViewById(R.id.txtArtist);
            holder.imgAvt = (ImageView) convertView.findViewById(R.id.imgAvt);

            convertView.setTag(holder);
        }
        else
        {
            holder = (OnlineSongAdapter.ViewHolder) convertView.getTag();
        }


         songToDisplay = onlineSongs.get(position);

        holder.txtTitle.setText(songToDisplay.getTitle());
        holder.txtArtist.setText(songToDisplay.getArtist());

        if(songToDisplay.getAvt() != null)
        {
            Picasso.with(mContext).load(songToDisplay.getAvt()).fit().into(((ViewHolder) holder).imgAvt, new Callback() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onError() {
                    Toast.makeText(mContext,"Can't load image",Toast.LENGTH_SHORT).show();
                }
            });
        }
        else
        {
            holder.imgAvt.setImageResource(R.drawable.ic_launcher);
        }
        return convertView;

    }

    public class ViewHolder {
        TextView txtTitle;
        TextView txtArtist;
        ImageView imgAvt;
    }
}
