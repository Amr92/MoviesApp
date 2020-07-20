package com.example.moviesapp.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviesapp.Adapters.KnownForAdapter;
import com.example.moviesapp.Adapters.PersonProfileImagesAdapter;
import com.example.moviesapp.Classes.CelebsDetailsPresenter;
import com.example.moviesapp.Classes.CelebsDetailsView;
import com.example.moviesapp.Models.CelebsDetailsModel;
import com.example.moviesapp.Models.PersonImagesModel;
import com.example.moviesapp.Models.PersonsSearchModel;
import com.example.moviesapp.Models.PopularCelebsModel;
import com.example.moviesapp.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PersonSearchDetails extends AppCompatActivity implements CelebsDetailsView {

    @BindView(R.id.back_arrow_celebs)
    AppCompatImageView backArrowCelebs;
    @BindView(R.id.person_detail_profile_image)
    AppCompatImageView personDetailProfileImage;
    @BindView(R.id.person_detail_name)
    AppCompatTextView personDetailName;
    @BindView(R.id.person_detail_also_known_as)
    AppCompatTextView personDetailAlsoKnownAs;
    @BindView(R.id.person_detail_birthday)
    AppCompatTextView personDetailBirthday;
    @BindView(R.id.person_detail_place_of_birth)
    AppCompatTextView personDetailPlaceOfBirth;
    @BindView(R.id.person_detail_known_for_department)
    AppCompatTextView personDetailKnownForDepartment;
    @BindView(R.id.person_detail_homepage)
    AppCompatTextView personDetailHomepage;
    @BindView(R.id.person_detail_biography)
    AppCompatTextView personDetailBiography;
    @BindView(R.id.person_detail_also_known_as_layout)
    LinearLayoutCompat personDetailAlsoKnownAsLayout;
    @BindView(R.id.person_detail_birthday_layout)
    LinearLayoutCompat personDetailBirthdayLayout;
    @BindView(R.id.person_detail_place_of_birth_layout)
    LinearLayoutCompat personDetailPlaceOfBirthLayout;
    @BindView(R.id.person_detail_known_for_department_layout)
    LinearLayoutCompat personDetailKnownForDepartmentLayout;
    @BindView(R.id.person_detail_homepage_layout)
    LinearLayoutCompat personDetailHomepageLayout;
    @BindView(R.id.person_detail_biography_layout)
    LinearLayoutCompat personDetailBiographyLayout;
    @BindView(R.id.person_detail_profile_images_rv)
    RecyclerView personDetailProfileImagesRv;
    @BindView(R.id.person_detail_profile_images_layout)
    LinearLayoutCompat personDetailProfileImagesLayout;
    @BindView(R.id.person_detail_known_for_rv)
    RecyclerView personDetailKnownForRv;
    @BindView(R.id.person_detail_known_for_layout)
    LinearLayoutCompat personDetailKnownForLayout;
    private int personId;
    private String knownFor;
    private PersonProfileImagesAdapter adapter;
    private CelebsDetailsPresenter presenter;
    private KnownForAdapter knownForAdapter;
    private List<PopularCelebsModel.ResultsBean.KnownForBean> knownForList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_search_details);
        ButterKnife.bind(this);

        personId = getIntent().getIntExtra("personId", 0);

        knownFor = getIntent().getStringExtra("x");
        Type dataType = new TypeToken<List<PopularCelebsModel.ResultsBean.KnownForBean>>() {
        }.getType();
        knownForList = new Gson().fromJson(knownFor, dataType);
        Log.d("star", String.valueOf(knownForList));

        personDetailProfileImagesRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        personDetailKnownForRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        if(knownForList != null && knownForList.size() > 0){
            personDetailKnownForLayout.setVisibility(View.VISIBLE);
            knownForAdapter = new KnownForAdapter(knownForList, this);
            personDetailKnownForRv.setAdapter(knownForAdapter);
        }
        else{
            personDetailKnownForLayout.setVisibility(View.GONE);
        }

        presenter = new CelebsDetailsPresenter(this);
        presenter.getCelebsDetails(personId);
        presenter.getCelebsImages(personId);

        backArrowCelebs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onGetCelebsDetails(CelebsDetailsModel cModel) {

        List<String> knownAs = cModel.getAlso_known_as();

        if (cModel.getName() != null) {
            if (cModel.getName().length() > 0) {
                personDetailName.setText(cModel.getName());
                personDetailName.setVisibility(View.VISIBLE);
            } else {
                personDetailName.setVisibility(View.GONE);
            }
        } else {
            personDetailName.setVisibility(View.GONE);
        }

        if (cModel.getBirthday() != null) {
            if (cModel.getName().length() > 0) {
                personDetailBirthday.setText(cModel.getBirthday());
                personDetailBirthdayLayout.setVisibility(View.VISIBLE);
            } else {
                personDetailBirthdayLayout.setVisibility(View.GONE);
            }
        } else {
            personDetailBirthdayLayout.setVisibility(View.GONE);
        }

        if (cModel.getPlace_of_birth() != null) {
            if (cModel.getPlace_of_birth().length() > 0) {
                personDetailPlaceOfBirth.setText(cModel.getPlace_of_birth());
                personDetailPlaceOfBirthLayout.setVisibility(View.VISIBLE);
            } else {
                personDetailPlaceOfBirthLayout.setVisibility(View.GONE);
            }
        } else {
            personDetailPlaceOfBirthLayout.setVisibility(View.GONE);
        }

        if (cModel.getKnown_for_department() != null) {
            if (cModel.getImdb_id().length() > 0) {
                personDetailKnownForDepartment.setText(cModel.getKnown_for_department());
                personDetailKnownForDepartmentLayout.setVisibility(View.VISIBLE);
            } else {
                personDetailKnownForDepartmentLayout.setVisibility(View.GONE);
            }
        } else {
            personDetailKnownForDepartmentLayout.setVisibility(View.GONE);
        }

        if (cModel.getBiography() != null) {
            if (cModel.getBiography().length() > 0) {
                personDetailBiography.setText(cModel.getBiography());
                personDetailBiographyLayout.setVisibility(View.VISIBLE);
            } else {
                personDetailBiographyLayout.setVisibility(View.GONE);
            }
        } else {
            personDetailBiographyLayout.setVisibility(View.GONE);
        }

        if (cModel.getHomepage() != null) {
            if (cModel.getHomepage().length() > 0) {
                personDetailHomepage.setText(cModel.getHomepage());
                personDetailHomepageLayout.setVisibility(View.VISIBLE);
            } else {
                personDetailHomepageLayout.setVisibility(View.GONE);
            }
        } else {
            personDetailHomepageLayout.setVisibility(View.GONE);
        }

        if (knownAs != null) {

            if (knownAs.size() > 0) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < knownAs.size(); i++) {

                    if (i == knownAs.size() - 1) {
                        stringBuilder.append(knownAs.get(i));
                    } else {
                        stringBuilder.append(knownAs.get(i)).append(", ");
                    }
                }
                personDetailAlsoKnownAs.setText(stringBuilder.toString());
                personDetailAlsoKnownAsLayout.setVisibility(View.VISIBLE);
            } else {
                personDetailAlsoKnownAsLayout.setVisibility(View.GONE);
            }
        } else {
            personDetailAlsoKnownAsLayout.setVisibility(View.GONE);
        }


        Glide.with(PersonSearchDetails.this).load("https://image.tmdb.org/t/p/w500" + cModel.getProfile_path()).into(personDetailProfileImage);

    }

    @Override
    public void onGetCelebsImages(List<PersonImagesModel.PersonImagesProfiles> personImagesList) {

        if (personImagesList != null && personImagesList.size() > 0) {

            personDetailProfileImagesLayout.setVisibility(View.VISIBLE);
            adapter = new PersonProfileImagesAdapter(personImagesList, PersonSearchDetails.this);
            personDetailProfileImagesRv.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        } else {
            personDetailProfileImagesLayout.setVisibility(View.GONE);
        }


    }
}
