package com.nurhidayaatt.core.data.source.remote

import com.nurhidayaatt.core.data.source.remote.network.ApiResponse
import com.nurhidayaatt.core.data.source.remote.network.ApiService
import com.nurhidayaatt.core.data.source.remote.response.movie.MovieResponse
import com.nurhidayaatt.core.data.source.remote.response.tvshow.TvShowResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getAllMovie(): Flow<ApiResponse<List<MovieResponse>>> = flow {
        val response = apiService.getMovie()
        val dataArray = response.results
        if (dataArray.isNotEmpty()) {
            emit(ApiResponse.Success(response.results))
        } else {
            emit(ApiResponse.Empty())
        }
    }.catch {
        emit(ApiResponse.Error(it.message.toString()))
        Timber.e(it)
    }.flowOn(Dispatchers.IO)

    suspend fun getAllTvShow(): Flow<ApiResponse<List<TvShowResponse>>> = flow {
        val response = apiService.getTvShow()
        val dataArray = response.results
        if (dataArray.isNotEmpty()) {
            emit(ApiResponse.Success(response.results))
        } else {
            emit(ApiResponse.Empty())
        }
    }.catch {
        emit(ApiResponse.Error(it.message.toString()))
        Timber.e(it)
    }.flowOn(Dispatchers.IO)
}