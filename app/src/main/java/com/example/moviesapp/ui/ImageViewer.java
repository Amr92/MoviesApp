package com.example.moviesapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import com.bumptech.glide.Glide;
import com.example.moviesapp.R;
import com.jsibbold.zoomage.ZoomageView;

public class ImageViewer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_viewer);


        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Intent intent = getIntent();

        ZoomageView zoomageView = findViewById(R.id.zoom_image_view);

        if(intent != null && intent.getExtras() != null){

            if (intent.getStringExtra("image_url") != null){

                String url = intent.getStringExtra("image_url");

                Glide.with(this).load( "https://image.tmdb.org/t/p/w500" + url).into(zoomageView);
            }
        }

    }
}
