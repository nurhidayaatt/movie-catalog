package com.nurhidayaatt.core.domain.usecase

import com.nurhidayaatt.core.data.source.Resource
import com.nurhidayaatt.core.domain.model.Movie
import com.nurhidayaatt.core.domain.model.TvShow
import com.nurhidayaatt.core.domain.repository.IMainRepository
import kotlinx.coroutines.flow.Flow

class MainInteractor(private val mainRepository: IMainRepository): MainUseCase {
    override fun getAllMovie(sortType: String): Flow<Resource<List<Movie>>> = mainRepository.getAllMovie(sortType)
    override suspend fun setFavoriteMovie(movie: Movie, state: Boolean) = mainRepository.setFavoriteMovie(movie, state)
    override fun getFavoriteMovie(): Flow<Resource<List<Movie>>> = mainRepository.getMovieFavorite()
    override fun getAllTvShow(sortType: String): Flow<Resource<List<TvShow>>> = mainRepository.getAllTvShow(sortType)
    override suspend fun setFavoriteTvShow(tvShow: TvShow, state: Boolean) = mainRepository.setFavoriteTvShow(tvShow, state)
    override fun getFavoriteTvShow(): Flow<Resource<List<TvShow>>> = mainRepository.getTvShowFavorite()
}