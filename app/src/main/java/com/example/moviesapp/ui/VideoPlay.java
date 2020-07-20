package com.example.moviesapp.ui;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codewaves.youtubethumbnailview.ThumbnailLoader;
import com.codewaves.youtubethumbnailview.ThumbnailView;
import com.example.moviesapp.Classes.FullScreenHelper;
import com.example.moviesapp.Models.PopularMoviesTrailerModel;
import com.example.moviesapp.R;
import com.pierfrancescosoffritti.androidyoutubeplayer.BuildConfig;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerFullScreenListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideoPlay extends AppCompatActivity {

    @BindView(R.id.video_progress_bar)
    ProgressBar videoProgressBar;
    @BindView(R.id.video_thumbnail_view)
    ThumbnailView videoThumbnailView;
    @BindView(R.id.video_player_view)
    YouTubePlayerView videoPlayerView;
    @BindView(R.id.video_player_layout)
    RelativeLayout videoPlayerLayout;
    @BindView(R.id.video_player_title)
    AppCompatTextView videoPlayerTitle;
    @BindView(R.id.other_videos_recycler_view)
    RecyclerView otherVideosRecyclerView;
    @BindView(R.id.no_results_found)
    AppCompatTextView noResultsFound;

    private FullScreenHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play);
        ButterKnife.bind(this);

        Intent intent = getIntent();

        ThumbnailLoader.initialize();

        helper = new FullScreenHelper(this);

        otherVideosRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        videoProgressBar.getIndeterminateDrawable().setColorFilter(0XFFFFFFFF, PorterDuff.Mode.MULTIPLY);

        if(intent != null && intent.getExtras() != null){
            ArrayList<PopularMoviesTrailerModel.ResultsBean> videosList = intent.getExtras().getParcelableArrayList("video");
            int position = Integer.parseInt(intent.getExtras().getString("position"));

            if(videosList != null && videosList.size() > 0){
                final String videoId = videosList.get(position).getKey();
                String videoTitle = videosList.get(position).getName();

                if(videoTitle != null){
                    videoPlayerTitle.setText(videoTitle);
                }
                if(videoId != null){
                    String baseUrl = "https://www.youtube.com/watch?v=";
                    videoThumbnailView.loadThumbnail(baseUrl + videoId);
                    videoPlayerView.initialize(new AbstractYouTubePlayerListener() {
                        @Override
                        public void onReady(YouTubePlayer youTubePlayer) {

                            videoThumbnailView.setVisibility(View.GONE);
                            videoProgressBar.setVisibility(View.GONE);
                            videoPlayerView.setVisibility(View.VISIBLE);

                            if(getLifecycle().getCurrentState() == Lifecycle.State.RESUMED){
                                youTubePlayer.loadVideo(videoId,0);
                            }else {
                                youTubePlayer.cueVideo(videoId,0);
                            }
                        }

                    });

                    videoPlayerView.addFullScreenListener(new YouTubePlayerFullScreenListener() {
                        @Override
                        public void onYouTubePlayerEnterFullScreen() {

                            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
                            helper.enterFullScreen();
                        }

                        @Override
                        public void onYouTubePlayerExitFullScreen() {
                            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
                            helper.exitFullScreen();
                        }
                    });

                }
            }
        }

    }
}
