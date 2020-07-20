package com.example.moviesapp.Classes;

import com.example.moviesapp.Models.CelebsDetailsModel;
import com.example.moviesapp.Models.PersonImagesModel;

import java.util.List;

public interface CelebsDetailsView {

    void onGetCelebsDetails(CelebsDetailsModel cModel);
    void onGetCelebsImages(List<PersonImagesModel.PersonImagesProfiles> personImagesList);
}
