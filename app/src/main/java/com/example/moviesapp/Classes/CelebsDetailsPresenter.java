package com.example.moviesapp.Classes;

import com.example.moviesapp.Models.CelebsDetailsModel;
import com.example.moviesapp.Models.PersonImagesModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class CelebsDetailsPresenter {

    CelebsDetailsView view;
    private AsyncHttpClient client;
    private final String API_KEY = "56461395297741c1c0452f322385501c";
    private List<PersonImagesModel.PersonImagesProfiles> profiles = new ArrayList<>();

    public CelebsDetailsPresenter(CelebsDetailsView view) {
        this.view = view;
    }

    public void getCelebsDetails(int celebId){
        String personId = String.valueOf(celebId);
        String SERVER_URL = "https://api.themoviedb.org/3/person/" + personId + "?api_key=" + API_KEY;
        client = new AsyncHttpClient();
        client.get(SERVER_URL, null, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

                Type dataType = new TypeToken<CelebsDetailsModel>(){}.getType();
                CelebsDetailsModel model = new Gson().fromJson(responseString,dataType);
                view.onGetCelebsDetails(model);

            }
        });
    }

    public void getCelebsImages(int celebId){
        String personId = String.valueOf(celebId);
        String SERVER_URL = "https://api.themoviedb.org/3/person/" + personId + "/images?api_key=" + API_KEY;
        client = new AsyncHttpClient();
        client.get(SERVER_URL, null, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

                Type dataType = new TypeToken<PersonImagesModel>(){}.getType();
                PersonImagesModel model = new Gson().fromJson(responseString,dataType);
                profiles.addAll(model.getProfiles());
                view.onGetCelebsImages(profiles);

            }
        });
    }

}
