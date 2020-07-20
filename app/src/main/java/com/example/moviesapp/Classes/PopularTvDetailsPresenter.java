package com.example.moviesapp.Classes;

import com.example.moviesapp.Models.MovieImagesModel;
import com.example.moviesapp.Models.MoviesCastModel;
import com.example.moviesapp.Models.PopularSimilarTvModel;
import com.example.moviesapp.Models.PopularTvDetailsModel;
import com.example.moviesapp.Models.PopularTvTrailerModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class PopularTvDetailsPresenter {

    PopularTvDetailsView view;
    private final String API_KEY = "56461395297741c1c0452f322385501c";
    private AsyncHttpClient client;
    private List<PopularSimilarTvModel.ResultsBean> sTvList = new ArrayList<>();
    private List<PopularTvTrailerModel.ResultsBean> tvTrailerList = new ArrayList<>();
    private List<MoviesCastModel.CastBean> tvCastList = new ArrayList<>();
    private List<MovieImagesModel.MovieImagesBackdrops> postersList = new ArrayList<>();

    public PopularTvDetailsPresenter(PopularTvDetailsView view) {
        this.view = view;
    }

    public void getPopularTvDetails(int movieId){
        String movie_id = String.valueOf(movieId);
        String SERVER_URL = "https://api.themoviedb.org/3/tv/" + movie_id + "?api_key=" + API_KEY;
        client = new AsyncHttpClient();
        client.get(SERVER_URL, null, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

                Type dataType = new TypeToken<PopularTvDetailsModel>(){}.getType();
                PopularTvDetailsModel model = new Gson().fromJson(responseString,dataType);
                view.onGetPopularTvDetails(model);

            }
        });
    }

    public void getPopularTvCast(int movieId){
        String movie_id = String.valueOf(movieId);
        String SERVER_URL = "https://api.themoviedb.org/3/tv/" + movie_id + "/credits?api_key=" + API_KEY;
        client = new AsyncHttpClient();
        client.get(SERVER_URL, null, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

                Type dataType = new TypeToken<MoviesCastModel>(){}.getType();
                MoviesCastModel model = new Gson().fromJson(responseString,dataType);
                tvCastList.addAll(model.getCast());
                view.onGetTvSeriesCast(tvCastList);

            }
        });
    }

    public void getPopularSimilarTv(int movieId){
        String movie_id = String.valueOf(movieId);
        String SERVER_URL = "https://api.themoviedb.org/3/tv/" + movie_id + "/similar?api_key=" + API_KEY;
        client = new AsyncHttpClient();
        client.get(SERVER_URL, null, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

                Type dataType = new TypeToken<PopularSimilarTvModel>(){}.getType();
                PopularSimilarTvModel model = new Gson().fromJson(responseString,dataType);
                sTvList.addAll(model.getResults());
                view.onGetPopularSimilarTv(sTvList);

            }
        });
    }

    public void getTvSeriesImages(int movieId){
        String movie_id = String.valueOf(movieId);
        String SERVER_URL = "https://api.themoviedb.org/3/tv/" + movie_id + "/images?api_key=" + API_KEY;
        client = new AsyncHttpClient();
        client.get(SERVER_URL, null, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

                Type dataType = new TypeToken<MovieImagesModel>(){}.getType();
                MovieImagesModel model = new Gson().fromJson(responseString,dataType);
                postersList.addAll(model.getPosters());
                view.onGetTvImages(postersList);

            }
        });
    }

    public void getPopularTrailersTv(int movieId){
        String movie_id = String.valueOf(movieId);
        String SERVER_URL = "https://api.themoviedb.org/3/tv/" + movie_id + "/videos?api_key=" + API_KEY;
        client = new AsyncHttpClient();
        client.get(SERVER_URL, null, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

                Type dataType = new TypeToken<PopularTvTrailerModel>(){}.getType();
                PopularTvTrailerModel model = new Gson().fromJson(responseString,dataType);
                tvTrailerList.addAll(model.getResults());
                view.onGetPopularTvTrailers(tvTrailerList);

            }
        });
    }




}
