package com.nurhidayaatt.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tv_show")
data class TvShowEntity(
    @ColumnInfo(name = "first_air_date")
    val firstAirDate: String? = null,
    @PrimaryKey
    @NonNull
    val id: Int? = null,
    val name: String? = null,
    val overview: String? = null,
    @ColumnInfo(name = "backdrop_path")
    var backdropPath: String ?= null,
    @ColumnInfo(name = "poster_path")
    val posterPath: String? = null,
    @ColumnInfo(name = "vote_average")
    val voteAverage: Double? = null,
    @ColumnInfo(name = "vote_count")
    var voteCount: Int,
    @ColumnInfo(name = "is_favorite")
    var isFavorite: Boolean = false
)