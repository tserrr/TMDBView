package com.tserr.tmdbview.domain

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

class LoadPopularMoviesInteractor(
   private val repository: PopularMoviesRepository
) {

    fun loadPopularMovies(): Flow<PagingData<Movie>> {
        println("LoadPopularMoviesInteractor loadPopularMovies")
        return repository.popularMovies
    }

}