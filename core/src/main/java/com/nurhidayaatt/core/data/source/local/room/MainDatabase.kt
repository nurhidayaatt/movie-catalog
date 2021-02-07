package com.nurhidayaatt.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nurhidayaatt.core.data.source.local.entity.MovieEntity
import com.nurhidayaatt.core.data.source.local.entity.TvShowEntity

@Database(
    entities = [MovieEntity::class, TvShowEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MainDatabase: RoomDatabase() {
    abstract fun mainDao(): MainDao
}