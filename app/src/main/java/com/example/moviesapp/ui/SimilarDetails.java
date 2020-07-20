package com.example.moviesapp.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.example.moviesapp.Classes.SimilarDetailsView;
import com.example.moviesapp.Classes.SimilarMoviesDetailsPresenter;
import com.example.moviesapp.Models.PopularMovieDetailsModel;
import com.example.moviesapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SimilarDetails extends AppCompatActivity implements SimilarDetailsView {

    @BindView(R.id.similar_detail_movies_cover)
    ImageView similarDetailMoviesCover;
    @BindView(R.id.similar_detail_movies_img)
    ImageView similarDetailMoviesImg;
    @BindView(R.id.similar_detail_movies_title)
    TextView similarDetailMoviesTitle;
    @BindView(R.id.similar_detail_movie_overview)
    TextView similarDetailMovieOverview;
    @BindView(R.id.floatingActionButton_similar_detail)
    FloatingActionButton floatingActionButtonSimilarDetail;
    @BindView(R.id.similar_movies_view_1)
    View similarMoviesView1;
    @BindView(R.id.similar_detail_watchlist_button)
    Button similarDetailWatchlistButton;
    @BindView(R.id.similar_detail_view_2)
    View similarDetailView2;
    @BindView(R.id.similar_detail_rating)
    AppCompatTextView similarDetailsRating;
    @BindView(R.id.similar_detail_vote_count)
    AppCompatTextView similarDetailVoteCount;
    @BindView(R.id.similar_detail_linear_rate)
    LinearLayout similarDetailLinearRate;
    @BindView(R.id.similar_movies_details_text)
    TextView similarMoviesDetailsText;
    @BindView(R.id.similar_detail_release_date)
    AppCompatTextView similarDetailReleaseDate;
    @BindView(R.id.similar_detail_Home_page)
    AppCompatTextView similarDetailHomePage;
    @BindView(R.id.similar_detail_language_spoken)
    AppCompatTextView similarDetailLanguageSpoken;
    @BindView(R.id.similar_detail_runtime)
    AppCompatTextView similarDetailRuntime;
    @BindView(R.id.similar_movies_details_card)
    CardView similarMoviesDetailsCard;
    @BindView(R.id.back_arrow)
    AppCompatImageView backArrow;

    private SimilarMoviesDetailsPresenter presenter;
    private int similarMoviesId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_similar_details);
        ButterKnife.bind(this);

        similarMoviesId = getIntent().getIntExtra("similarMovieId", 0);

        presenter = new SimilarMoviesDetailsPresenter(this);
        presenter.getSimilarMoviesDetails(similarMoviesId);

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }

    @Override
    public void onGetSimilarMoviesDetails(PopularMovieDetailsModel popularMovieDetailsModel) {

        similarDetailMoviesTitle.setText(popularMovieDetailsModel.getTitle());
        similarDetailMovieOverview.setText(popularMovieDetailsModel.getOverview());
        similarDetailReleaseDate.setText(popularMovieDetailsModel.getRelease_date());
        similarDetailHomePage.setText(popularMovieDetailsModel.getHomepage());
        similarDetailLanguageSpoken.setText(popularMovieDetailsModel.getOriginal_language());
        similarDetailsRating.setText(String.valueOf(popularMovieDetailsModel.getVote_average()));
        similarDetailVoteCount.setText(String.valueOf(popularMovieDetailsModel.getVote_count()));
        Glide.with(SimilarDetails.this).load("https://image.tmdb.org/t/p/w500" + popularMovieDetailsModel.getPoster_path()).into(similarDetailMoviesImg);
        Glide.with(SimilarDetails.this).load("https://image.tmdb.org/t/p/w500" + popularMovieDetailsModel.getBackdrop_path()).into(similarDetailMoviesCover);

    }
}
