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
import com.example.moviesapp.Models.PopularMovieDetailsModel;
import com.example.moviesapp.R;

import java.util.List;

public class MoviesProductionCompaniesAdapter extends RecyclerView.Adapter<MoviesProductionCompaniesAdapter.MovieProductionVH> {

    private List<PopularMovieDetailsModel.ProductionCompaniesBean> proComList;
    private Context context;

    public MoviesProductionCompaniesAdapter(List<PopularMovieDetailsModel.ProductionCompaniesBean> proComList, Context context) {
        this.proComList = proComList;
        this.context = context;
    }

    @NonNull
    @Override
    public MovieProductionVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_production_companies_rec,parent,false);
        return new MovieProductionVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieProductionVH holder, int position) {

        holder.proComName.setText(proComList.get(position).getName());
        Glide.with(context).load("https://image.tmdb.org/t/p/w500" + proComList.get(position).getLogo_path()).into(holder.proComImg);

    }

    @Override
    public int getItemCount() {
        return proComList.size();
    }

    public class MovieProductionVH extends RecyclerView.ViewHolder{

        AppCompatTextView proComName;
        AppCompatImageView proComImg;

        public MovieProductionVH(@NonNull View itemView) {
            super(itemView);

            proComName = itemView.findViewById(R.id.production_company_name);
            proComImg = itemView.findViewById(R.id.production_company_image);
        }
    }
}
