package com.nurhidayaatt.core.data.source.remote.network

import com.nurhidayaatt.core.data.source.remote.response.movie.ListMovieResponse
import com.nurhidayaatt.core.data.source.remote.response.tvshow.ListTvShowResponse
import com.nurhidayaatt.core.BuildConfig.TMDB_API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("discover/movie")
    suspend fun getMovie(@Query("api_key") apiKey: String = TMDB_API_KEY): ListMovieResponse

    @GET("discover/tv")
    suspend fun getTvShow(@Query("api_key") apiKey: String = TMDB_API_KEY): ListTvShowResponse
}
