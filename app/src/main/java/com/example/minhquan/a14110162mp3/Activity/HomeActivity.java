package com.example.minhquan.a14110162mp3.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;

import com.example.minhquan.a14110162mp3.Activity.Fragment.FragmentLocal;
import com.example.minhquan.a14110162mp3.Activity.Fragment.FragmentOnline;
import com.example.minhquan.a14110162mp3.Adapter.SongAdapter;
import com.example.minhquan.a14110162mp3.Adapter.ViewPagerAdapter;
import com.example.minhquan.a14110162mp3.Model.Song;
import com.example.minhquan.a14110162mp3.R;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private ArrayList<Song> songList;
    private ListView songView;
    private SongAdapter songAdapter;

    private ViewPager viewPagerMain;
    RadioButton buttonLocal;
    RadioButton buttonOnline;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        viewPagerMain = (ViewPager) findViewById(R.id.viewPager_home);
        ViewPagerAdapter viewPagerMainAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerMainAdapter.addFragment(new FragmentLocal(),"Local");
        viewPagerMainAdapter.addFragment(new FragmentOnline(),"Online");
        viewPagerMain.setAdapter(viewPagerMainAdapter);

        buttonLocal = (RadioButton) findViewById(R.id.btn_local);
        buttonOnline = (RadioButton) findViewById(R.id.btn_online);
        viewPagerMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageSelected(int position)
            {
                switch (position){
                case 0:
                    buttonLocal.setChecked(true);
                break;
                case 1:
                    buttonOnline.setChecked(true);
                break;
            }
            }

            @Override
            public void onPageScrollStateChanged(int state) {}

            @Override
            public void onPageScrolled(int position, float arg1, int arg2) {}
        });

        buttonLocal.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                viewPagerMain.setCurrentItem(0,true);
            }
        });
        buttonOnline.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                viewPagerMain.setCurrentItem(1,true);
            }
        });
        // cac gia tri mac dinh
        buttonLocal.setChecked(true);
        viewPagerMain.setCurrentItem(0);

//        songView = (ListView)findViewById(R.id.song_list);
//        songList = new ArrayList<>();
//        getSongList();
//        songAdapter = new SongAdapter(songList,this,R.layout.item_song);
//
//        songView.setAdapter(songAdapter);
//
//        songView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent myIntent = new Intent(view.getContext(),PlaySongActivity.class);
//                myIntent.putExtra("songList",songList);
//                myIntent.putExtra("position",position);
//                startActivity(myIntent);
//            }
//        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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
