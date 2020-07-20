package com.example.moviesapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviesapp.Models.PopularSimilarMoviesModel;
import com.example.moviesapp.R;
import com.example.moviesapp.ui.MoviesSearchDetails;
import com.example.moviesapp.ui.SimilarDetails;
import com.flaviofaria.kenburnsview.KenBurnsView;

import java.util.List;

public class PopularSimilarMoviesAdapter extends RecyclerView.Adapter<PopularSimilarMoviesAdapter.SMViewHolder> {

    private List<PopularSimilarMoviesModel.ResultsBean> smList;
    private Context context;

    public PopularSimilarMoviesAdapter(List<PopularSimilarMoviesModel.ResultsBean> smList, Context context) {
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
    public void onBindViewHolder(@NonNull SMViewHolder holder, final int position) {

        holder.posterTitle.setText(smList.get(position).getTitle());
        holder.movieRating.setText(String.valueOf(smList.get(position).getVote_average()));
        Glide.with(context).load("https://image.tmdb.org/t/p/w500" +smList.get(position).getPoster_path()).into(holder.posterImageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context.getApplicationContext(), SimilarDetails.class);
                intent.putExtra("similarMovieId",smList.get(position).getId());
                context.startActivity(intent);
            }
        });
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
