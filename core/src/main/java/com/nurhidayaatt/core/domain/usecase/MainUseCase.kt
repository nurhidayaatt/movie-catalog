package com.nurhidayaatt.core.domain.usecase

import com.nurhidayaatt.core.data.source.Resource
import com.nurhidayaatt.core.domain.model.Movie
import com.nurhidayaatt.core.domain.model.TvShow
import kotlinx.coroutines.flow.Flow

interface MainUseCase {
    fun getAllMovie(sortType: String): Flow<Resource<List<Movie>>>
    suspend fun setFavoriteMovie(movie: Movie, state: Boolean)
    fun getFavoriteMovie(): Flow<Resource<List<Movie>>>
    fun getAllTvShow(sortType: String): Flow<Resource<List<TvShow>>>
    suspend fun setFavoriteTvShow(tvShow: TvShow, state: Boolean)
    fun getFavoriteTvShow(): Flow<Resource<List<TvShow>>>
}