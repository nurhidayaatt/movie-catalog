package com.nurhidayaatt.core.data.source.remote.response.tvshow

import com.google.gson.annotations.SerializedName

data class ListTvShowResponse(
    @SerializedName("results")
    val results: List<TvShowResponse>
)