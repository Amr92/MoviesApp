package com.example.moviesapp.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviesapp.Models.MovieImagesModel;
import com.example.moviesapp.Models.PersonImagesModel;
import com.example.moviesapp.R;
import com.example.moviesapp.ui.ImageViewer;

import java.util.List;

public class MoviesImagesAdapter extends RecyclerView.Adapter<MoviesImagesAdapter.KnownForVH> {

    private List<MovieImagesModel.MovieImagesBackdrops> movieImagesList;
    private Context context;

    public MoviesImagesAdapter(List<MovieImagesModel.MovieImagesBackdrops> movieImagesList, Context context) {
        this.movieImagesList = movieImagesList;
        this.context = context;
    }

    @NonNull
    @Override
    public KnownForVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rec_profile_images,parent,false);
        return new KnownForVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final KnownForVH holder, final int position) {

        Glide.with(context).load("https://image.tmdb.org/t/p/w500" + movieImagesList.get(position).getFile_path()).into(holder.profileImageView);

        holder.profileImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, ImageViewer.class);
                ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context,
                        holder.profileImageView, ViewCompat.getTransitionName(holder.profileImageView));
                intent.putExtra("image_url",movieImagesList.get(position).getFile_path());
                context.startActivity(intent,compat.toBundle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieImagesList.size();
    }

    public class KnownForVH extends RecyclerView.ViewHolder{

       AppCompatImageView profileImageView;

        public KnownForVH(@NonNull View itemView) {
            super(itemView);

            profileImageView = itemView.findViewById(R.id.profile_images);
        }
    }
}
