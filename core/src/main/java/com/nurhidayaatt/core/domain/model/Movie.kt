package com.nurhidayaatt.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id: Int,
    val overview: String? = null,
    var backdropPath: String ?= null,
    val posterPath: String? = null,
    val releaseDate: String? = null,
    val title: String? = null,
    val voteAverage: Double? = null,
    var voteCount: Int,
    var isFavorite: Boolean = false
): Parcelable