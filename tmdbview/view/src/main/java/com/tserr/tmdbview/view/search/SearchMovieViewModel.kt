package com.tserr.tmdbview.view.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.tserr.tmdbview.domain.Movie
import com.tserr.tmdbview.domain.SearchMovieInteractor
import com.tserr.tmdbview.view.navigation.TmdbDirection
import com.tserr.tmdbview.view.topbar.TopBarViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SearchMovieViewModel(
    private val interactor: SearchMovieInteractor
) : TopBarViewModel(true) {

    val directionState = mutableStateOf<TmdbDirection?>(null)

    val queryState = mutableStateOf("")

    val pagedSearchResult: Flow<PagingData<Movie>>
        get() = _pagedSearchResult.cachedIn(viewModelScope)

    private val _pagedSearchResult: MutableStateFlow<PagingData<Movie>> =
        MutableStateFlow(PagingData.empty())

    fun onSearchValueChanged(searchValue: String) {
        queryState.value = searchValue
        viewModelScope.launch {
            interactor.searchMovie(searchValue)
                .cachedIn(viewModelScope)
                .collect { pagingData ->
                    _pagedSearchResult.value = pagingData
                }
        }
    }

    fun onMovieClicked(movieId: String) {
        navigationState.value = TmdbDirection.MovieDetails(movieId)
    }

}
