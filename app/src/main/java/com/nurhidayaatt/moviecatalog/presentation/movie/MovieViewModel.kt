package com.nurhidayaatt.moviecatalog.presentation.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.nurhidayaatt.core.domain.usecase.MainUseCase
import com.nurhidayaatt.core.presentation.PreferencesManager
import com.nurhidayaatt.core.presentation.SortType
import kotlinx.coroutines.launch

class MovieViewModel(private val mainUseCase: MainUseCase, private val preferencesManager: PreferencesManager): ViewModel() {

    private val preferencesFlow = preferencesManager.preferencesFlow

    fun onSortOrderSelected(sortType: SortType) = viewModelScope.launch {
        preferencesManager.updateSortType(sortType)
    }

    val sortType get() = preferencesFlow.asLiveData()

    val movie = sortType.switchMap {
        mainUseCase.getAllMovie(it.name).asLiveData()
    }
}