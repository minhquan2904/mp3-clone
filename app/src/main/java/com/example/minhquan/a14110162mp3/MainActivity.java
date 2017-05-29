package com.example.minhquan.a14110162mp3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.minhquan.a14110162mp3.Activity.LocalSongActivity;

public class MainActivity extends AppCompatActivity {
    Button btn_local;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_local = (Button) findViewById(R.id.btn_local);

        btn_local.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), LocalSongActivity.class);
                startActivity(myIntent);
            }
        });
    }
}
