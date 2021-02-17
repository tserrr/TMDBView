package com.tserr.tmdbview.view.details

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tserr.tmdbview.domain.Movie
import com.tserr.tmdbview.domain.MovieDetailsInteractor
import com.tserr.tmdbview.view.topbar.TopBarViewModel
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val movieDetailsInteractor: MovieDetailsInteractor
) : TopBarViewModel(true, false) {

    val movieState: MutableState<Movie?> = mutableStateOf(null)

    fun getMovieDetails(id: String?) {
        viewModelScope.launch {
            movieState.value = id?.let { movieDetailsInteractor.getMovieDetails(it) }
        }
    }
}
