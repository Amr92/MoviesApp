package com.example.moviesapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Movie;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviesapp.ui.MoviesSearchDetails;
import com.example.moviesapp.ui.TopRatedMoviesDetails;
import com.example.moviesapp.Models.TopRatedMoviesModel;
import com.example.moviesapp.R;
import com.flaviofaria.kenburnsview.KenBurnsView;

import java.util.List;

public class TopRatedMoviesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int ITEM = 0;
    private static final int LOADING = 1;
    private List<TopRatedMoviesModel.ResultsBean> movies;
    private Context context;
    private boolean isLoading;

    public TopRatedMoviesAdapter(List<TopRatedMoviesModel.ResultsBean> movies, Context context) {
        this.movies = movies;
        this.context = context;

    }

    public List<TopRatedMoviesModel.ResultsBean> getMovies() {
        return movies;
    }

    public void setMovies(List<TopRatedMoviesModel.ResultsBean> movies) {
        this.movies = movies;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == ITEM){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rec_movies,parent,false);
            return new MovieVH(view);
        }
        else if(viewType == LOADING){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_progress,parent,false);
            return new LoadingVH(view);
        }
        return null;
    }

    @NonNull
    private RecyclerView.ViewHolder getViewHolder(ViewGroup parent, LayoutInflater inflater) {
        RecyclerView.ViewHolder viewHolder;
        View v1 = inflater.inflate(R.layout.item_rec_movies, parent, false);
        viewHolder = new MovieVH(v1);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        if(holder instanceof MovieVH){
            TopRatedMoviesModel.ResultsBean model = movies.get(position);
            MovieVH movieVH = (MovieVH) holder;
            movieVH.posterTitle.setText(model.getTitle());
            movieVH.movieRating.setText(String.valueOf(model.getVote_average()));
            Glide.with(context).load("https://image.tmdb.org/t/p/w500" +movies.get(position).getPoster_path()).into(movieVH.posterImageView);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context.getApplicationContext(), MoviesSearchDetails.class);
                    intent.putExtra("movie_id",movies.get(position).getId());
                    intent.putExtra("vote_average",Double.toString(movies.get(position).getVote_average()));
                    intent.putExtra("poster_path",movies.get(position).getPoster_path());
                    intent.putExtra("overview",movies.get(position).getOverview());
                    intent.putExtra("title",movies.get(position).getTitle());
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
        return movies.size();
    }

    public void setLoaded() {
        isLoading = false;
    }

    @Override
    public int getItemViewType(int position) {
        return movies.get(position) == null ? LOADING : ITEM;
    }

    public void add(TopRatedMoviesModel.ResultsBean mc) {
        movies.add(mc);
        notifyItemInserted(movies.size() - 1);
    }

    public void addAll(List<TopRatedMoviesModel.ResultsBean> mcList) {
        for (TopRatedMoviesModel.ResultsBean mc : mcList) {
            add(mc);
        }
    }

    public void remove(Movie r) {
        int position = movies.indexOf(r);
        if (position > -1) {
            movies.remove(position);
            notifyItemRemoved(position);
        }
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }


    public void addLoadingFooter() {
        isLoading = true;
        add(new TopRatedMoviesModel.ResultsBean());
    }

    public void removeLoadingFooter() {
        isLoading = false;

        int position = movies.size() - 1;
        TopRatedMoviesModel.ResultsBean item = getItem(position);

        if (item != null) {
            movies.remove(position);
            notifyItemRemoved(position);
        }
    }

    public TopRatedMoviesModel.ResultsBean getItem(int position) {
        return movies.get(position);
    }

    protected class MovieVH extends RecyclerView.ViewHolder {
        KenBurnsView posterImageView;
        AppCompatTextView posterTitle;
        AppCompatTextView movieRating;

        public MovieVH(View itemView) {
            super(itemView);

            posterImageView = itemView.findViewById(R.id.poster_image_view);
            posterTitle = itemView.findViewById(R.id.poster_title);
            movieRating = itemView.findViewById(R.id.movie_rating);
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
