package com.example.moviesapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviesapp.Models.PersonsSearchModel;
import com.example.moviesapp.Models.PopularCelebsModel;
import com.example.moviesapp.R;
import com.example.moviesapp.ui.PersonSearchDetails;
import com.example.moviesapp.ui.PopularCelebsDetails;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class PopularCelebsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int ITEM = 0;
    private static final int LOADING = 1;
    private List<PersonsSearchModel.ResultsBean> movies;
    private Context context;
    private boolean isLoading;

    public PopularCelebsAdapter(Context context) {
        movies = new ArrayList<>();
        this.context = context;

    }

    public List<PersonsSearchModel.ResultsBean> getMovies() {
        return movies;
    }

    public void setMovies(List<PersonsSearchModel.ResultsBean> movies) {
        this.movies = movies;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        if(viewType == ITEM){
           viewHolder = getViewHolder(parent,inflater);

        }
        else if(viewType == LOADING){
            View view = inflater.inflate(R.layout.item_progress,parent,false);
            viewHolder = new LoadingVH(view);
        }
        return viewHolder;
    }

    @NonNull
    private RecyclerView.ViewHolder getViewHolder(ViewGroup parent, LayoutInflater inflater) {
        RecyclerView.ViewHolder viewHolder;
        View v1 = inflater.inflate(R.layout.celebs_item_rec, parent, false);
        viewHolder = new MovieVH(v1);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        if(holder instanceof MovieVH){
            PersonsSearchModel.ResultsBean model = movies.get(position);
            MovieVH movieVH = (MovieVH) holder;
            movieVH.celebName.setText(model.getName());
            Glide.with(context).load("https://image.tmdb.org/t/p/w500" +movies.get(position).getProfile_path()).into(movieVH.celebImage);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String x = new Gson().toJson(movies.get(position).getKnown_for());
                    Intent intent = new Intent(context.getApplicationContext(), PersonSearchDetails.class);
                    intent.putExtra("personId",movies.get(position).getId());
                    intent.putExtra("x",x);
                    context.startActivity(intent);
                }
            });
        }
        else if(holder instanceof LoadingVH){
            LoadingVH loadingVH = (LoadingVH) holder;
            loadingVH.progressBar.setIndeterminate(true);
        }

        }


    @Override
    public int getItemCount() {
        return movies == null ? 0 : movies.size();
    }

    public void setLoaded() {
        isLoading = false;
    }

    @Override
    public int getItemViewType(int position) {
        return (position == movies.size() - 1 && isLoading) ? LOADING : ITEM;
    }

    public void add(PersonsSearchModel.ResultsBean mc) {
        movies.add(mc);
        notifyItemInserted(movies.size() - 1);
    }

    public void addAll(List<PersonsSearchModel.ResultsBean> mcList) {
        for (PersonsSearchModel.ResultsBean mc : mcList) {
            add(mc);
        }
    }

    public void remove(PersonsSearchModel.ResultsBean r) {
        int position = movies.indexOf(r);
        if (position > -1) {
            movies.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear(){
        isLoading = false;
        while(getItemCount() > 0){
            remove(getItem(0));
        }
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }


    public void addLoadingFooter() {
        isLoading = true;
        add(new PersonsSearchModel.ResultsBean());
    }

    public void removeLoadingFooter() {
        isLoading = false;

        int position = movies.size() - 1;
        PersonsSearchModel.ResultsBean item = getItem(position);

        if (item != null) {
            movies.remove(position);
            notifyItemRemoved(position);
        }
    }

    public PersonsSearchModel.ResultsBean getItem(int position) {
        return movies.get(position);
    }

    protected class MovieVH extends RecyclerView.ViewHolder {
        AppCompatImageView celebImage;
        AppCompatTextView celebName;

        public MovieVH(View itemView) {
            super(itemView);

            celebImage = itemView.findViewById(R.id.celebs_poster_image);
            celebName = itemView.findViewById(R.id.celebs_poster_title);
        }
    }


    protected class LoadingVH extends RecyclerView.ViewHolder {
        ProgressBar progressBar;

        public LoadingVH(View itemView) {
            super(itemView);

            progressBar = itemView.findViewById(R.id.load_more_progress);
        }
    }


}
