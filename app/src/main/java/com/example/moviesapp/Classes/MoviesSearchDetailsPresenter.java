package com.example.moviesapp.Classes;

import com.example.moviesapp.Models.MovieImagesModel;
import com.example.moviesapp.Models.MoviesCastModel;
import com.example.moviesapp.Models.PopularMovieDetailsModel;
import com.example.moviesapp.Models.PopularMoviesTrailerModel;
import com.example.moviesapp.Models.PopularSimilarMoviesModel;
import com.example.moviesapp.ui.PopularMoviesDetails;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class MoviesSearchDetailsPresenter {

    private MoviesSearchDetailsView view;
    private final String API_KEY = "56461395297741c1c0452f322385501c";
    private AsyncHttpClient client;
    private List<MoviesCastModel.CastBean> castList = new ArrayList<>();
    private List<PopularSimilarMoviesModel.ResultsBean> sMList = new ArrayList<>();
    private List<MovieImagesModel.MovieImagesBackdrops> postersList = new ArrayList<>();
    private List<PopularMoviesTrailerModel.ResultsBean> trailerList = new ArrayList<>();

    public MoviesSearchDetailsPresenter(MoviesSearchDetailsView view) {
        this.view = view;
    }

    public void getMoviesSearchDetails(int movieId){

        String SERVER_URL = "https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" + API_KEY;
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(SERVER_URL, null, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

                Type dataType = new TypeToken<PopularMovieDetailsModel>() {}.getType();
                PopularMovieDetailsModel model = new Gson().fromJson(responseString,dataType);
                view.onGetSearchMoviesDetails(model);
            }
        });
    }

    public void getMoviesCast(int movie_id){
        String SERVER_URL = "https://api.themoviedb.org/3/movie/" + movie_id + "/credits?api_key=" + API_KEY;
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(SERVER_URL, null, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

                Type dataType = new TypeToken<MoviesCastModel>() {}.getType();
                MoviesCastModel model = new Gson().fromJson(responseString,dataType);
                castList.addAll(model.getCast());
                view.onGetMoviesCast(castList);
            }
        });
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

    public void getMoviesPosters(int movie_id){
        String SERVER_URL = "https://api.themoviedb.org/3/movie/" + movie_id + "/images?api_key=" + API_KEY;
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(SERVER_URL, null, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

                Type dataType = new TypeToken<MovieImagesModel>() {}.getType();
                MovieImagesModel model = new Gson().fromJson(responseString,dataType);
                postersList.addAll(model.getPosters());
                view.onGetMoviesImages(postersList);
            }
        });
    }

    public void getMoviesTrailers(int movie_id){
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
                view.onGetMovieTrailers(trailerList);
            }
        });
    }
}
