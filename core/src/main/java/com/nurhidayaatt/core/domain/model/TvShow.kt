package com.nurhidayaatt.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TvShow(
    val firstAirDate: String? = null,
    val id: Int,
    val name: String? = null,
    val overview: String? = null,
    var backdropPath: String ?= null,
    val posterPath: String? = null,
    val voteAverage: Double? = null,
    var voteCount: Int,
    var isFavorite: Boolean = false
): Parcelable