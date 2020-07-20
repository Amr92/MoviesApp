package com.example.moviesapp.Classes;

import com.example.moviesapp.Models.PopularMoviesTrailerModel;
import com.example.moviesapp.Models.PopularSimilarMoviesModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class PopularMoviesDetailsPresenter {


    PopularMoviesDetailsView view;
    private final String API_KEY = "56461395297741c1c0452f322385501c";
    private List<PopularSimilarMoviesModel.ResultsBean> sMList = new ArrayList<>();
    private List<PopularMoviesTrailerModel.ResultsBean> trailerList = new ArrayList<>();

    public PopularMoviesDetailsPresenter(PopularMoviesDetailsView view) {
        this.view = view;
    }

    public void getPopularSimilarMoviesData(final int movie_id) {
        String SERVER_URL = "https://api.themoviedb.org/3/movie/" + movie_id + "/similar?api_key=" + API_KEY;
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(SERVER_URL, null, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

                Type dataType = new TypeToken<PopularSimilarMoviesModel>() {}.getType();
                PopularSimilarMoviesModel model = new Gson().fromJson(responseString,dataType);
                sMList.addAll(model.getResults());
                view.onGetPopularSimilarMovies(sMList);
            }
        });

    }

    public void getPopularMoviesTrailers(final int movie_id) {

        String SERVER_URL = "https://api.themoviedb.org/3/movie/" + movie_id + "/videos?api_key=" + API_KEY;
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(SERVER_URL, null, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

                Type dataType = new TypeToken<PopularMoviesTrailerModel>() {}.getType();
                PopularMoviesTrailerModel model = new Gson().fromJson(responseString,dataType);
                trailerList.addAll(model.getResults());
                view.onGetPopularMoviesTrailer(trailerList);
            }
        });

    }



}
