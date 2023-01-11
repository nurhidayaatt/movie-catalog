package com.nurhidayaatt.core.utils

import com.nurhidayaatt.core.data.source.local.entity.MovieEntity
import com.nurhidayaatt.core.data.source.local.entity.TvShowEntity
import com.nurhidayaatt.core.data.source.remote.response.movie.MovieResponse
import com.nurhidayaatt.core.data.source.remote.response.tvshow.TvShowResponse
import com.nurhidayaatt.core.domain.model.Movie
import com.nurhidayaatt.core.domain.model.TvShow

object DataMapper {
    fun mapMovieResponsesToEntities(input: List<MovieResponse>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                id = it.id,
                overview = it.overview,
                backdropPath = it.backdropPath,
                posterPath = it.posterPath,
                releaseDate = it.releaseDate,
                title = it.title,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount,
                isFavorite = false
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapMovieEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                id = it.id,
                overview = it.overview,
                backdropPath = it.backdropPath,
                posterPath = it.posterPath,
                releaseDate = it.releaseDate,
                title = it.title,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount,
                isFavorite = it.isFavorite
            )
        }

    fun mapMovieDomainToEntity(input: Movie) = MovieEntity(
        id = input.id,
        overview = input.overview,
        backdropPath = input.backdropPath,
        posterPath = input.posterPath,
        releaseDate = input.releaseDate,
        title = input.title,
        voteAverage = input.voteAverage,
        voteCount =  input.voteCount,
        isFavorite = input.isFavorite
    )

    fun mapTvShowResponsesToEntities(input: List<TvShowResponse>): List<TvShowEntity> {
        val tvShowList = ArrayList<TvShowEntity>()
        input.map {
            val tvShow = TvShowEntity(
                firstAirDate = it.firstAirDate,
                id = it.id,
                name = it.name,
                overview = it.overview,
                backdropPath = it.backdropPath,
                posterPath = it.posterPath,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount,
                isFavorite = false
            )
            tvShowList.add(tvShow)
        }
        return tvShowList
    }

    fun mapTvShowEntitiesToDomain(input: List<TvShowEntity>): List<TvShow> =
        input.map {
            TvShow(
                firstAirDate = it.firstAirDate,
                id = it.id ?: 0,
                name = it.name,
                overview = it.overview,
                backdropPath = it.backdropPath,
                posterPath = it.posterPath,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount,
                isFavorite = it.isFavorite
            )
        }

    fun mapTvShowDomainToEntity(input: TvShow) = TvShowEntity(
        firstAirDate = input.firstAirDate,
        id = input.id,
        name = input.name,
        overview = input.overview,
        backdropPath = input.backdropPath,
        posterPath = input.posterPath,
        voteAverage = input.voteAverage,
        voteCount = input.voteCount,
        isFavorite = input.isFavorite
    )

    // for unit test
    fun mapMovieDomainToEntity(input: List<Movie>): List<MovieEntity> = input.map {
        MovieEntity(
            id = it.id,
            overview = it.overview,
            backdropPath = it.backdropPath,
            posterPath = it.posterPath,
            releaseDate = it.releaseDate,
            title = it.title,
            voteAverage = it.voteAverage,
            voteCount = it.voteCount,
            isFavorite = it.isFavorite
        )
    }

    fun mapMovieDomainToResponses(input: List<Movie>): List<MovieResponse> = input.map {
        MovieResponse(
            id = it.id,
            overview = it.overview,
            backdropPath = it.backdropPath,
            posterPath = it.posterPath,
            releaseDate = it.releaseDate,
            title = it.title,
            voteAverage = it.voteAverage,
            voteCount = it.voteCount,
        )
    }

    fun mapTvShowDomainToEntity(input: List<TvShow>): List<TvShowEntity> = input.map {
        TvShowEntity(
            firstAirDate = it.firstAirDate,
            id = it.id,
            name = it.name,
            overview = it.overview,
            backdropPath = it.backdropPath,
            posterPath = it.posterPath,
            voteAverage = it.voteAverage,
            voteCount = it.voteCount,
            isFavorite = it.isFavorite
        )
    }

    fun mapTvShowDomainToResponses(input: List<TvShow>): List<TvShowResponse> = input.map {
        TvShowResponse(
            firstAirDate = it.firstAirDate,
            id = it.id,
            name = it.name,
            overview = it.overview,
            backdropPath = it.backdropPath,
            posterPath = it.posterPath,
            voteAverage = it.voteAverage,
            voteCount = it.voteCount
        )
    }
}