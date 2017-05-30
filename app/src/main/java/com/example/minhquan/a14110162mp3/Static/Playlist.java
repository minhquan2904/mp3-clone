package com.example.minhquan.a14110162mp3.Static;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Picture;
import android.graphics.drawable.PictureDrawable;
import android.media.MediaMetadataRetriever;
import android.os.Environment;
import android.util.Log;

import com.example.minhquan.a14110162mp3.Model.Song;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by MinhQuan on 5/30/2017.
 */

public class Playlist {
    public static void getLocalSongList(String type,ArrayList<Song> songListTemp) {
        //Get playlist in SD card
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + type;

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
                songListTemp.add(song);


            }
        }
    }


    //Convert Picture to Bitmap
    private static Bitmap pictureDrawable2Bitmap(Picture picture) {
        PictureDrawable pd = new PictureDrawable(picture);
        Bitmap bitmap = Bitmap.createBitmap(pd.getIntrinsicWidth(), pd.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawPicture(pd.getPicture());
        return bitmap;
    }


}
