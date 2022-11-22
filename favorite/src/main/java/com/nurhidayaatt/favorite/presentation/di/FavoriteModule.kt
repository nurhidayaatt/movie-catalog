package com.nurhidayaatt.favorite.presentation.di

import com.nurhidayaatt.favorite.presentation.favorite.FavoriteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel { FavoriteViewModel(get()) }
}