package com.nurhidayaatt.moviecatalog.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nurhidayaatt.core.domain.model.Movie
import com.nurhidayaatt.core.domain.model.TvShow
import com.nurhidayaatt.core.domain.usecase.MainUseCase
import kotlinx.coroutines.launch

class DetailViewModel(private val mainUseCase: MainUseCase) : ViewModel() {

    fun setFavoriteMovie(movie: Movie, state: Boolean) = viewModelScope.launch {
        mainUseCase.setFavoriteMovie(movie, state)
    }

    fun setFavoriteTvShow(tvShow: TvShow, state: Boolean) = viewModelScope.launch {
        mainUseCase.setFavoriteTvShow(tvShow, state)
    }
}