package com.example.moviesapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviesapp.Classes.SeasonTvDetailsView;
import com.example.moviesapp.Classes.TvSeasonDetailsPresenter;
import com.example.moviesapp.Models.PopularTvDetailsModel;
import com.example.moviesapp.Models.TvSeasonCastModel;
import com.example.moviesapp.Models.TvSeasonsDetailsModel;
import com.example.moviesapp.R;
import com.github.lzyzsd.circleprogress.ArcProgress;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SeasonTvDetails extends AppCompatActivity implements SeasonTvDetailsView {

    @BindView(R.id.back_arrow_tv)
    AppCompatImageView backArrowTv;
    @BindView(R.id.detail_tv_seasons_cover)
    ImageView detailTvSeasonsCover;
    @BindView(R.id.detail_tv_season_img)
    ImageView detailTvImg;
    @BindView(R.id.detail_tv_season_title)
    TextView detailTvTitle;
    @BindView(R.id.tv_season_episodes_text)
    TextView tvSeasonEpisodesText;
    @BindView(R.id.tv_season_episodes_rv)
    RecyclerView tvSeasonEpisodesRv;
    @BindView(R.id.season_detail_name)
    AppCompatTextView seasonDetailName;
    @BindView(R.id.season_detail_name_layout)
    LinearLayoutCompat seasonDetailNameLayout;
    @BindView(R.id.season_detail_air_date)
    AppCompatTextView seasonDetailAirDate;
    @BindView(R.id.season_detail_air_date_layout)
    LinearLayoutCompat seasonDetailAirDateLayout;
    @BindView(R.id.season_detail_overview)
    AppCompatTextView seasonDetailOverview;
    @BindView(R.id.season_detail_overview_layout)
    LinearLayoutCompat seasonDetailOverviewLayout;
    @BindView(R.id.season_detail_season_number)
    AppCompatTextView seasonDetailSeasonNumber;
    @BindView(R.id.season_detail_season_number_layout)
    LinearLayoutCompat seasonDetailSeasonNumberLayout;
    @BindView(R.id.tv_season_detail_cast_rv)
    RecyclerView tvSeasonDetailCastRv;
    @BindView(R.id.movie_detail_cast_images_layout)
    LinearLayoutCompat movieDetailCastImagesLayout;
    private TvSeasonDetailsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_seasons_details);
        ButterKnife.bind(this);

        int tvId = getIntent().getIntExtra("popularTvId",0);
        int seasonNum = getIntent().getIntExtra("seasonNumber",0);

        presenter = new TvSeasonDetailsPresenter(this);
        presenter.getSeasonDetails(tvId, seasonNum);
        presenter.getSeasonCast(tvId, seasonNum);

        tvSeasonEpisodesRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        tvSeasonDetailCastRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        backArrowTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onGetSeasonDetails(TvSeasonsDetailsModel tvSeasonsDetailsModel) {

        if (tvSeasonsDetailsModel != null) {

            detailTvTitle.setText(tvSeasonsDetailsModel.getName());
            Glide.with(SeasonTvDetails.this).load("https://image.tmdb.org/t/p/w500" + tvSeasonsDetailsModel.getPoster_path()).into(detailTvSeasonsCover);
            Glide.with(SeasonTvDetails.this).load("https://image.tmdb.org/t/p/w500" + tvSeasonsDetailsModel.getPoster_path()).into(detailTvImg);

            List<TvSeasonsDetailsModel.EpisodesBean> seasonsList = tvSeasonsDetailsModel.getEpisodes();

            if (tvSeasonsDetailsModel.getName() != null) {
                seasonDetailNameLayout.setVisibility(View.VISIBLE);
                seasonDetailName.setText(tvSeasonsDetailsModel.getName());
            } else {
                seasonDetailNameLayout.setVisibility(View.GONE);
            }

        }

    }

    @Override
    public void onGetSeasonCast(List<TvSeasonCastModel.CastBean> castBeanList) {

    }
}