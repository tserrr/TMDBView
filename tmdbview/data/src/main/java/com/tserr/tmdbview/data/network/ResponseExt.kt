package com.tserr.tmdbview.data.network

import com.tserr.tmdbview.data.BuildConfig
import com.tserr.tmdbview.data.database.ConfigurationEntity
import com.tserr.tmdbview.data.database.MovieEntity
import com.tserr.tmdbview.domain.Movie

fun MovieListResponse.toMovieEntities(config: ConfigurationEntity): List<MovieEntity> =
    results.map { movieResponse ->
        movieResponse.run {
            "${BuildConfig.TMDB_URL}/${BuildConfig.TMDB_API_VERSION}/?api_key="
            MovieEntity(
                id,
                title,
                "${config.baseUrl}/${config.posterSizes.last()}/$posterPath",
                overview,
                popularity,
                releaseDate,
                budget
            )
        }
    }

val MovieListResponse.isEndOfPaginationReached
    get() = page == totalPages

fun ConfigurationResponse.toEntity(): ConfigurationEntity =
    with(images) {
        ConfigurationEntity(
            baseUrl,
            secureBaseUrl,
            posterSizes,
            logoSizes,
            profileSizes,
            stillSizes
        )
    }

fun MovieResponse.toMovieModel() = Movie(
    id,
    title,
    posterPath,
    overview,
    popularity,
    releaseDate,
    budget
)

