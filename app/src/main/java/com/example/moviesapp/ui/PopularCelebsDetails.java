package com.example.moviesapp.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviesapp.Adapters.KnownForAdapter;
import com.example.moviesapp.Classes.CelebsDetailsPresenter;
import com.example.moviesapp.Classes.CelebsDetailsView;
import com.example.moviesapp.Models.CelebsDetailsModel;
import com.example.moviesapp.Models.PersonImagesModel;
import com.example.moviesapp.Models.PopularCelebsModel;
import com.example.moviesapp.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PopularCelebsDetails extends AppCompatActivity implements CelebsDetailsView {

    @BindView(R.id.back_arrow_celebs)
    AppCompatImageView backArrowCelebs;
    @BindView(R.id.detail_celebs_cover)
    ImageView detailCelebsCover;
    @BindView(R.id.detail_celebs_img)
    ImageView detailCelebsImg;
    @BindView(R.id.detail_celebs_title)
    TextView detailCelebsTitle;
    @BindView(R.id.detail_celebs_overview)
    TextView detailCelebsOverview;
    @BindView(R.id.celebs_known_for_rv)
    RecyclerView celebsKnownForRv;
    @BindView(R.id.celebs_birth_date)
    AppCompatTextView celebsBirthDate;
    @BindView(R.id.celebs_place_birth)
    AppCompatTextView celebsPlaceBirth;
    @BindView(R.id.celebs_homepage)
    AppCompatTextView celebsHomepage;
    private List<PopularCelebsModel.ResultsBean.KnownForBean> knownForBeanList = new ArrayList<>();

    private int celebsId;
    private String knownForList;
    private CelebsDetailsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular_celebs_details);
        ButterKnife.bind(this);

        celebsId = getIntent().getIntExtra("popularCelebId", 0);

        presenter = new CelebsDetailsPresenter(this);
        presenter.getCelebsDetails(celebsId);

        backArrowCelebs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //knownForList = getIntent().getStringExtra("x");
        //Type dataType = new TypeToken<List<PopularCelebsModel.ResultsBean.KnownForBean>>(){}.getType();
        //knownForBeanList = new Gson().fromJson(knownForList,dataType);

        /*celebsKnownForRv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        KnownForAdapter adapter = new KnownForAdapter(knownForBeanList,this);
        celebsKnownForRv.setAdapter(adapter);*/

    }

    @Override
    public void onGetCelebsDetails(CelebsDetailsModel cModel) {

        detailCelebsTitle.setText(cModel.getName());
        detailCelebsOverview.setText(cModel.getBiography());
        celebsBirthDate.setText(cModel.getBirthday());
        celebsPlaceBirth.setText(cModel.getPlace_of_birth());

        Glide.with(PopularCelebsDetails.this).load("https://image.tmdb.org/t/p/w500" + cModel.getProfile_path()).into(detailCelebsImg);
        Glide.with(PopularCelebsDetails.this).load("https://image.tmdb.org/t/p/w500" + cModel.getProfile_path()).into(detailCelebsCover);

    }

    @Override
    public void onGetCelebsImages(List<PersonImagesModel.PersonImagesProfiles> personImagesList) {

    }

}
