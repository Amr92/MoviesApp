package com.example.moviesapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviesapp.Models.NowPlayingMoviesModel;
import com.example.moviesapp.Models.PopularMoviesModel;
import com.example.moviesapp.R;
import com.flaviofaria.kenburnsview.KenBurnsView;

import java.util.List;

public class NowPlayingMoviesAdapter extends RecyclerView.Adapter<NowPlayingMoviesAdapter.NowPlayingViewHolder> {

    private List<NowPlayingMoviesModel.ResultsBean> results;
    private Context context;

    public NowPlayingMoviesAdapter(List<NowPlayingMoviesModel.ResultsBean> results, Context context) {
        this.results = results;
        this.context = context;
    }

    @NonNull
    @Override
    public NowPlayingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rec_movies,parent,false);
        NowPlayingViewHolder viewHolder = new NowPlayingViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NowPlayingViewHolder holder, int position) {

         holder.posterTitle.setText(results.get(position).getTitle());

        Glide.with(context).load("https://image.tmdb.org/t/p/w500" +results.get(position).getPoster_path()).into(holder.posterImageView);
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class NowPlayingViewHolder extends RecyclerView.ViewHolder{

        KenBurnsView posterImageView;
        AppCompatTextView posterTitle;

        public NowPlayingViewHolder(@NonNull View itemView) {
            super(itemView);

            posterImageView = itemView.findViewById(R.id.poster_image_view);
            posterTitle = itemView.findViewById(R.id.poster_title);

        }

    }
}
