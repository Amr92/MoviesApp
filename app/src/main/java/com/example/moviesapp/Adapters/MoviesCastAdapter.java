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
import com.example.moviesapp.Models.MoviesCastModel;
import com.example.moviesapp.R;
import com.example.moviesapp.ui.PersonSearchDetails;
import com.google.gson.Gson;

import java.util.List;

public class MoviesCastAdapter extends RecyclerView.Adapter<MoviesCastAdapter.MoviesCastVH> {

    private List<MoviesCastModel.CastBean> castList;
    private Context context;

    public MoviesCastAdapter(List<MoviesCastModel.CastBean> castList, Context context) {
        this.castList = castList;
        this.context = context;
    }

    @NonNull
    @Override
    public MoviesCastVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movies_cast, parent, false);
        return new MoviesCastVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesCastVH holder, final int position) {

        holder.movieCastName.setText(castList.get(position).getName());
        holder.MovieCastCharacter.setText(castList.get(position).getCharacter());
        Glide.with(context).load("https://image.tmdb.org/t/p/w500" + castList.get(position).getProfile_path())
                .into(holder.movieCastImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context.getApplicationContext(), PersonSearchDetails.class);
                intent.putExtra("personId",castList.get(position).getId());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return castList.size();
    }

    public class MoviesCastVH extends RecyclerView.ViewHolder {

        AppCompatImageView movieCastImage;
        AppCompatTextView movieCastName, MovieCastCharacter;

        public MoviesCastVH(@NonNull View itemView) {
            super(itemView);

            movieCastImage = itemView.findViewById(R.id.movie_cast_image);
            movieCastName = itemView.findViewById(R.id.movie_cast_name);
            MovieCastCharacter = itemView.findViewById(R.id.movie_cast_character);
        }

    }
}
