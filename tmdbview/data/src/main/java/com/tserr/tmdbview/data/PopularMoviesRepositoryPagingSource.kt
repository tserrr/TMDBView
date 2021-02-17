package com.tserr.tmdbview.data

import androidx.paging.*
import com.tserr.tmdbview.data.database.MovieDao
import com.tserr.tmdbview.data.database.toMovieModel
import com.tserr.tmdbview.domain.Movie
import com.tserr.tmdbview.domain.PopularMoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber


class PopularMoviesRepositoryPagingSource(
    private val movieDao: MovieDao,
    private val popularMoviesRemoteMediator: PopularMoviesRemoteMediator,
    private val pagingConfig: PagingConfig
) : PopularMoviesRepository {

    @OptIn(ExperimentalPagingApi::class)
    override val popularMovies: Flow<PagingData<Movie>>
        get() = Pager(
            config = pagingConfig,
            remoteMediator = popularMoviesRemoteMediator
        ) {
            movieDao.moviesPagingSource()
        }.flow.map { pagingData ->
            pagingData.map { movieEntity ->
                movieEntity.toMovieModel()
            }
        }
}

