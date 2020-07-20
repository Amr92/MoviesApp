package com.example.moviesapp.Classes;

import com.example.moviesapp.Models.PopularMovieDetailsModel;
import com.example.moviesapp.Models.PopularTvDetailsModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import java.lang.reflect.Type;

import cz.msebera.android.httpclient.Header;

public class SimilarMoviesDetailsPresenter {

    SimilarDetailsView view;
    private final String API_KEY = "56461395297741c1c0452f322385501c";
    private AsyncHttpClient client;

    public SimilarMoviesDetailsPresenter(SimilarDetailsView view) {
        this.view = view;
    }

    public void getSimilarMoviesDetails(int movieId){
        String movie_id = String.valueOf(movieId);
        String SERVER_URL = "https://api.themoviedb.org/3/movie/" + movie_id + "?api_key=" + API_KEY;
        client = new AsyncHttpClient();
        client.get(SERVER_URL, null, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

                Type dataType = new TypeToken<PopularMovieDetailsModel>(){}.getType();
                PopularMovieDetailsModel model = new Gson().fromJson(responseString,dataType);
                view.onGetSimilarMoviesDetails(model);

            }
        });
    }
}
