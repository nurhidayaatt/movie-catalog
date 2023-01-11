package com.nurhidayaatt.core.data.source

import com.nurhidayaatt.core.data.source.local.LocalDataSource
import com.nurhidayaatt.core.data.source.remote.RemoteDataSource
import com.nurhidayaatt.core.data.source.remote.network.ApiResponse
import com.nurhidayaatt.core.data.source.remote.response.movie.MovieResponse
import com.nurhidayaatt.core.data.source.remote.response.tvshow.TvShowResponse
import com.nurhidayaatt.core.domain.model.Movie
import com.nurhidayaatt.core.domain.model.TvShow
import com.nurhidayaatt.core.domain.repository.IMainRepository
import com.nurhidayaatt.core.utils.DataMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import timber.log.Timber

class FakeMainRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : IMainRepository {

    override fun getAllMovie(sortType: String): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                Timber.d("LoadFromDB")
                return localDataSource.getAllMovie(sortType).map {
                    DataMapper.mapMovieEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean = data.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> {
                Timber.d("LoadFromAPI")
                return remoteDataSource.getAllMovie()
            }

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                Timber.d("SaveToDB")
                val movieList = DataMapper.mapMovieResponsesToEntities(data)
                localDataSource.insertMovie(movieList)
            }
        }.asFlow()

    override suspend fun setFavoriteMovie(movie: Movie, state: Boolean) = withContext(Dispatchers.IO) {
        val movieEntity = DataMapper.mapMovieDomainToEntity(movie)
        localDataSource.setFavoriteMovie(movieEntity, state)
    }

    override fun getMovieFavorite(): Flow<Resource<List<Movie>>> {
        return flow {
            emit(Resource.Loading())
            try {
                emitAll(
                    localDataSource.getMovieFavorite().map {
                        DataMapper.mapMovieEntitiesToDomain(it)
                    }.map { Resource.Success(it) })
            } catch (e: Exception) {
                emit(Resource.Error(e.message.toString(), null))
                Timber.e(e)
            }
        }
    }

    override fun getAllTvShow(sortType: String): Flow<Resource<List<TvShow>>> =
        object : NetworkBoundResource<List<TvShow>, List<TvShowResponse>>() {
            override fun loadFromDB(): Flow<List<TvShow>> {
                return localDataSource.getAllTvShow(sortType).map {
                    DataMapper.mapTvShowEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<TvShow>?): Boolean = data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<TvShowResponse>>> =
                remoteDataSource.getAllTvShow()

            override suspend fun saveCallResult(data: List<TvShowResponse>) {
                val tvShowList = DataMapper.mapTvShowResponsesToEntities(data)
                localDataSource.insertTvShow(tvShowList)
            }
        }.asFlow()

    override suspend fun setFavoriteTvShow(tvShow: TvShow, state: Boolean) = withContext(Dispatchers.IO) {
        val tvShowEntity = DataMapper.mapTvShowDomainToEntity(tvShow)
        localDataSource.setFavoriteTvShow(tvShowEntity, state)
    }

    override fun getTvShowFavorite(): Flow<Resource<List<TvShow>>> {
        return flow {
            emit(Resource.Loading())
            try {
                emitAll(
                    localDataSource.getTvShowFavorite().map {
                        DataMapper.mapTvShowEntitiesToDomain(it)
                    }.map { Resource.Success(it) })
            } catch (e: Exception) {
                emit(Resource.Error(e.message.toString(), null))
                Timber.e(e)
            }
        }
    }
}