package com.example.moviesapp.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviesapp.Adapters.MoviesCastAdapter;
import com.example.moviesapp.Adapters.MoviesImagesAdapter;
import com.example.moviesapp.Adapters.MoviesProductionCompaniesAdapter;
import com.example.moviesapp.Adapters.PopularMoviesTrailerAdapter;
import com.example.moviesapp.Adapters.PopularSimilarMoviesAdapter;
import com.example.moviesapp.Classes.MoviesSearchDetailsPresenter;
import com.example.moviesapp.Classes.MoviesSearchDetailsView;
import com.example.moviesapp.Database.FavoriteContract;
import com.example.moviesapp.Database.FavoriteDBHelper;
import com.example.moviesapp.Models.MovieImagesModel;
import com.example.moviesapp.Models.MoviesCastModel;
import com.example.moviesapp.Models.PopularMovieDetailsModel;
import com.example.moviesapp.Models.PopularMoviesModel;
import com.example.moviesapp.Models.PopularMoviesTrailerModel;
import com.example.moviesapp.Models.PopularSimilarMoviesModel;
import com.example.moviesapp.R;
import com.github.ivbaranov.mfb.MaterialFavoriteButton;
import com.github.lzyzsd.circleprogress.ArcProgress;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesSearchDetails extends AppCompatActivity implements MoviesSearchDetailsView {


    @BindView(R.id.back_arrow)
    AppCompatImageView backArrow;
    @BindView(R.id.movie_detail_poster_image)
    AppCompatImageView movieDetailPosterImage;

    @BindView(R.id.movie_search_detail_rating_bar)
    ArcProgress movieSearchDetailRatingBar;
    @BindView(R.id.back_drop_layout)
    FrameLayout backDropLayout;
    @BindView(R.id.movie_search_detail_original_title)
    AppCompatTextView movieSearchDetailOriginalTitle;
    @BindView(R.id.movie_search_detail_original_title_layout)
    LinearLayoutCompat movieSearchDetailOriginalTitleLayout;
    @BindView(R.id.movie_search_detail_original_language)
    AppCompatTextView movieSearchDetailOriginalLanguage;
    @BindView(R.id.movie_search_detail_original_language_layout)
    LinearLayoutCompat movieSearchDetailOriginalLanguageLayout;
    @BindView(R.id.movie_search_detail_adult)
    AppCompatTextView movieSearchDetailAdult;
    @BindView(R.id.movie_search_detail_adult_layout)
    LinearLayoutCompat movieSearchDetailAdultLayout;
    @BindView(R.id.movie_search_detail_status)
    AppCompatTextView movieSearchDetailStatus;
    @BindView(R.id.movie_search_detail_status_layout)
    LinearLayoutCompat movieSearchDetailStatusLayout;
    @BindView(R.id.movie_search_detail_runtime)
    AppCompatTextView movieSearchDetailRuntime;
    @BindView(R.id.movie_search_detail_runtime_layout)
    LinearLayoutCompat movieSearchDetailRuntimeLayout;
    @BindView(R.id.movie_search_detail_budget)
    AppCompatTextView movieSearchDetailBudget;
    @BindView(R.id.movie_search_detail_budget_layout)
    LinearLayoutCompat movieSearchDetailBudgetLayout;
    @BindView(R.id.movie_search_detail_revenue)
    AppCompatTextView movieSearchDetailRevenue;
    @BindView(R.id.movie_search_detail_revenue_layout)
    LinearLayoutCompat movieSearchDetailRevenueLayout;
    @BindView(R.id.movie_search_detail_genre)
    AppCompatTextView movieSearchDetailGenre;
    @BindView(R.id.movie_search_detail_genre_layout)
    LinearLayoutCompat movieSearchDetailGenreLayout;
    @BindView(R.id.movie_search_detail_production_country)
    AppCompatTextView movieSearchDetailProductionCountry;
    @BindView(R.id.movie_search_detail_production_country_layout)
    LinearLayoutCompat movieSearchDetailProductionCountryLayout;
    @BindView(R.id.movie_search_detail_release_date)
    AppCompatTextView movieSearchDetailReleaseDate;
    @BindView(R.id.movie_search_detail_release_date_layout)
    LinearLayoutCompat movieSearchDetailReleaseDateLayout;
    @BindView(R.id.movie_search_detail_homepage)
    AppCompatTextView movieSearchDetailHomepage;
    @BindView(R.id.movie_search_detail_Homepage_layout)
    LinearLayoutCompat movieSearchDetailHomepageLayout;
    @BindView(R.id.movie_search_overview)
    AppCompatTextView movieSearchOverview;
    @BindView(R.id.movie_search_detail_overview_layout)
    LinearLayoutCompat movieSearchDetailOverviewLayout;
    @BindView(R.id.movie_detail_cast_rv)
    RecyclerView movieDetailCastRv;
    @BindView(R.id.movie_detail_cast_images_layout)
    LinearLayoutCompat movieDetailCastImagesLayout;
    @BindView(R.id.movie_detail_production_company_rv)
    RecyclerView movieDetailProductionCompanyRv;
    @BindView(R.id.movie_detail_production_company_layout)
    LinearLayoutCompat movieDetailProductionCompanyLayout;
    @BindView(R.id.movie_detail_images_rv)
    RecyclerView movieDetailImagesRv;
    @BindView(R.id.movie_detail_images_layout)
    LinearLayoutCompat movieDetailImagesLayout;
    @BindView(R.id.movie_detail_videos_rv)
    RecyclerView movieDetailVideosRv;
    @BindView(R.id.movie_detail_videos_layout)
    LinearLayoutCompat movieDetailVideosLayout;
    @BindView(R.id.movie_detail_similar_movies_rv)
    RecyclerView movieDetailSimilarMoviesRv;
    @BindView(R.id.movie_detail_similar_movies_layout)
    LinearLayoutCompat movieDetailSimilarMoviesLayout;
    @BindView(R.id.movie_search_detail_title)
    TextView movieSearchDetailTitle;

    private String movieName,moviePosterPath;
    private MoviesSearchDetailsPresenter presenter;
    private MoviesCastAdapter adapter;
    private PopularSimilarMoviesAdapter similarMoviesAdapter;
    private MoviesProductionCompaniesAdapter productionAdapter;
    private MoviesImagesAdapter imagesAdapter;
    private PopularMoviesTrailerAdapter trailerAdapter;
    private FavoriteDBHelper favoriteDBHelper;
    private final AppCompatActivity activity = MoviesSearchDetails.this;
    private SQLiteDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_search_details);
        ButterKnife.bind(this);

        Intent intent = getIntent();

        presenter = new MoviesSearchDetailsPresenter(this);

        if (intent != null && intent.getExtras() != null) {

            int movieId = intent.getIntExtra("movie_id", 0);
            presenter.getMoviesSearchDetails(movieId);
            presenter.getMoviesCast(movieId);
            presenter.getPopularSimilarMoviesData(movieId);
            presenter.getMoviesPosters(movieId);
            presenter.getMoviesTrailers(movieId);
        }

        movieDetailCastRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        movieDetailProductionCompanyRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        movieDetailImagesRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        movieDetailVideosRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        movieDetailSimilarMoviesRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        FavoriteDBHelper dBHelper = new FavoriteDBHelper(this);
        mDb = dBHelper.getWritableDatabase();

        MaterialFavoriteButton moviesFavButton = findViewById(R.id.movies_fav_button);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        moviesFavButton.setOnFavoriteChangeListener(new MaterialFavoriteButton.OnFavoriteChangeListener() {
            @Override
            public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite) {
                if(favorite){
                    SharedPreferences.Editor editor = getSharedPreferences("myPrefs",MODE_PRIVATE).edit();
                    editor.putBoolean("Favourite Added",true);
                    editor.apply();
                    saveFavourite();
                    Snackbar.make(buttonView,"Added to Favourite",Snackbar.LENGTH_SHORT).show();
                } else{
                    int movieId = getIntent().getExtras().getInt("movie_id");
                    favoriteDBHelper = new FavoriteDBHelper(MoviesSearchDetails.this);
                    favoriteDBHelper.deleteFavorite(movieId);
                    SharedPreferences.Editor editor = getSharedPreferences("myPrefs",MODE_PRIVATE).edit();
                    editor.putBoolean("Favourite Removed",true);
                    editor.apply();
                    Snackbar.make(buttonView,"Removed From Favourite",Snackbar.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onGetSearchMoviesDetails(PopularMovieDetailsModel popularMovieDetailsModel) {

        if (popularMovieDetailsModel != null) {

            movieName = popularMovieDetailsModel.getTitle();
            moviePosterPath = popularMovieDetailsModel.getPoster_path();

            List<PopularMovieDetailsModel.GenresBean> movieDetailsGenresList = popularMovieDetailsModel.getGenres();
            List<PopularMovieDetailsModel.ProductionCompaniesBean> productionCompaniesList = popularMovieDetailsModel.getProduction_companies();
            List<PopularMovieDetailsModel.ProductionCountriesBean> productionCountriesList = popularMovieDetailsModel.getProduction_countries();

            Glide.with(MoviesSearchDetails.this).load("https://image.tmdb.org/t/p/w500" + moviePosterPath).into(movieDetailPosterImage);

            movieSearchDetailTitle.setText(movieName);

            if (popularMovieDetailsModel.getOriginal_title() != null) {
                if (popularMovieDetailsModel.getOriginal_title().length() > 0) {
                    movieSearchDetailOriginalTitle.setText(popularMovieDetailsModel.getOriginal_title());
                    movieSearchDetailOriginalTitleLayout.setVisibility(View.VISIBLE);
                } else {
                    movieSearchDetailOriginalTitleLayout.setVisibility(View.GONE);
                }
            } else {
                movieSearchDetailOriginalTitleLayout.setVisibility(View.GONE);
            }

            if (popularMovieDetailsModel.getOriginal_language() != null) {
                if (popularMovieDetailsModel.getOriginal_language().length() > 0) {
                    movieSearchDetailOriginalLanguage.setText(popularMovieDetailsModel.getOriginal_language());
                    movieSearchDetailOriginalLanguageLayout.setVisibility(View.VISIBLE);
                } else {
                    movieSearchDetailOriginalLanguageLayout.setVisibility(View.GONE);
                }
            } else {
                movieSearchDetailOriginalLanguageLayout.setVisibility(View.GONE);
            }

            if (popularMovieDetailsModel.isAdult()) {
                movieSearchDetailAdult.setText("Yes");
                movieSearchDetailAdultLayout.setVisibility(View.VISIBLE);
            } else {
                movieSearchDetailAdult.setText("No");
                movieSearchDetailAdultLayout.setVisibility(View.VISIBLE);
            }

            if (popularMovieDetailsModel.getStatus() != null) {
                if (popularMovieDetailsModel.getStatus().length() > 0) {
                    movieSearchDetailStatus.setText(popularMovieDetailsModel.getStatus());
                    movieSearchDetailStatusLayout.setVisibility(View.VISIBLE);
                } else {
                    movieSearchDetailStatusLayout.setVisibility(View.GONE);
                }
            } else {
                movieSearchDetailStatusLayout.setVisibility(View.GONE);
            }

            if (popularMovieDetailsModel.getRuntime() != 0) {
                movieSearchDetailRuntime.setText(String.valueOf(popularMovieDetailsModel.getRuntime()));
                movieSearchDetailRuntimeLayout.setVisibility(View.VISIBLE);
            } else {
                movieSearchDetailStatusLayout.setVisibility(View.GONE);
            }

            if (popularMovieDetailsModel.getBudget() != 0) {
                movieSearchDetailBudget.setText(String.valueOf(popularMovieDetailsModel.getBudget()));
                movieSearchDetailBudgetLayout.setVisibility(View.VISIBLE);
            } else {
                movieSearchDetailBudgetLayout.setVisibility(View.GONE);
            }

            if (popularMovieDetailsModel.getRevenue() != 0) {
                movieSearchDetailRevenue.setText(String.valueOf(popularMovieDetailsModel.getRevenue()));
                movieSearchDetailRevenueLayout.setVisibility(View.VISIBLE);
            } else {
                movieSearchDetailRevenueLayout.setVisibility(View.GONE);
            }

            if (movieDetailsGenresList != null && movieDetailsGenresList.size() > 0) {

                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < movieDetailsGenresList.size(); i++) {
                    if (i == movieDetailsGenresList.size() - 1) {
                        stringBuilder.append(movieDetailsGenresList.get(i).getName());
                    } else {
                        stringBuilder.append(movieDetailsGenresList.get(i).getName()).append(", ");
                    }
                }
                movieSearchDetailGenre.setText(stringBuilder.toString());
                movieSearchDetailGenreLayout.setVisibility(View.VISIBLE);
            } else {
                movieSearchDetailGenreLayout.setVisibility(View.GONE);
            }

            if (productionCountriesList != null && productionCountriesList.size() > 0) {

                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < productionCountriesList.size(); i++) {
                    if (i == productionCountriesList.size() - 1) {
                        stringBuilder.append(productionCountriesList.get(i).getName());
                    } else {
                        stringBuilder.append(productionCountriesList.get(i).getName()).append(", ");
                    }
                }
                movieSearchDetailProductionCountry.setText(stringBuilder.toString());
                movieSearchDetailProductionCountryLayout.setVisibility(View.VISIBLE);
            } else {
                movieSearchDetailProductionCountryLayout.setVisibility(View.GONE);
            }

            if (popularMovieDetailsModel.getRelease_date() != null) {
                if (popularMovieDetailsModel.getRelease_date().length() > 0) {
                    movieSearchDetailReleaseDate.setText(popularMovieDetailsModel.getRelease_date());
                    movieSearchDetailReleaseDateLayout.setVisibility(View.VISIBLE);
                } else {
                    movieSearchDetailReleaseDateLayout.setVisibility(View.GONE);
                }
            } else {
                movieSearchDetailReleaseDateLayout.setVisibility(View.GONE);
            }

            if (popularMovieDetailsModel.getHomepage() != null) {
                if (popularMovieDetailsModel.getHomepage().length() > 0) {
                    movieSearchDetailHomepage.setText(popularMovieDetailsModel.getHomepage());
                    movieSearchDetailHomepageLayout.setVisibility(View.VISIBLE);
                } else {
                    movieSearchDetailHomepageLayout.setVisibility(View.GONE);
                }
            } else {
                movieSearchDetailHomepageLayout.setVisibility(View.GONE);
            }

            if (popularMovieDetailsModel.getOverview() != null) {
                if (popularMovieDetailsModel.getOverview().length() > 0) {
                    movieSearchOverview.setText(popularMovieDetailsModel.getOverview());
                    movieSearchDetailOverviewLayout.setVisibility(View.VISIBLE);
                } else {
                    movieSearchDetailOverviewLayout.setVisibility(View.GONE);
                }
            } else {
                movieSearchDetailOverviewLayout.setVisibility(View.GONE);
            }

            double vote_average = popularMovieDetailsModel.getVote_average() * 10;

            int rating = (int) vote_average;

            movieSearchDetailRatingBar.setProgress(rating);

            if (productionCompaniesList != null && productionCompaniesList.size() > 0) {
                movieDetailProductionCompanyLayout.setVisibility(View.VISIBLE);
                productionAdapter = new MoviesProductionCompaniesAdapter(productionCompaniesList, MoviesSearchDetails.this);
                movieDetailProductionCompanyRv.setAdapter(productionAdapter);

            } else {
                movieDetailProductionCompanyLayout.setVisibility(View.GONE);
            }

        }
    }

    @Override
    public void onGetMoviesCast(List<MoviesCastModel.CastBean> castList) {

        if (castList != null && castList.size() > 0) {
            movieDetailCastImagesLayout.setVisibility(View.VISIBLE);
            adapter = new MoviesCastAdapter(castList, MoviesSearchDetails.this);
            movieDetailCastRv.setAdapter(adapter);
            adapter.notifyDataSetChanged();

        } else {
            movieDetailCastImagesLayout.setVisibility(View.GONE);
        }

    }

    @Override
    public void onGetPopularSimilarMovies(List<PopularSimilarMoviesModel.ResultsBean> sMList) {

        if (sMList != null && sMList.size() > 0) {
            movieDetailSimilarMoviesLayout.setVisibility(View.VISIBLE);
            similarMoviesAdapter = new PopularSimilarMoviesAdapter(sMList, MoviesSearchDetails.this);
            movieDetailSimilarMoviesRv.setAdapter(similarMoviesAdapter);
            similarMoviesAdapter.notifyDataSetChanged();
        } else {
            movieDetailSimilarMoviesLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void onGetMoviesImages(List<MovieImagesModel.MovieImagesBackdrops> postersList) {

        if (postersList != null && postersList.size() > 0) {

            movieDetailImagesLayout.setVisibility(View.VISIBLE);
            imagesAdapter = new MoviesImagesAdapter(postersList, MoviesSearchDetails.this);
            movieDetailImagesRv.setAdapter(imagesAdapter);
            imagesAdapter.notifyDataSetChanged();
        } else {
            movieDetailImagesLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void onGetMovieTrailers(List<PopularMoviesTrailerModel.ResultsBean> trailerList) {

        if (trailerList != null && trailerList.size() > 0) {

            movieDetailVideosLayout.setVisibility(View.VISIBLE);
            trailerAdapter = new PopularMoviesTrailerAdapter(trailerList, MoviesSearchDetails.this);
            movieDetailVideosRv.setAdapter(trailerAdapter);
            trailerAdapter.notifyDataSetChanged();
        } else {
            movieDetailVideosLayout.setVisibility(View.GONE);
        }
    }

    public void saveFavourite() {

        favoriteDBHelper = new FavoriteDBHelper(MoviesSearchDetails.this);
        PopularMoviesModel.ResultsBean favorite = new PopularMoviesModel.ResultsBean();

        int movieId = getIntent().getExtras().getInt("movie_id");
        String movieTitle = getIntent().getExtras().getString("title");
        String posterPath = getIntent().getExtras().getString("poster_path");

        favorite.setId(movieId);
        favorite.setOriginal_title(movieTitle);
        favorite.setPoster_path(posterPath);

        favoriteDBHelper.addFavorite(favorite);

    }
}
