package com.nurhidayaatt.favorite.presentation.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.nurhidayaatt.core.domain.usecase.MainUseCase

class FavoriteViewModel(mainUseCase: MainUseCase) : ViewModel() {

    var movies = mainUseCase.getFavoriteMovie().asLiveData()

    var tvShows = mainUseCase.getFavoriteTvShow().asLiveData()

}