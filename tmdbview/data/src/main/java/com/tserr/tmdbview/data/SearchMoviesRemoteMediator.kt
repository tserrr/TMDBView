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
class SearchMoviesRemoteMediator(
    private val db: TmdbViewDatabase,
    private val tmdbApiService: TmdbApiService,
) : RemoteMediator<Int, MovieEntity>() {

    private val movieDao = db.movieDao()

    private val searchMoviesPageDao = db.searchMoviesPageDao()

    var query: String = ""

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MovieEntity>
    ): MediatorResult {
        if (query.isEmpty())
            return MediatorResult.Error(Exception("Empty search query string"))
        return try {
            val nextPage = checkNextPage(loadType) ?: return MediatorResult.Success(true)

            val response = tmdbApiService.searchMovies(
                BuildConfig.TMDB_API_VERSION,
                BuildConfig.TMDB_API_KEY,
                Locale.ENGLISH.isO3Language,
                query,
                nextPage
            )

            val configResponse = tmdbApiService.getConfiguration(
                BuildConfig.TMDB_API_VERSION,
                BuildConfig.TMDB_API_KEY,
            )

            db.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    searchMoviesPageDao.deleteSearchMoviesPage()
                }

                movieDao.insertAll(response.toMovieEntities(configResponse.toEntity()))
                searchMoviesPageDao.updateSearchMoviesPage(response.page, response.totalPages)
            }

            MediatorResult.Success(response.isEndOfPaginationReached)
        } catch (e: Exception) {
            Timber.e(e, "Failed to find movies")
            MediatorResult.Error(e)
        }
    }

    private suspend fun checkNextPage(loadType: LoadType): Int? = when (loadType) {
        LoadType.REFRESH -> 1
        LoadType.PREPEND -> null
        LoadType.APPEND -> with(searchMoviesPageDao.latestSearchMoviesPage()) {
            if (isEndOfPaginationReached)
                null
            else
                latestPage + 1
        }
    }
}
