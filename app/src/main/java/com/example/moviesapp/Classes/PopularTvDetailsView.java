package com.example.moviesapp.Classes;

import com.example.moviesapp.Models.MovieImagesModel;
import com.example.moviesapp.Models.MoviesCastModel;
import com.example.moviesapp.Models.PopularSimilarTvModel;
import com.example.moviesapp.Models.PopularTvDetailsModel;
import com.example.moviesapp.Models.PopularTvTrailerModel;

import java.util.List;

public interface PopularTvDetailsView {

    void onGetPopularTvDetails(PopularTvDetailsModel model);
    void onGetTvSeriesCast(List<MoviesCastModel.CastBean> tvCastList);
    void onGetPopularSimilarTv(List<PopularSimilarTvModel.ResultsBean> sTvList);
    void onGetTvImages(List<MovieImagesModel.MovieImagesBackdrops> tvPostersList);
    void onGetPopularTvTrailers(List<PopularTvTrailerModel.ResultsBean> pTvTrailerList);
}
