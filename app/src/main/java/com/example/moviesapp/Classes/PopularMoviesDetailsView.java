package com.example.moviesapp.Classes;

import com.example.moviesapp.Models.PopularMoviesModel;
import com.example.moviesapp.Models.PopularMoviesTrailerModel;
import com.example.moviesapp.Models.PopularSimilarMoviesModel;

import java.util.List;

public interface PopularMoviesDetailsView {

    void onGetPopularSimilarMovies(List<PopularSimilarMoviesModel.ResultsBean> pSMoviesList);

    void onGetPopularMoviesTrailer(List<PopularMoviesTrailerModel.ResultsBean> pMTrailerList);
}
