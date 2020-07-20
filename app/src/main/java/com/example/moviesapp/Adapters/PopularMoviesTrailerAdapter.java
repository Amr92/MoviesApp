package com.example.moviesapp.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.codewaves.youtubethumbnailview.ThumbnailLoadingListener;
import com.codewaves.youtubethumbnailview.ThumbnailView;
import com.example.moviesapp.Models.PopularMoviesTrailerModel;
import com.example.moviesapp.Models.TopRatedTvTrailerModel;
import com.example.moviesapp.R;
import com.example.moviesapp.ui.VideoPlay;

import java.util.ArrayList;
import java.util.List;

public class PopularMoviesTrailerAdapter extends RecyclerView.Adapter<PopularMoviesTrailerAdapter.TrailerViewHolder> {

    private List<PopularMoviesTrailerModel.ResultsBean> trailerList;
    private Activity activity;

    public PopularMoviesTrailerAdapter(List<PopularMoviesTrailerModel.ResultsBean> trailerList, Activity activity) {
        this.trailerList = trailerList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public TrailerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.trailer_item_rec,parent,false);
        return new TrailerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final TrailerViewHolder holder, int position) {

        holder.trailerTitle.setText(trailerList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return trailerList.size();
    }

    public class TrailerViewHolder extends RecyclerView.ViewHolder{

        TextView trailerTitle;
        AppCompatImageView trailerImage;

        public TrailerViewHolder(@NonNull View itemView) {
            super(itemView);

            trailerTitle = itemView.findViewById(R.id.video_title);
            trailerImage = itemView.findViewById(R.id.video_image_view);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        PopularMoviesTrailerModel.ResultsBean model = trailerList.get(position);

                        String videoId = model.getKey();
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + videoId));
                        intent.putExtra("VIDEO_ID",videoId);
                        activity.startActivity(intent);
                    }
                }
            });


        }
    }
}
