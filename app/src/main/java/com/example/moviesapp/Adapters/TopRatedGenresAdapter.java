package com.example.moviesapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviesapp.Models.PopularMovieDetailsModel;
import com.example.moviesapp.Models.TopRatedMovieDetailsModel;
import com.example.moviesapp.R;

import java.util.List;

public class TopRatedGenresAdapter extends RecyclerView.Adapter<TopRatedGenresAdapter.GenresViewHolder> {

    private List<TopRatedMovieDetailsModel.GenresBean> genresList;
    private Context context;

    public TopRatedGenresAdapter(List<TopRatedMovieDetailsModel.GenresBean> genresList, Context context) {
        this.genresList = genresList;
        this.context = context;
    }

    @NonNull
    @Override
    public GenresViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_genres,parent,false);
        return new GenresViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull GenresViewHolder holder, int position) {

        holder.genresTitle.setText(genresList.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return genresList.size();
    }

    public class GenresViewHolder extends RecyclerView.ViewHolder{

        TextView genresTitle;

        public GenresViewHolder(@NonNull View itemView) {
            super(itemView);

            genresTitle = itemView.findViewById(R.id.genres_title);
        }
    }
}
