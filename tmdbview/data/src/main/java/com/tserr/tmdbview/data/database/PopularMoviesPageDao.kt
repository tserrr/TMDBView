package com.tserr.tmdbview.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
abstract class PopularMoviesPageDao {

    suspend fun updatePopularMoviesPage(page: Int, totalPages: Int) =
        insertPopularMoviesPage(PopularMoviesPageEntity(page, totalPages))

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertPopularMoviesPage(entity: PopularMoviesPageEntity)

    @Query("SELECT * FROM popular_movies_page_entity ORDER BY ROWID ASC LIMIT 1")
    abstract suspend fun latestPopularMoviesPage(): PopularMoviesPageEntity

    @Query("DELETE FROM popular_movies_page_entity")
    abstract suspend fun deletePopularMoviesPage()

}

