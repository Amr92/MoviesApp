package com.example.moviesapp.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
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
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PopularTvSeriesDetails extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);



    }
}
