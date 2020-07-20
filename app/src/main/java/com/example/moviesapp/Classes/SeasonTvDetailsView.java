package com.example.moviesapp.Classes;

import com.example.moviesapp.Models.TvSeasonCastModel;
import com.example.moviesapp.Models.TvSeasonsDetailsModel;

import java.util.List;

public interface SeasonTvDetailsView {

    void onGetSeasonDetails(TvSeasonsDetailsModel tvSeasonsDetailsModel);
    void onGetSeasonCast(List<TvSeasonCastModel.CastBean> castBeanList);

}
