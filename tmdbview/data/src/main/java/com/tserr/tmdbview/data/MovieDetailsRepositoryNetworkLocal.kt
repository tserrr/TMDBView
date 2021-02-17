package com.tserr.tmdbview.data

import com.tserr.tmdbview.data.database.MovieDao
import com.tserr.tmdbview.data.database.toMovieModel
import com.tserr.tmdbview.data.network.MovieResponse
import com.tserr.tmdbview.data.network.TmdbApiService
import com.tserr.tmdbview.data.network.toMovieModel
import com.tserr.tmdbview.domain.Movie
import com.tserr.tmdbview.domain.MovieDetailsRepository
import timber.log.Timber

class MovieDetailsRepositoryNetworkLocal(
    private val tmdbApiService: TmdbApiService,
    private val movieDao: MovieDao
) : MovieDetailsRepository {
    override suspend fun getMovieDetails(movieId: String): Movie? = movieDao.runCatching {
        getMovie(movieId)
    }.onFailure { e ->
        Timber.e(e, "Failed to get movie from DB")
    }.getOrNull()?.toMovieModel() ?: tmdbApiService.runCatching {
        getMovie(BuildConfig.TMDB_API_VERSION, movieId)
    }.onFailure { e ->
        Timber.e(e, "Failed to get movie from Network")
    }.getOrNull()?.toMovieModel()
}

