package com.tserr.tmdbview.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [MovieEntity::class, PopularMoviesPageEntity::class, SearchMoviesPageEntity::class],
    version = 1
)
abstract class TmdbViewDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun popularMoviesPageDao(): PopularMoviesPageDao
    abstract fun searchMoviesPageDao(): SearchMoviesPageDao
}