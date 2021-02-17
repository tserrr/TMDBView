package com.tserr.tmdbview.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.tserr.tmdbview.data.database.MovieEntity
import com.tserr.tmdbview.data.database.TmdbViewDatabase
import com.tserr.tmdbview.data.database.isEndOfPaginationReached
import com.tserr.tmdbview.data.network.TmdbApiService
import com.tserr.tmdbview.data.network.isEndOfPaginationReached
import com.tserr.tmdbview.data.network.toEntity
import com.tserr.tmdbview.data.network.toMovieEntities
import timber.log.Timber
import java.util.*

@OptIn(ExperimentalPagingApi::class)
class PopularMoviesRemoteMediator(
    private val db: TmdbViewDatabase,
    private val tmdbApiService: TmdbApiService,
) : RemoteMediator<Int, MovieEntity>() {

    private val movieDao by lazy { db.movieDao() }

    private val popularMoviesPageDao by lazy { db.popularMoviesPageDao() }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MovieEntity>
    ): MediatorResult {
        return try {
            val nextPage = checkNextPage(loadType) ?: return MediatorResult.Success(true)

            val response = tmdbApiService.getPopularMovies(
                BuildConfig.TMDB_API_VERSION,
                BuildConfig.TMDB_API_KEY,
                Locale.ENGLISH.isO3Language,
                nextPage
            )

            val configResponse = tmdbApiService.getConfiguration(
                BuildConfig.TMDB_API_VERSION,
                BuildConfig.TMDB_API_KEY,
            )

            db.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    movieDao.clearMovies()
                    popularMoviesPageDao.deletePopularMoviesPage()
                }

                movieDao.insertAll(response.toMovieEntities(configResponse.toEntity()))
                popularMoviesPageDao.updatePopularMoviesPage(response.page, response.totalPages)
            }

            MediatorResult.Success(response.isEndOfPaginationReached)
        } catch (e: Exception) {
            Timber.e(e, "Failed to load popular movies")
            MediatorResult.Error(e)
        }
    }


    private suspend fun checkNextPage(loadType: LoadType): Int? = when (loadType) {
        LoadType.REFRESH -> 1
        LoadType.PREPEND -> null
        LoadType.APPEND -> with(popularMoviesPageDao.latestPopularMoviesPage()) {
            if (isEndOfPaginationReached)
                null
            else
                latestPage + 1
        }
    }

}
