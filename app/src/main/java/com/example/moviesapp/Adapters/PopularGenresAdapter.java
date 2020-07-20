package com.example.moviesapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviesapp.Models.PopularMovieDetailsModel;
import com.example.moviesapp.R;

import java.util.List;

public class PopularGenresAdapter extends RecyclerView.Adapter<PopularGenresAdapter.GenresViewHolder> {

    private List<PopularMovieDetailsModel.GenresBean> genresList;
    private Context context;

    public PopularGenresAdapter(List<PopularMovieDetailsModel.GenresBean> genresList, Context context) {
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
