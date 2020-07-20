package com.example.moviesapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviesapp.Models.PopularSimilarMoviesModel;
import com.example.moviesapp.Models.TopRatedSimilarMoviesModel;
import com.example.moviesapp.R;

import java.util.List;

public class TopRatedSimilarMoviesAdapter extends RecyclerView.Adapter<TopRatedSimilarMoviesAdapter.SMViewHolder> {

    private List<TopRatedSimilarMoviesModel.ResultsBean> smList;
    private Context context;

    public TopRatedSimilarMoviesAdapter(List<TopRatedSimilarMoviesModel.ResultsBean> smList, Context context) {
        this.smList = smList;
        this.context = context;
    }

    @NonNull
    @Override
    public SMViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.similar_movies_item_rec,parent,false);
        return new SMViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SMViewHolder holder, int position) {

        holder.posterTitle.setText(smList.get(position).getTitle());
        holder.movieRating.setText(String.valueOf(smList.get(position).getVote_average()));
        Glide.with(context).load("https://image.tmdb.org/t/p/w500" +smList.get(position).getPoster_path()).into(holder.posterImageView);
    }

    @Override
    public int getItemCount() {
        return smList.size();
    }

    public class SMViewHolder extends RecyclerView.ViewHolder{

        private AppCompatImageView posterImageView;
        private AppCompatTextView posterTitle;
        private AppCompatTextView movieRating;

        public SMViewHolder(@NonNull View itemView) {
            super(itemView);

            posterImageView = itemView.findViewById(R.id.similar_movie_poster_image);
            posterTitle = itemView.findViewById(R.id.similar_movie_poster_title);
            movieRating = itemView.findViewById(R.id.similar_movie_rating);
        }
    }
}
