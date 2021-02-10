package com.nurhidayaatt.core.data.source.remote.response.tvshow

import com.google.gson.annotations.SerializedName

data class TvShowResponse(
    @SerializedName("first_air_date")
    val firstAirDate: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("overview")
    val overview: String? = null,
    @SerializedName("backdrop_path")
    var backdropPath: String ?= null,
    @SerializedName("poster_path")
    val posterPath: String? = null,
    @SerializedName("vote_average")
    val voteAverage: Double? = null,
    @SerializedName("vote_count")
    var voteCount: Int
)