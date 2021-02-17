package com.tserr.tmdbview.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
abstract class SearchMoviesPageDao {

    suspend fun updateSearchMoviesPage(page: Int, totalPages: Int) =
        insertSearchMoviesPage(SearchMoviesPageEntity(page, totalPages))

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertSearchMoviesPage(entity: SearchMoviesPageEntity)

    @Query("SELECT * FROM search_movies_page_entity ORDER BY ROWID ASC LIMIT 1")
    abstract suspend fun latestSearchMoviesPage(): SearchMoviesPageEntity

    @Query("DELETE FROM search_movies_page_entity")
    abstract suspend fun deleteSearchMoviesPage()

}