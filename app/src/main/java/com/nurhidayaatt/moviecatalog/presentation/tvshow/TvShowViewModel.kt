package com.nurhidayaatt.moviecatalog.presentation.tvshow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.nurhidayaatt.core.domain.usecase.MainUseCase
import com.nurhidayaatt.core.presentation.PreferencesManager
import com.nurhidayaatt.core.presentation.SortType
import kotlinx.coroutines.launch

class TvShowViewModel(private val mainUseCase: MainUseCase, private val preferencesManager: PreferencesManager) : ViewModel() {

    private val preferencesFlow = preferencesManager.preferencesFlow

    fun onSortOrderSelected(sortType: SortType) = viewModelScope.launch {
        preferencesManager.updateSortType(sortType)
    }

    val sortType get() = preferencesFlow.asLiveData()

    val tvShow = sortType.switchMap {
        mainUseCase.getAllTvShow(it.name).asLiveData()
    }
}