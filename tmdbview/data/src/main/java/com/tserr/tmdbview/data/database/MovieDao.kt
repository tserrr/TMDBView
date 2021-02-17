package com.tserr.tmdbview.data.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<MovieEntity>)

    @Query("SELECT * FROM movies")
    fun moviesPagingSource(): PagingSource<Int, MovieEntity>

    @Query("SELECT * FROM movies WHERE id == :movieId")
    suspend fun getMovie(movieId: String): MovieEntity?

    @Query("SELECT * FROM movies WHERE movies.title LIKE  '%' || :title || '%'")
    fun searchMoviesPagingSource(title: String): PagingSource<Int, MovieEntity>

    @Query("DELETE FROM movies")
    suspend fun clearMovies()
}