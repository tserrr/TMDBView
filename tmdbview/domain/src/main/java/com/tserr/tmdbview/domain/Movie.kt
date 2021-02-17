package com.tserr.tmdbview.domain

data class Movie(
    val id: String,
    val title: String,
    val poster: String?,
    val overview: String?,
    val popularity: String?,
    val releaseDate: String?,
    val budget: Int?
)