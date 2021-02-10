package com.nurhidayaatt.core.data.source.remote.response.movie

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("overview")
    val overview: String? = null,
    @SerializedName("backdrop_path")
    var backdropPath: String ?= null,
    @SerializedName("poster_path")
    val posterPath: String? = null,
    @SerializedName("release_date")
    val releaseDate: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("vote_average")
    val voteAverage: Double? = null,
    @SerializedName("vote_count")
    var voteCount: Int
)