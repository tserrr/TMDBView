package com.tserr.tmdbview.data.database

import com.tserr.tmdbview.domain.Movie

fun MovieEntity.toMovieModel(): Movie = Movie(
    id,
    title,
    posterPath,
    overview,
    popularity,
    releaseDate,
    budget
)

val PopularMoviesPageEntity.isEndOfPaginationReached: Boolean
    get() = totalPages == latestPage

val SearchMoviesPageEntity.isEndOfPaginationReached: Boolean
    get() = totalPages == latestPage