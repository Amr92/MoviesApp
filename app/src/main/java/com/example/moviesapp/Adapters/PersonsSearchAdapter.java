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
import com.example.moviesapp.Models.PersonsSearchModel;
import com.example.moviesapp.Models.PopularCelebsModel;
import com.example.moviesapp.R;
import com.example.moviesapp.ui.PersonSearchDetails;
import com.google.gson.Gson;

import java.util.List;

public class PersonsSearchAdapter extends RecyclerView.Adapter<PersonsSearchAdapter.MoviesSearchVH> {

    private List<PersonsSearchModel.ResultsBean> resultsBeans;
    private Context context;

    public PersonsSearchAdapter(List<PersonsSearchModel.ResultsBean> resultsBeans, Context context) {
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

        holder.celebName.setText(resultsBeans.get(position).getName());
        holder.celebsPopularity.setText(String.valueOf(resultsBeans.get(position).getPopularity()));
        Glide.with(context).load("https://image.tmdb.org/t/p/w500" + resultsBeans.get(position).getProfile_path()).into(holder.celebImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String x = new Gson().toJson(resultsBeans.get(position).getKnown_for());
                Intent intent = new Intent(context.getApplicationContext(), PersonSearchDetails.class);
                intent.putExtra("personId",resultsBeans.get(position).getId());
                intent.putExtra("x",x);
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
