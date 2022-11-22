package com.nurhidayaatt.moviecatalog.presentation.di

import com.nurhidayaatt.core.domain.usecase.MainInteractor
import com.nurhidayaatt.core.domain.usecase.MainUseCase
import com.nurhidayaatt.core.presentation.PreferencesManager
import com.nurhidayaatt.moviecatalog.presentation.detail.DetailViewModel
import com.nurhidayaatt.moviecatalog.presentation.movie.MovieViewModel
import com.nurhidayaatt.moviecatalog.presentation.tvshow.TvShowViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MainUseCase> { MainInteractor(get()) }
}

val viewModelModule = module {
    single { PreferencesManager(androidContext()) }
        viewModel { MovieViewModel(get(), get()) }
        viewModel { TvShowViewModel(get(), get()) }
        viewModel { DetailViewModel(get()) }
}