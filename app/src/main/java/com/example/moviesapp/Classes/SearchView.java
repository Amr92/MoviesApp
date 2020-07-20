package com.example.moviesapp.Classes;

import com.example.moviesapp.Models.MoviesSearchModel;
import com.example.moviesapp.Models.PersonsSearchModel;
import com.example.moviesapp.Models.TvSeriesSearchModel;

import java.util.List;

public interface SearchView {

    void onGetSearchMovies(List<MoviesSearchModel.ResultsBean> sMoviesList);
    void onGetSearchPersons(List<PersonsSearchModel.ResultsBean> sPersonsList);
    void onGetSearchTvSeries(List<TvSeriesSearchModel.ResultsBean> sTvList);
}
