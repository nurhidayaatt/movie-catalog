package com.nurhidayaatt.core.data.source.remote.response.movie

import com.google.gson.annotations.SerializedName

data class ListMovieResponse(
    @SerializedName("results")
    val results: List<MovieResponse>
)