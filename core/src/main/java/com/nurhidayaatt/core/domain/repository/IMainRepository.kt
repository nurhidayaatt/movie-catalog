package com.nurhidayaatt.core.domain.repository

import com.nurhidayaatt.core.data.source.Resource
import com.nurhidayaatt.core.domain.model.Movie
import com.nurhidayaatt.core.domain.model.TvShow
import kotlinx.coroutines.flow.Flow

interface IMainRepository {
    fun getAllMovie(sortType: String): Flow<Resource<List<Movie>>>
    suspend fun setFavoriteMovie(movie: Movie, state: Boolean)
    fun getMovieFavorite(): Flow<Resource<List<Movie>>>
    fun getAllTvShow(sortType: String): Flow<Resource<List<TvShow>>>
    suspend fun setFavoriteTvShow(tvShow: TvShow, state: Boolean)
    fun getTvShowFavorite(): Flow<Resource<List<TvShow>>>
}