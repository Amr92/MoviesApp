package com.example.moviesapp.Classes;

import com.example.moviesapp.Models.PopularSimilarTvModel;
import com.example.moviesapp.Models.PopularTvDetailsModel;
import com.example.moviesapp.Models.PopularTvTrailerModel;
import com.example.moviesapp.Models.TopRatedSimilarTvModel;
import com.example.moviesapp.Models.TopRatedTvDetailsModel;
import com.example.moviesapp.Models.TopRatedTvTrailerModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class TopRatedTvDetailsPresenter {

    TopRatedTvDetailsView view;
    private final String API_KEY = "56461395297741c1c0452f322385501c";
    private AsyncHttpClient client;
    private List<TopRatedSimilarTvModel.ResultsBean> sTvList = new ArrayList<>();
    private List<TopRatedTvTrailerModel.ResultsBean> tvTrailerList = new ArrayList<>();

    public TopRatedTvDetailsPresenter(TopRatedTvDetailsView view) {
        this.view = view;
    }

    public void getTopRatedTvDetails(int movieId){
        String movie_id = String.valueOf(movieId);
        String SERVER_URL = "https://api.themoviedb.org/3/tv/" + movie_id + "?api_key=" + API_KEY;
        client = new AsyncHttpClient();
        client.get(SERVER_URL, null, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

                Type dataType = new TypeToken<TopRatedTvDetailsModel>(){}.getType();
                TopRatedTvDetailsModel model = new Gson().fromJson(responseString,dataType);
                view.onGetTopRatedTvDetails(model);

            }
        });
    }

    public void getTopRatedSimilarTv(int movieId){
        String movie_id = String.valueOf(movieId);
        String SERVER_URL = "https://api.themoviedb.org/3/tv/" + movie_id + "/similar?api_key=" + API_KEY;
        client = new AsyncHttpClient();
        client.get(SERVER_URL, null, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

                Type dataType = new TypeToken<TopRatedSimilarTvModel>(){}.getType();
                TopRatedSimilarTvModel model = new Gson().fromJson(responseString,dataType);
                sTvList.addAll(model.getResults());
                view.onGetTopRatedSimilarTv(sTvList);

            }
        });
    }

    public void getTopRatedTrailersTv(int movieId){
        String movie_id = String.valueOf(movieId);
        String SERVER_URL = "https://api.themoviedb.org/3/tv/" + movie_id + "/videos?api_key=" + API_KEY;
        client = new AsyncHttpClient();
        client.get(SERVER_URL, null, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

                Type dataType = new TypeToken<TopRatedTvTrailerModel>(){}.getType();
                TopRatedTvTrailerModel model = new Gson().fromJson(responseString,dataType);
                tvTrailerList.addAll(model.getResults());
                view.onGetTopRatedTvTrailers(tvTrailerList);

            }
        });
    }




}
