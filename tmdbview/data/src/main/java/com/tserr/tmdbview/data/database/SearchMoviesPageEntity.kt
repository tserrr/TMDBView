package com.tserr.tmdbview.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "search_movies_page_entity")
data class SearchMoviesPageEntity(
    val latestPage: Int,
    val totalPages: Int,
    @PrimaryKey
    val id: Int = 0
)