package com.nurhidayaatt.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey
    val id: Int,
    val overview: String? = null,
    @ColumnInfo(name = "backdrop_path")
    var backdropPath: String ?= null,
    @ColumnInfo(name = "poster_path")
    val posterPath: String? = null,
    @ColumnInfo(name = "release_date")
    val releaseDate: String? = null,
    val title: String? = null,
    @ColumnInfo(name = "vote_average")
    val voteAverage: Double? = null,
    @ColumnInfo(name = "vote_count")
    var voteCount: Int,
    @ColumnInfo(name = "is_favorite")
    var isFavorite: Boolean = false
)