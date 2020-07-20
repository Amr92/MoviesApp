package com.example.moviesapp.Classes;

import com.example.moviesapp.Models.PersonsSearchModel;
import com.example.moviesapp.Models.PopularCelebsModel;
import com.example.moviesapp.Models.PopularSimilarTvModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class PopularCelebsPresenter {

    PopularCelebsView view;
    private final String API_KEY = "56461395297741c1c0452f322385501c";
    private AsyncHttpClient client;
    private List<PersonsSearchModel.ResultsBean> celebsList = new ArrayList<>();

    public PopularCelebsPresenter(PopularCelebsView view) {
        this.view = view;
    }

    public void getPopularCelebs(){
        String SERVER_URL = "https://api.themoviedb.org/3/person/popular?api_key=" + API_KEY;
        client = new AsyncHttpClient();
        client.get(SERVER_URL, null, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

                Type dataType = new TypeToken<PersonsSearchModel>(){}.getType();
                PersonsSearchModel model = new Gson().fromJson(responseString,dataType);
                celebsList.addAll(model.getResults());
                view.OnGetPopularCelebs(celebsList);

            }
        });
    }
}
