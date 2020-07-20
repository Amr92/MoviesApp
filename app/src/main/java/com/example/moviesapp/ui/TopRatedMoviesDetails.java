package com.example.moviesapp.ui;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviesapp.Adapters.TopRatedGenresAdapter;
import com.example.moviesapp.Adapters.TopRatedMoviesTrailerAdapter;
import com.example.moviesapp.Adapters.TopRatedSimilarMoviesAdapter;
import com.example.moviesapp.Models.TopRatedMovieDetailsModel;
import com.example.moviesapp.Models.TopRatedMoviesTrailerModel;
import com.example.moviesapp.Models.TopRatedSimilarMoviesModel;
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

public class TopRatedMoviesDetails extends AppCompatActivity {


    @BindView(R.id.detail_top_rated_movie_cover)
    ImageView detailTopRatedMovieCover;
    @BindView(R.id.detail_top_rated_movie_img)
    ImageView detailTopRatedMovieImg;
    @BindView(R.id.detail_top_rated_movie_title)
    TextView detailTopRatedMovieTitle;
    @BindView(R.id.detail_top_rated_movie_overview)
    TextView detailTopRatedMovieOverview;
    @BindView(R.id.top_genres_rec_view)
    RecyclerView topGenresRecView;
    @BindView(R.id.floatingActionButton_top_rated)
    FloatingActionButton floatingActionButtonTopRated;
    @BindView(R.id.top_view_1)
    View topView1;
    @BindView(R.id.top_rated_watchlist_button)
    Button topRatedWatchlistButton;
    @BindView(R.id.top_view_2)
    View topView2;
    @BindView(R.id.top_rated_rating)
    AppCompatTextView topRatedRating;
    @BindView(R.id.top_rated_vote_count)
    AppCompatTextView topRatedVoteCount;
    @BindView(R.id.top_rated_rate_this)
    TextView topRatedRateThis;
    @BindView(R.id.top_rated_rating_bar)
    RatingBar topRatedRatingBar;
    @BindView(R.id.top_rated_rate_linear_layout)
    LinearLayout topRatedRateLinearLayout;
    @BindView(R.id.top_rated_linear_rate)
    LinearLayout topRatedLinearRate;
    @BindView(R.id.top_view_3)
    View topView3;
    @BindView(R.id.top_rated_images_text)
    TextView topRatedImagesText;
    @BindView(R.id.top_rated_similar_movies_rv)
    RecyclerView topRatedSimilarMoviesRv;
    @BindView(R.id.top_rated_similar_movies_card)
    CardView topRatedSimilarMoviesCard;
    @BindView(R.id.top_rated_movies_details_text)
    TextView topRatedMoviesDetailsText;
    @BindView(R.id.top_rated_movies_release_date)
    AppCompatTextView topRatedMoviesReleaseDate;
    @BindView(R.id.top_rated_movies_Home_page)
    AppCompatTextView topRatedMoviesHomePage;
    @BindView(R.id.top_rated_movies_language_spoken)
    AppCompatTextView topRatedMoviesLanguageSpoken;
    @BindView(R.id.top_rated_movies_runtime)
    AppCompatTextView topRatedMoviesRuntime;
    @BindView(R.id.top_rated_movies_details_card)
    CardView topRatedMoviesDetailsCard;
    @BindView(R.id.top_rated_movies_videos_text)
    TextView topRatedMoviesVideosText;
    @BindView(R.id.top_rated_movies_videos_rv)
    RecyclerView topRatedMoviesVideosRv;
    @BindView(R.id.top_rated_movies_videos_card)
    CardView topRatedMoviesVideosCard;
    private int topRatedMovieId;
    private AsyncHttpClient client;
    private String API_KEY = "56461395297741c1c0452f322385501c";
    private RecyclerView topRatedGenresRec;
    private RecyclerView topRatedSimilarMoviesRec;
    private RecyclerView recTopRatedVideos;
    private TopRatedGenresAdapter topRatedGenresAdapter;
    private List<TopRatedMovieDetailsModel.GenresBean> list = new ArrayList<>();
    private List<TopRatedSimilarMoviesModel.ResultsBean> sMList = new ArrayList<>();
    private List<TopRatedMoviesTrailerModel.ResultsBean> trailerList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_rated_movies_details);
        ButterKnife.bind(this);

        topRatedMovieId = getIntent().getIntExtra("topRatedId", 0);

        detailTopRatedMovieCover.setAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_animation));
        floatingActionButtonTopRated.setAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_animation));

        topRatedGenresRec = findViewById(R.id.top_genres_rec_view);
        topRatedGenresRec.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        topRatedSimilarMoviesRec = findViewById(R.id.top_rated_similar_movies_rv);
        topRatedSimilarMoviesRec.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        recTopRatedVideos = findViewById(R.id.top_rated_movies_videos_rv);
        recTopRatedVideos.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        getMovieDetails(topRatedMovieId);
        getSimilarMovies(topRatedMovieId);
        getTrailer(topRatedMovieId);
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
                Type dataType = new TypeToken<TopRatedMovieDetailsModel>() {
                }.getType();
                TopRatedMovieDetailsModel model = new Gson().fromJson(responseString, dataType);

                Glide.with(TopRatedMoviesDetails.this).load("https://image.tmdb.org/t/p/w500" + model.getPoster_path()).into(detailTopRatedMovieImg);
                detailTopRatedMovieTitle.setText(model.getTitle());
                detailTopRatedMovieOverview.setText(model.getOverview());
                Glide.with(TopRatedMoviesDetails.this).load("https://image.tmdb.org/t/p/w500" + model.getBackdrop_path()).into(detailTopRatedMovieCover);

                topRatedRating.setText(String.valueOf(model.getVote_average()));
                topRatedVoteCount.setText(String.valueOf(model.getVote_count()));
                topRatedMoviesReleaseDate.setText(model.getRelease_date());
                topRatedMoviesHomePage.setText(model.getHomepage());
                topRatedMoviesLanguageSpoken.setText(model.getOriginal_language());
                topRatedMoviesRuntime.setText(String.valueOf(model.getRuntime()));

                list.addAll(model.getGenres());
                topRatedGenresAdapter = new TopRatedGenresAdapter(list, TopRatedMoviesDetails.this);
                topRatedGenresRec.setAdapter(topRatedGenresAdapter);
            }
        });
    }

    private void getSimilarMovies(int topRatedMovieId) {
        String movie_id = String.valueOf(topRatedMovieId);
        String SERVER_URL = "https://api.themoviedb.org/3/movie/" + movie_id + "/similar?api_key=" + API_KEY;
        client = new AsyncHttpClient();
        client.get(SERVER_URL, null, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Type dataType = new TypeToken<TopRatedSimilarMoviesModel>() {
                }.getType();
                TopRatedSimilarMoviesModel model = new Gson().fromJson(responseString, dataType);
                sMList.addAll(model.getResults());

                TopRatedSimilarMoviesAdapter adapter = new TopRatedSimilarMoviesAdapter(sMList, TopRatedMoviesDetails.this);
                topRatedSimilarMoviesRec.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void getTrailer(int topRatedMovieId) {
        String movie_id = String.valueOf(topRatedMovieId);
        String SERVER_URL = "https://api.themoviedb.org/3/movie/" + movie_id + "/videos?api_key=" + API_KEY;
        client = new AsyncHttpClient();
        client.get(SERVER_URL, null, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Type dataType = new TypeToken<TopRatedMoviesTrailerModel>() {
                }.getType();
                TopRatedMoviesTrailerModel model = new Gson().fromJson(responseString, dataType);
                trailerList.addAll(model.getResults());

                TopRatedMoviesTrailerAdapter trailerAdapter = new TopRatedMoviesTrailerAdapter(trailerList, TopRatedMoviesDetails.this);
                recTopRatedVideos.setAdapter(trailerAdapter);
                trailerAdapter.notifyDataSetChanged();
            }
        });

    }
}
