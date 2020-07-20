package com.example.moviesapp.Classes;

import com.example.moviesapp.Models.MoviesSearchModel;
import com.example.moviesapp.Models.PersonsSearchModel;
import com.example.moviesapp.Models.TvSeriesSearchModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class SearchPresenter {

    private SearchView view;
    private final String API_KEY = "56461395297741c1c0452f322385501c";
    private AsyncHttpClient client;
    private List<MoviesSearchModel.ResultsBean> searchMoviesList = new ArrayList<>();
    private List<PersonsSearchModel.ResultsBean> searchPersonsList = new ArrayList<>();
    private List<TvSeriesSearchModel.ResultsBean> searchTvList = new ArrayList<>();

    public SearchPresenter(SearchView view) {
        this.view = view;
    }

    public void getMoviesSearchResult(String moviesQuery){

        String SERVER_URL = "https://api.themoviedb.org/3/search/movie?api_key=" + API_KEY + "&query=" + moviesQuery;
        client = new AsyncHttpClient();
        client.get(SERVER_URL, null, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

                Type dataType = new TypeToken<MoviesSearchModel>(){}.getType();
                MoviesSearchModel model = new Gson().fromJson(responseString,dataType);
                searchMoviesList.clear();
                searchMoviesList.addAll(model.getResults());
                view.onGetSearchMovies(searchMoviesList);
            }
        });
    }

    public void getPersonsSearchResult(String personQuery){

        String SERVER_URL = "https://api.themoviedb.org/3/search/person?api_key=" + API_KEY + "&query=" + personQuery;
        client = new AsyncHttpClient();
        client.get(SERVER_URL, null, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

                Type dataType = new TypeToken<PersonsSearchModel>(){}.getType();
                PersonsSearchModel model = new Gson().fromJson(responseString,dataType);
                searchPersonsList.clear();
                searchPersonsList.addAll(model.getResults());
                view.onGetSearchPersons(searchPersonsList);
            }
        });
    }

    public void getTvSeriesSearchResult(String tvQuery){

        String SERVER_URL = "https://api.themoviedb.org/3/search/tv?api_key=" + API_KEY + "&query=" + tvQuery;
        client = new AsyncHttpClient();
        client.get(SERVER_URL, null, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

                Type dataType = new TypeToken<TvSeriesSearchModel>(){}.getType();
                TvSeriesSearchModel model = new Gson().fromJson(responseString,dataType);
                searchTvList.clear();
                searchTvList.addAll(model.getResults());
                view.onGetSearchTvSeries(searchTvList);
            }
        });
    }
}
