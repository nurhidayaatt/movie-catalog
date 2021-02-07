package com.nurhidayaatt.core.data.source.local

import com.nurhidayaatt.core.data.source.local.entity.MovieEntity
import com.nurhidayaatt.core.data.source.local.entity.TvShowEntity
import com.nurhidayaatt.core.data.source.local.room.MainDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val mainDao: MainDao) {

    fun getAllMovie(sortType: String): Flow<List<MovieEntity>> = mainDao.getAllMovie(sortType)

    suspend fun insertMovie(movieEntity: List<MovieEntity>) = mainDao.insertMovie(movieEntity)

    suspend fun setFavoriteMovie(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        mainDao.updateMovieFavorite(movie)
    }

    fun getMovieFavorite(): Flow<List<MovieEntity>> = mainDao.getFavoriteMovie()

    fun getAllTvShow(sortType: String): Flow<List<TvShowEntity>> = mainDao.getAllTvShow(sortType)

    suspend fun insertTvShow(tvShowEntity: List<TvShowEntity>) = mainDao.insertTvShow(tvShowEntity)

    suspend fun setFavoriteTvShow(tvShow: TvShowEntity, newState: Boolean) {
        tvShow.isFavorite = newState
        mainDao.updateTvShowFavorite(tvShow)
    }

    fun getTvShowFavorite(): Flow<List<TvShowEntity>> = mainDao.getFavoriteTvShow()
}