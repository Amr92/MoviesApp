package com.example.moviesapp.Classes;

import com.example.moviesapp.Models.PersonsSearchModel;
import com.example.moviesapp.Models.PopularCelebsModel;

import java.util.List;

public interface PopularCelebsView {

    void OnGetPopularCelebs(List<PersonsSearchModel.ResultsBean> celebsList);
}
