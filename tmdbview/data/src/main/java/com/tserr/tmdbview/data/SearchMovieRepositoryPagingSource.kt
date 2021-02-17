package com.tserr.tmdbview.data

import androidx.paging.*
import com.tserr.tmdbview.data.database.MovieDao
import com.tserr.tmdbview.data.database.toMovieModel
import com.tserr.tmdbview.domain.Movie
import com.tserr.tmdbview.domain.SearchMovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SearchMovieRepositoryPagingSource(
    private val movieDao: MovieDao,
    private val remoteMediator: SearchMoviesRemoteMediator,
    private val pagingConfig: PagingConfig
) : SearchMovieRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun search(query: String): Flow<PagingData<Movie>> {
        remoteMediator.query = query
        return Pager(
            config = pagingConfig,
            remoteMediator = remoteMediator
        ) {
            movieDao.searchMoviesPagingSource(query)
        }.flow.map { pagingData ->
            pagingData.map { movieEntity ->
                movieEntity.toMovieModel()
            }
        }
    }
}