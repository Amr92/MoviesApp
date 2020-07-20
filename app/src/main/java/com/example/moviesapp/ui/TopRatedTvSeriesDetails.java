package com.example.moviesapp.ui;

import android.os.Bundle;
import android.view.View;
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
import com.example.moviesapp.Adapters.TopRatedSimilarTvAdapter;
import com.example.moviesapp.Adapters.TopRatedTvSeasonsAdapter;
import com.example.moviesapp.Adapters.TopRatedTvTrailerAdapter;
import com.example.moviesapp.Classes.TopRatedTvDetailsPresenter;
import com.example.moviesapp.Classes.TopRatedTvDetailsView;
import com.example.moviesapp.Models.TopRatedSimilarTvModel;
import com.example.moviesapp.Models.TopRatedTvDetailsModel;
import com.example.moviesapp.Models.TopRatedTvTrailerModel;
import com.example.moviesapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TopRatedTvSeriesDetails extends AppCompatActivity implements TopRatedTvDetailsView {

    @BindView(R.id.top_rated_detail_tv_cover)
    ImageView topRatedDetailTvCover;
    @BindView(R.id.top_rated_detail_tv_img)
    ImageView topRatedDetailTvImg;
    @BindView(R.id.top_rated_detail_tv_title)
    TextView topRatedDetailTvTitle;
    @BindView(R.id.top_rated_detail_tv_overview)
    TextView topRatedDetailTvOverview;
    @BindView(R.id.floatingActionButton)
    FloatingActionButton floatingActionButton;
    @BindView(R.id.top_rated_tv_view_1)
    View topRatedTvView1;
    @BindView(R.id.top_rated_tv_watchlist_button)
    Button topRatedTvWatchlistButton;
    @BindView(R.id.top_rated_tv_view_2)
    View topRatedTvView2;
    @BindView(R.id.top_rated_tv_rating)
    AppCompatTextView topRatedTvRating;
    @BindView(R.id.top_rated_tv_vote_count)
    AppCompatTextView topRatedTvVoteCount;
    @BindView(R.id.top_rated_tv_rate_this)
    TextView topRatedTvRateThis;
    @BindView(R.id.top_rated_tv_rating_bar)
    RatingBar topRatedTvRatingBar;
    @BindView(R.id.top_rated_tv_rate_linear_layout)
    LinearLayout topRatedTvRateLinearLayout;
    @BindView(R.id.top_rated_tv_linear_rate)
    LinearLayout topRatedTvLinearRate;
    @BindView(R.id.top_rated_tv_view_3)
    View topRatedTvView3;
    @BindView(R.id.top_rated_tv_images_text)
    TextView topRatedTvImagesText;
    @BindView(R.id.top_rated_similar_tv_rv)
    RecyclerView topRatedSimilarTvRv;
    @BindView(R.id.top_rated_similar_tv_card)
    CardView topRatedSimilarTvCard;
    @BindView(R.id.top_rated_tv_details_text)
    TextView topRatedTvDetailsText;
    @BindView(R.id.top_rated_tv_release_date)
    AppCompatTextView topRatedTvReleaseDate;
    @BindView(R.id.top_rated_tv_Home_page)
    AppCompatTextView topRatedTvHomePage;
    @BindView(R.id.top_rated_tv_details_card)
    CardView topRatedTvDetailsCard;
    @BindView(R.id.top_rated_tv_videos_text)
    TextView topRatedTvVideosText;
    @BindView(R.id.top_rated_tv_videos_rv)
    RecyclerView topRatedTvVideosRv;
    @BindView(R.id.top_rated_tv_videos_card)
    CardView topRatedTvVideosCard;
    @BindView(R.id.top_rated_tv_number_seasons)
    AppCompatTextView topRatedTvNumberSeasons;
    @BindView(R.id.top_rated_tv_number_episodes)
    AppCompatTextView topRatedTvNumberEpisodes;
    @BindView(R.id.top_rated_tv_seasons_rv)
    RecyclerView topRatedTvSeasonsRv;
    private int topRatedTvId;
    private TopRatedTvSeasonsAdapter tvSeasonsAdapter;
    private TopRatedTvDetailsPresenter presenter;
    private TopRatedTvSeasonsAdapter seasonsAdapter;
    private TopRatedSimilarTvAdapter similarTvAdapter;
    private TopRatedTvTrailerAdapter trailerAdapter;
    private List<TopRatedTvDetailsModel.SeasonsBean> seasonsBeanList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_rated_tv_series_details);
        ButterKnife.bind(this);

        topRatedTvId = getIntent().getIntExtra("topRatedTvId", 0);

        presenter = new TopRatedTvDetailsPresenter(this);
        presenter.getTopRatedTvDetails(topRatedTvId);
        presenter.getTopRatedSimilarTv(topRatedTvId);
        presenter.getTopRatedTrailersTv(topRatedTvId);

        topRatedTvSeasonsRv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        topRatedSimilarTvRv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        topRatedTvVideosRv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

    }

    @Override
    public void onGetTopRatedTvDetails(TopRatedTvDetailsModel model) {

        topRatedDetailTvTitle.setText(model.getOriginal_name());
        topRatedDetailTvOverview.setText(model.getOverview());
        topRatedTvRating.setText(String.valueOf(model.getVote_average()));
        topRatedTvVoteCount.setText(String.valueOf(model.getVote_count()));
        topRatedTvReleaseDate.setText(model.getFirst_air_date());
        topRatedTvHomePage.setText(model.getHomepage());
        topRatedTvNumberSeasons.setText(String.valueOf(model.getNumber_of_seasons()));
        topRatedTvNumberEpisodes.setText(String.valueOf(model.getNumber_of_episodes()));

        Glide.with(TopRatedTvSeriesDetails.this).load("https://image.tmdb.org/t/p/w500" + model.getPoster_path()).into(topRatedDetailTvImg);
        Glide.with(TopRatedTvSeriesDetails.this).load("https://image.tmdb.org/t/p/w500" + model.getPoster_path()).into(topRatedDetailTvCover);

        seasonsBeanList.addAll(model.getSeasons());
        seasonsAdapter = new TopRatedTvSeasonsAdapter(seasonsBeanList,TopRatedTvSeriesDetails.this);
        topRatedTvSeasonsRv.setAdapter(seasonsAdapter);
    }

    @Override
    public void onGetTopRatedSimilarTv(List<TopRatedSimilarTvModel.ResultsBean> sTvList) {

        similarTvAdapter = new TopRatedSimilarTvAdapter(sTvList,TopRatedTvSeriesDetails.this);
        topRatedSimilarTvRv.setAdapter(similarTvAdapter);

    }

    @Override
    public void onGetTopRatedTvTrailers(List<TopRatedTvTrailerModel.ResultsBean> pTvTrailerList) {

        trailerAdapter = new TopRatedTvTrailerAdapter(pTvTrailerList,TopRatedTvSeriesDetails.this);
        topRatedTvVideosRv.setAdapter(trailerAdapter);
    }
}
