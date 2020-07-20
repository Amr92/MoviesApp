package com.example.moviesapp.Classes;

import com.example.moviesapp.Models.PopularSimilarTvModel;
import com.example.moviesapp.Models.PopularTvDetailsModel;
import com.example.moviesapp.Models.PopularTvTrailerModel;
import com.example.moviesapp.Models.TopRatedSimilarTvModel;
import com.example.moviesapp.Models.TopRatedTVSeries;
import com.example.moviesapp.Models.TopRatedTvDetailsModel;
import com.example.moviesapp.Models.TopRatedTvTrailerModel;

import java.util.List;

public interface TopRatedTvDetailsView {

    void onGetTopRatedTvDetails(TopRatedTvDetailsModel model);
    void onGetTopRatedSimilarTv(List<TopRatedSimilarTvModel.ResultsBean> sTvList);
    void onGetTopRatedTvTrailers(List<TopRatedTvTrailerModel.ResultsBean> pTvTrailerList);
}
