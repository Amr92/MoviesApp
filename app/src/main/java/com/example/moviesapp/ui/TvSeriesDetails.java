package com.example.moviesapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviesapp.Adapters.MoviesCastAdapter;
import com.example.moviesapp.Adapters.MoviesImagesAdapter;
import com.example.moviesapp.Adapters.PopularSimilarTvAdapter;
import com.example.moviesapp.Adapters.PopularTvSeasonsAdapter;
import com.example.moviesapp.Adapters.PopularTvTrailerAdapter;
import com.example.moviesapp.Classes.PopularTvDetailsPresenter;
import com.example.moviesapp.Classes.PopularTvDetailsView;
import com.example.moviesapp.Models.MovieImagesModel;
import com.example.moviesapp.Models.MoviesCastModel;
import com.example.moviesapp.Models.PopularSimilarTvModel;
import com.example.moviesapp.Models.PopularTvDetailsModel;
import com.example.moviesapp.Models.PopularTvTrailerModel;
import com.example.moviesapp.R;
import com.github.lzyzsd.circleprogress.ArcProgress;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TvSeriesDetails extends AppCompatActivity implements PopularTvDetailsView {

    @BindView(R.id.back_arrow)
    AppCompatImageView backArrow;
    @BindView(R.id.tv_search_detail_poster_image)
    AppCompatImageView tvSearchDetailPosterImage;
    @BindView(R.id.tv_search_detail_title)
    AppCompatTextView tvSearchDetailTitle;
    @BindView(R.id.tv_search_detail_rating_bar)
    ArcProgress tvSearchDetailRatingBar;
    @BindView(R.id.tv_search_detail_original_title)
    AppCompatTextView tvSearchDetailOriginalTitle;
    @BindView(R.id.tv_search_detail_original_title_layout)
    LinearLayoutCompat tvSearchDetailOriginalTitleLayout;
    @BindView(R.id.tv_search_detail_original_language)
    AppCompatTextView tvSearchDetailOriginalLanguage;
    @BindView(R.id.tv_search_detail_original_language_layout)
    LinearLayoutCompat tvSearchDetailOriginalLanguageLayout;
    @BindView(R.id.movie_search_detail_in_production)
    AppCompatTextView movieSearchDetailInProduction;
    @BindView(R.id.tv_search_detail_in_production_layout)
    LinearLayoutCompat tvSearchDetailInProductionLayout;
    @BindView(R.id.tv_search_detail_status)
    AppCompatTextView tvSearchDetailStatus;
    @BindView(R.id.tv_search_detail_status_layout)
    LinearLayoutCompat tvSearchDetailStatusLayout;
    @BindView(R.id.tv_search_detail_popularity)
    AppCompatTextView tvSearchDetailPopularity;
    @BindView(R.id.tv_search_detail_popularity_layout)
    LinearLayoutCompat tvSearchDetailPopularityLayout;
    @BindView(R.id.tv_search_detail_number_of_episodes)
    AppCompatTextView tvSearchDetailNumberOfEpisodes;
    @BindView(R.id.tv_search_detail_number_of_episodes_layout)
    LinearLayoutCompat tvSearchDetailNumberOfEpisodesLayout;
    @BindView(R.id.tv_search_detail_number_of_seasons)
    AppCompatTextView tvSearchDetailNumberOfSeasons;
    @BindView(R.id.tv_search_detail_number_of_seasons_layout)
    LinearLayoutCompat tvSearchDetailNumberOfSeasonsLayout;
    @BindView(R.id.tv_search_detail_genre)
    AppCompatTextView tvSearchDetailGenre;
    @BindView(R.id.tv_search_detail_genre_layout)
    LinearLayoutCompat tvSearchDetailGenreLayout;
    @BindView(R.id.tv_search_detail_first_air_date)
    AppCompatTextView tvSearchDetailFirstAirDate;
    @BindView(R.id.tv_search_detail_first_air_date_layout)
    LinearLayoutCompat tvSearchDetailFirstAirDateLayout;
    @BindView(R.id.tv_search_detail_last_air_date)
    AppCompatTextView tvSearchDetailLastAirDate;
    @BindView(R.id.tv_search_detail_last_air_date_layout)
    LinearLayoutCompat tvSearchDetailLastAirDateLayout;
    @BindView(R.id.tv_search_detail_homepage)
    AppCompatTextView tvSearchDetailHomepage;
    @BindView(R.id.tv_search_detail_Homepage_layout)
    LinearLayoutCompat tvSearchDetailHomepageLayout;
    @BindView(R.id.tv_search_overview)
    AppCompatTextView tvSearchOverview;
    @BindView(R.id.tv_search_detail_overview_layout)
    LinearLayoutCompat tvSearchDetailOverviewLayout;
    @BindView(R.id.tv_detail_cast_rv)
    RecyclerView tvDetailCastRv;
    @BindView(R.id.tv_detail_cast_images_layout)
    LinearLayoutCompat tvDetailCastImagesLayout;
    @BindView(R.id.tv_detail_similar_series_rv)
    RecyclerView tvDetailSimilarSeriesRv;
    @BindView(R.id.tv_detail_similar_series_layout)
    LinearLayoutCompat tvDetailSimilarSeriesLayout;
    @BindView(R.id.tv_detail_production_company_rv)
    RecyclerView tvDetailProductionCompanyRv;
    @BindView(R.id.tv_detail_production_company_layout)
    LinearLayoutCompat tvDetailProductionCompanyLayout;
    @BindView(R.id.tv_detail_images_rv)
    RecyclerView tvDetailImagesRv;
    @BindView(R.id.tv_detail_images_layout)
    LinearLayoutCompat tvDetailImagesLayout;
    @BindView(R.id.tv_detail_videos_rv)
    RecyclerView tvDetailVideosRv;
    @BindView(R.id.tv_detail_videos_layout)
    LinearLayoutCompat tvDetailVideosLayout;
    @BindView(R.id.tv_detail_seasons_rv)
    RecyclerView tvDetailSeasonsRv;
    @BindView(R.id.tv_detail_seasons_layout)
    LinearLayoutCompat tvDetailSeasonsLayout;

    private PopularTvDetailsPresenter presenter;
    private PopularSimilarTvAdapter similarTvAdapter;
    private PopularTvSeasonsAdapter seasonsAdapter;
    private MoviesCastAdapter castAdapter;
    private MoviesImagesAdapter imagesAdapter;
    private PopularTvTrailerAdapter trailerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_series_details);
        ButterKnife.bind(this);

        Intent intent = getIntent();

        presenter = new PopularTvDetailsPresenter(this);

        if (intent != null && intent.getExtras() != null) {

            int movieId = intent.getIntExtra("popularTvId", 0);
            presenter.getPopularTvDetails(movieId);
            presenter.getPopularTvCast(movieId);
            presenter.getPopularSimilarTv(movieId);
            presenter.getPopularTrailersTv(movieId);
            presenter.getTvSeriesImages(movieId);
        }

        tvDetailSimilarSeriesRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        tvDetailSeasonsRv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        tvDetailCastRv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        tvDetailImagesRv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        tvDetailVideosRv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    @Override
    public void onGetPopularTvDetails(PopularTvDetailsModel model) {

        if (model != null) {

            Glide.with(TvSeriesDetails.this).load("https://image.tmdb.org/t/p/w500" + model.getPoster_path()).into(tvSearchDetailPosterImage);

            List<PopularTvDetailsModel.GenresBean> TvDetailsGenresList = model.getGenres();
            List<PopularTvDetailsModel.ProductionCompaniesBean> productionCompaniesList = model.getProduction_companies();
            List<PopularTvDetailsModel.SeasonsBean> seasonsList = model.getSeasons();

            tvSearchDetailTitle.setText(model.getName());

            if (model.getOriginal_name() != null) {
                tvSearchDetailOriginalTitleLayout.setVisibility(View.VISIBLE);
                tvSearchDetailOriginalTitle.setText(model.getOriginal_name());
            } else {
                tvSearchDetailOriginalTitleLayout.setVisibility(View.GONE);
            }

            if (model.getOriginal_language() != null) {
                tvSearchDetailOriginalLanguageLayout.setVisibility(View.VISIBLE);
                tvSearchDetailOriginalLanguage.setText(model.getOriginal_language());
            } else {
                tvSearchDetailOriginalLanguageLayout.setVisibility(View.GONE);
            }

            if (model.in_production) {
                tvSearchDetailInProductionLayout.setVisibility(View.VISIBLE);
                movieSearchDetailInProduction.setText("true");
            } else {
                movieSearchDetailInProduction.setText("false");
            }

            if (model.getStatus() != null) {
                tvSearchDetailStatusLayout.setVisibility(View.VISIBLE);
                tvSearchDetailStatus.setText(model.getStatus());
            } else {
                tvSearchDetailStatusLayout.setVisibility(View.GONE);
            }

            if (model.getPopularity() != 0) {
                tvSearchDetailPopularityLayout.setVisibility(View.VISIBLE);
                tvSearchDetailPopularity.setText(String.valueOf(model.getPopularity()));
            } else {
                tvSearchDetailPopularityLayout.setVisibility(View.GONE);
            }

            if (model.getNumber_of_episodes() != 0) {
                tvSearchDetailNumberOfEpisodesLayout.setVisibility(View.VISIBLE);
                tvSearchDetailNumberOfEpisodes.setText(String.valueOf(model.getNumber_of_episodes()));
            } else {
                tvSearchDetailNumberOfEpisodesLayout.setVisibility(View.GONE);
            }

            if (model.getNumber_of_seasons() != 0) {
                tvSearchDetailNumberOfSeasonsLayout.setVisibility(View.VISIBLE);
                tvSearchDetailNumberOfSeasons.setText(String.valueOf(model.getNumber_of_seasons()));
            } else {
                tvSearchDetailNumberOfSeasonsLayout.setVisibility(View.GONE);
            }

            if (model.getFirst_air_date() != null) {
                tvSearchDetailFirstAirDateLayout.setVisibility(View.VISIBLE);
                tvSearchDetailFirstAirDate.setText(String.valueOf(model.getFirst_air_date()));
            } else {
                tvSearchDetailFirstAirDateLayout.setVisibility(View.GONE);
            }

            if (model.getLast_air_date() != null) {
                tvSearchDetailLastAirDateLayout.setVisibility(View.VISIBLE);
                tvSearchDetailLastAirDate.setText(String.valueOf(model.getLast_air_date()));
            } else {
                tvSearchDetailLastAirDateLayout.setVisibility(View.GONE);
            }

            if (model.getHomepage() != null) {
                tvSearchDetailHomepageLayout.setVisibility(View.VISIBLE);
                tvSearchDetailHomepage.setText(String.valueOf(model.getHomepage()));
            } else {
                tvSearchDetailHomepageLayout.setVisibility(View.GONE);
            }

            if (model.getOverview() != null) {
                tvSearchDetailOverviewLayout.setVisibility(View.VISIBLE);
                tvSearchOverview.setText(String.valueOf(model.getOverview()));
            } else {
                tvSearchDetailOverviewLayout.setVisibility(View.GONE);
            }

            if (TvDetailsGenresList != null && TvDetailsGenresList.size() > 0) {

                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < TvDetailsGenresList.size(); i++) {
                    if (i == TvDetailsGenresList.size() - 1) {
                        stringBuilder.append(TvDetailsGenresList.get(i).getName());
                    } else {
                        stringBuilder.append(TvDetailsGenresList.get(i).getName()).append(", ");
                    }
                }
                tvSearchDetailGenre.setText(stringBuilder.toString());
                tvSearchDetailGenreLayout.setVisibility(View.VISIBLE);
            } else {
                tvSearchDetailGenreLayout.setVisibility(View.GONE);
            }

            double vote_average = model.getVote_average() * 10;

            int rating = (int) vote_average;

            tvSearchDetailRatingBar.setProgress(rating);

            if(seasonsList != null && seasonsList.size() > 0){
                tvDetailSeasonsLayout.setVisibility(View.VISIBLE);
                seasonsAdapter = new PopularTvSeasonsAdapter(seasonsList,TvSeriesDetails.this);
                tvDetailSeasonsRv.setAdapter(seasonsAdapter);
                seasonsAdapter.notifyDataSetChanged();
            }

        }

    }

    @Override
    public void onGetTvSeriesCast(List<MoviesCastModel.CastBean> tvCastList) {

        if(tvCastList != null && tvCastList.size() > 0){
            tvDetailCastImagesLayout.setVisibility(View.VISIBLE);
            castAdapter = new MoviesCastAdapter(tvCastList,TvSeriesDetails.this);
            tvDetailCastRv.setAdapter(castAdapter);
            castAdapter.notifyDataSetChanged();
        }else{
            tvDetailCastImagesLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void onGetPopularSimilarTv(List<PopularSimilarTvModel.ResultsBean> sTvList) {

        if (sTvList != null && sTvList.size() > 0) {
            tvDetailSimilarSeriesLayout.setVisibility(View.VISIBLE);
            similarTvAdapter = new PopularSimilarTvAdapter(sTvList, TvSeriesDetails.this);
            tvDetailSimilarSeriesRv.setAdapter(similarTvAdapter);
        } else {
            tvDetailSimilarSeriesLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void onGetTvImages(List<MovieImagesModel.MovieImagesBackdrops> tvPostersList) {
        if(tvPostersList != null && tvPostersList.size() > 0){
            tvDetailImagesLayout.setVisibility(View.VISIBLE);
            imagesAdapter = new MoviesImagesAdapter(tvPostersList,TvSeriesDetails.this);
            tvDetailImagesRv.setAdapter(imagesAdapter);
            imagesAdapter.notifyDataSetChanged();
        }else{
            tvDetailImagesLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void onGetPopularTvTrailers(List<PopularTvTrailerModel.ResultsBean> pTvTrailerList) {

        if(pTvTrailerList != null && pTvTrailerList.size() > 0){
            tvDetailVideosLayout.setVisibility(View.VISIBLE);
            trailerAdapter = new PopularTvTrailerAdapter(pTvTrailerList,TvSeriesDetails.this);
            tvDetailVideosRv.setAdapter(trailerAdapter);
        }else{
            tvDetailVideosLayout.setVisibility(View.GONE);
        }
    }
}