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
import com.example.moviesapp.Models.PopularTvDetailsModel;
import com.example.moviesapp.Models.TopRatedTvDetailsModel;
import com.example.moviesapp.R;

import java.util.List;

public class TopRatedTvSeasonsAdapter extends RecyclerView.Adapter<TopRatedTvSeasonsAdapter.SeasonsViewHolder> {

    private List<TopRatedTvDetailsModel.SeasonsBean> seasonsList;
    private Context context;

    public TopRatedTvSeasonsAdapter(List<TopRatedTvDetailsModel.SeasonsBean> seasonsList, Context context) {
        this.seasonsList = seasonsList;
        this.context = context;
    }

    @NonNull
    @Override
    public SeasonsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.seasons_tv_item_rec,parent,false);
        return new SeasonsViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull SeasonsViewHolder holder, int position) {

        holder.seasonTitle.setText(seasonsList.get(position).getName());
        holder.seasonStartDate.setText(seasonsList.get(position).getAir_date());
        holder.seasonNumEpisodes.setText(String.valueOf(seasonsList.get(position).getEpisode_count()));
        Glide.with(context).load("https://image.tmdb.org/t/p/w500" + seasonsList.get(position).getPoster_path())
                .into(holder.seasonPoster);

    }

    @Override
    public int getItemCount() {
        return seasonsList.size();
    }

    public class SeasonsViewHolder extends RecyclerView.ViewHolder{

        AppCompatTextView seasonTitle,seasonStartDate,seasonNumEpisodes;
        AppCompatImageView seasonPoster;

        public SeasonsViewHolder(@NonNull View itemView) {
            super(itemView);

            seasonTitle = itemView.findViewById(R.id.seasons_tv_poster_title);
            seasonPoster = itemView.findViewById(R.id.seasons_tv_poster_image);
            seasonStartDate = itemView.findViewById(R.id.seasons_tv_start_date);
            seasonNumEpisodes = itemView.findViewById(R.id.seasons_tv_number_episodes);
        }
    }
}
