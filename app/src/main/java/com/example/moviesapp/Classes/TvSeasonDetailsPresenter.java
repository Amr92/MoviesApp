package com.example.moviesapp.Classes;

import com.example.moviesapp.Classes.SeasonTvDetailsView;
import com.example.moviesapp.Models.TvSeasonCastModel;
import com.example.moviesapp.Models.TvSeasonsDetailsModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class TvSeasonDetailsPresenter {

    private SeasonTvDetailsView view;
    private AsyncHttpClient client;
    private final String API_KEY = "56461395297741c1c0452f322385501c";
    private List<TvSeasonCastModel.CastBean> castBeanList = new ArrayList<>();


    public TvSeasonDetailsPresenter(SeasonTvDetailsView view) {
        this.view = view;
    }

    public void getSeasonDetails(int tvId,int seasonNum){
        String SERVER_URL = "https://api.themoviedb.org/3/tv/" + tvId + "/season/" + seasonNum + "?api_key=" + API_KEY;
        client = new AsyncHttpClient();
        client.get(SERVER_URL, null, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

                Type dataType = new TypeToken<TvSeasonsDetailsModel>(){}.getType();
                TvSeasonsDetailsModel model = new Gson().fromJson(responseString,dataType);
                view.onGetSeasonDetails(model);
            }
        });
    }


    public void getSeasonCast(int tvId,int seasonNum){
        String SERVER_URL = "https://api.themoviedb.org/3/tv/" + tvId + "/season/" + seasonNum + "/credits?api_key=" + API_KEY;
        client = new AsyncHttpClient();
        client.get(SERVER_URL, null, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

                Type dataType = new TypeToken<TvSeasonCastModel>(){}.getType();
                TvSeasonCastModel model = new Gson().fromJson(responseString,dataType);
                castBeanList.addAll(model.getCast());
                view.onGetSeasonCast(castBeanList);
            }
        });
    }
}
