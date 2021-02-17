package com.tserr.tmdbview.domain

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface SearchMovieRepository {

    fun search(query: String): Flow<PagingData<Movie>>
}
