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
import com.example.moviesapp.Models.PersonsSearchModel;
import com.example.moviesapp.Models.PopularCelebsModel;
import com.example.moviesapp.R;
import com.example.moviesapp.ui.MoviesSearchDetails;
import com.example.moviesapp.ui.TvSeriesDetails;

import java.util.List;

public class KnownForAdapter extends RecyclerView.Adapter<KnownForAdapter.KnownForVH> {

    private List<PopularCelebsModel.ResultsBean.KnownForBean> knownForList;
    private Context context;

    public KnownForAdapter(List<PopularCelebsModel.ResultsBean.KnownForBean> knownForList, Context context) {
        this.knownForList = knownForList;
        this.context = context;
    }

    @NonNull
    @Override
    public KnownForVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.similar_movies_item_rec,parent,false);
        return new KnownForVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KnownForVH holder, final int position) {

        holder.posterTitle.setText(knownForList.get(position).getOriginal_name());
        holder.movieRating.setText(String.valueOf(knownForList.get(position).getVote_average()));
        Glide.with(context).load("https://image.tmdb.org/t/p/w500" + knownForList.get(position).getPoster_path()).into(holder.posterImageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context.getApplicationContext(), MoviesSearchDetails.class);
                intent.putExtra("movie_id",String.valueOf(knownForList.get(position).getId()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return knownForList.size();
    }

    public class KnownForVH extends RecyclerView.ViewHolder{

       AppCompatImageView posterImageView;
       AppCompatTextView posterTitle;
       AppCompatTextView movieRating;

        public KnownForVH(@NonNull View itemView) {
            super(itemView);

            posterImageView = itemView.findViewById(R.id.similar_movie_poster_image);
            posterTitle = itemView.findViewById(R.id.similar_movie_poster_title);
            movieRating = itemView.findViewById(R.id.similar_movie_rating);
        }
    }
}
