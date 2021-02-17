package com.tserr.tmdbview.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "popular_movies_page_entity")
data class PopularMoviesPageEntity(
    val latestPage: Int,
    val totalPages: Int,
    @PrimaryKey
    val id: Int = 0
)