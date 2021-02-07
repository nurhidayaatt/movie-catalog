package com.nurhidayaatt.core.data.source.local.room

import androidx.room.*
import com.nurhidayaatt.core.data.source.local.entity.MovieEntity
import com.nurhidayaatt.core.data.source.local.entity.TvShowEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MainDao {

    @Query("""SELECT * FROM movie
        ORDER BY 
        CASE WHEN :sortType = 'ID'  THEN id END DESC,
        CASE WHEN :sortType = 'DATE' THEN release_date END DESC,
        CASE WHEN :sortType = 'RATING' THEN vote_average END DESC
    """)
    fun getAllMovie(sortType: String): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movies: List<MovieEntity>)

    @Update
    suspend fun updateMovieFavorite(movie: MovieEntity)

    @Query("SELECT * FROM movie where is_favorite = 1")
    fun getFavoriteMovie(): Flow<List<MovieEntity>>

    @Query("""SELECT * FROM tv_show
        ORDER BY 
        CASE WHEN :sortType = 'ID'  THEN id END DESC,
        CASE WHEN :sortType = 'DATE' THEN first_air_date END DESC,
        CASE WHEN :sortType = 'RATING' THEN vote_average END DESC
    """)
    fun getAllTvShow(sortType: String): Flow<List<TvShowEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvShow(tvShows: List<TvShowEntity>)

    @Update
    suspend fun updateTvShowFavorite(tvShows: TvShowEntity)

    @Query("SELECT * FROM tv_show where is_favorite = 1")
    fun getFavoriteTvShow(): Flow<List<TvShowEntity>>
}