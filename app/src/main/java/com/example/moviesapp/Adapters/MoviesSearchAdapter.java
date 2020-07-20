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
import com.example.moviesapp.Models.MoviesSearchModel;
import com.example.moviesapp.R;
import com.example.moviesapp.ui.MoviesSearchDetails;

import java.util.List;

public class MoviesSearchAdapter extends RecyclerView.Adapter<MoviesSearchAdapter.MoviesSearchVH> {

    private List<MoviesSearchModel.ResultsBean> resultsBeans;
    private Context context;

    public MoviesSearchAdapter(List<MoviesSearchModel.ResultsBean> resultsBeans, Context context) {
        this.resultsBeans = resultsBeans;
        this.context = context;
    }

    @NonNull
    @Override
    public MoviesSearchVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rec_more_celebs,parent,false);
        return new MoviesSearchVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesSearchVH holder, final int position) {

        holder.celebName.setText(resultsBeans.get(position).getTitle());
        holder.celebsPopularity.setText(String.valueOf(resultsBeans.get(position).getVote_average()));
        Glide.with(context).load("https://image.tmdb.org/t/p/w500" + resultsBeans.get(position).getPoster_path()).into(holder.celebImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context.getApplicationContext(), MoviesSearchDetails.class);
                intent.putExtra("movie_id",resultsBeans.get(position).getId());
                intent.putExtra("vote_average",Double.toString(resultsBeans.get(position).getVote_average()));
                intent.putExtra("poster_path",resultsBeans.get(position).getPoster_path());
                intent.putExtra("overview",resultsBeans.get(position).getOverview());
                intent.putExtra("title",resultsBeans.get(position).getTitle());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return resultsBeans.size();
    }

    public class MoviesSearchVH extends RecyclerView.ViewHolder{

        AppCompatImageView celebImage;
        AppCompatTextView celebName,celebsPopularity;

        public MoviesSearchVH(@NonNull View itemView) {
            super(itemView);

            celebImage = itemView.findViewById(R.id.more_celebs_img);
            celebName = itemView.findViewById(R.id.more_celebs_name);
            celebsPopularity = itemView.findViewById(R.id.more_celebs_popularity);
        }
    }
}
