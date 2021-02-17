package com.tserr.tmdbview.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    val posterPath: String?,
    val overview: String?,
    val popularity: String?,
    val releaseDate: String?,
    val budget: Int?
)