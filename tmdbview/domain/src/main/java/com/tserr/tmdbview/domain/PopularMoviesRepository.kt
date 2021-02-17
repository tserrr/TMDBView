package com.tserr.tmdbview.domain


import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface PopularMoviesRepository {

    val popularMovies: Flow<PagingData<Movie>>
}
