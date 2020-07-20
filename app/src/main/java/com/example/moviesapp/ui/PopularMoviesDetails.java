package com.example.moviesapp.ui;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviesapp.Adapters.PopularGenresAdapter;
import com.example.moviesapp.Adapters.PopularMoviesTrailerAdapter;
import com.example.moviesapp.Adapters.PopularSimilarMoviesAdapter;
import com.example.moviesapp.Classes.PopularMoviesDetailsPresenter;
import com.example.moviesapp.Classes.PopularMoviesDetailsView;
import com.example.moviesapp.Models.PopularMovieDetailsModel;
import com.example.moviesapp.Models.PopularMoviesTrailerModel;
import com.example.moviesapp.Models.PopularSimilarMoviesModel;
import com.example.moviesapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class PopularMoviesDetails extends AppCompatActivity implements PopularMoviesDetailsView {

    @BindView(R.id.detail_movie_cover)
    ImageView detailMovieCover;
    @BindView(R.id.detail_movie_img)
    ImageView detailMovieImg;
    @BindView(R.id.detail_movie_title)
    TextView detailMovieTitle;
    @BindView(R.id.detail_movie_overview)
    TextView detailMovieOverview;
    @BindView(R.id.floatingActionButton)
    FloatingActionButton play_fab;
    @BindView(R.id.back_arrow)
    AppCompatImageView backArrow;
    @BindView(R.id.popular_watchlist_button)
    Button popularWatchlistButton;
    @BindView(R.id.popular_rating)
    AppCompatTextView popularRating;
    @BindView(R.id.popular_vote_count)
    AppCompatTextView popularVoteCount;
    @BindView(R.id.popular_movies_release_date)
    AppCompatTextView popularSimilarReleaseDate;
    @BindView(R.id.popular_movies_Home_page)
    AppCompatTextView popularSimilarHomePage;
    @BindView(R.id.popular_movies_runtime)
    AppCompatTextView popularSimilarRuntime;
    @BindView(R.id.popular_movies_language_spoken)
    AppCompatTextView popularSimilarLanguageSpoken;
    private int popularMovieId;
    private AsyncHttpClient client;
    private String API_KEY = "56461395297741c1c0452f322385501c";
    private RecyclerView popularGenresRec;
    private RecyclerView popularSimilarMoviesRec;
    private RecyclerView recPopularVideos;
    private PopularMoviesTrailerAdapter trailerAdapter;
    private PopularGenresAdapter adapter;
    private List<PopularMovieDetailsModel.GenresBean> list = new ArrayList<>();

    PopularMoviesDetailsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular_movies_details);
        ButterKnife.bind(this);

        popularMovieId = getIntent().getIntExtra("popularId", 0);

        detailMovieCover.setAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_animation));
        play_fab.setAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_animation));

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        popularGenresRec = findViewById(R.id.popular_genres_rec_view);
        popularGenresRec.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        popularSimilarMoviesRec = findViewById(R.id.popular_similar_movies_rv);
        popularSimilarMoviesRec.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        recPopularVideos = findViewById(R.id.popular_movies_videos_rv);
        recPopularVideos.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        presenter = new PopularMoviesDetailsPresenter(this);
        presenter.getPopularSimilarMoviesData(popularMovieId);
        presenter.getPopularMoviesTrailers(popularMovieId);

        getMovieDetails(popularMovieId);
    }

    private void getMovieDetails(int movieId) {
        String movie_id = String.valueOf(movieId);
        String SERVER_URL = "https://api.themoviedb.org/3/movie/" + movie_id + "?api_key=" + API_KEY;
        client = new AsyncHttpClient();
        client.get(SERVER_URL, null, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Type dataType = new TypeToken<PopularMovieDetailsModel>() {
                }.getType();
                PopularMovieDetailsModel model = new Gson().fromJson(responseString, dataType);

                Glide.with(PopularMoviesDetails.this).load("https://image.tmdb.org/t/p/w500" + model.getPoster_path()).into(detailMovieImg);
                detailMovieTitle.setText(model.getTitle());
                detailMovieOverview.setText(model.getOverview());
                Glide.with(PopularMoviesDetails.this).load("https://image.tmdb.org/t/p/w500" + model.getBackdrop_path()).into(detailMovieCover);
                popularRating.setText(String.valueOf(model.getVote_average()));
                popularVoteCount.setText(String.valueOf(model.getVote_count()));
                popularSimilarReleaseDate.setText(model.getRelease_date());
                popularSimilarHomePage.setText(model.getHomepage());
                popularSimilarLanguageSpoken.setText(model.getOriginal_language());
                popularSimilarRuntime.setText(String.valueOf(model.getRuntime()));

                list.addAll(model.getGenres());
                adapter = new PopularGenresAdapter(list, PopularMoviesDetails.this);
                popularGenresRec.setAdapter(adapter);
            }
        });
    }


    @Override
    public void onGetPopularSimilarMovies(List<PopularSimilarMoviesModel.ResultsBean> pSMoviesList) {

        PopularSimilarMoviesAdapter adapter = new PopularSimilarMoviesAdapter(pSMoviesList, PopularMoviesDetails.this);
        popularSimilarMoviesRec.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onGetPopularMoviesTrailer(List<PopularMoviesTrailerModel.ResultsBean> pMTrailerList) {

        trailerAdapter= new PopularMoviesTrailerAdapter(pMTrailerList, PopularMoviesDetails.this);
        recPopularVideos.setAdapter(trailerAdapter);
        trailerAdapter.notifyDataSetChanged();
    }
}
