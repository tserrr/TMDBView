package com.tserr.tmdbview.domain

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

class SearchMovieInteractor(
    private val repository: SearchMovieRepository
) {

    fun searchMovie(searchValue: String): Flow<PagingData<Movie>> {
        return repository.search(searchValue)
    }

}