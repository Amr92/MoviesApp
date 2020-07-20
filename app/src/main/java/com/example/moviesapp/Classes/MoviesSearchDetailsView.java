package com.example.moviesapp.Classes;

import com.example.moviesapp.Models.MovieImagesModel;
import com.example.moviesapp.Models.MoviesCastModel;
import com.example.moviesapp.Models.PopularMovieDetailsModel;
import com.example.moviesapp.Models.PopularMoviesTrailerModel;
import com.example.moviesapp.Models.PopularSimilarMoviesModel;
import com.example.moviesapp.ui.PopularMoviesDetails;

import java.util.List;

public interface MoviesSearchDetailsView {

    void onGetSearchMoviesDetails(PopularMovieDetailsModel popularMovieDetailsModel);
    void onGetMoviesCast(List<MoviesCastModel.CastBean> castList);
    void onGetPopularSimilarMovies(List<PopularSimilarMoviesModel.ResultsBean> sMList);
    void onGetMoviesImages(List<MovieImagesModel.MovieImagesBackdrops> postersList);
    void onGetMovieTrailers(List<PopularMoviesTrailerModel.ResultsBean> trailerList);
}
