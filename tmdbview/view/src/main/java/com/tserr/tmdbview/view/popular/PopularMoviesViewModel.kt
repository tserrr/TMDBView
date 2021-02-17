package com.tserr.tmdbview.view.popular

import androidx.compose.runtime.mutableStateOf
import androidx.paging.PagingData
import com.tserr.tmdbview.domain.LoadPopularMoviesInteractor
import com.tserr.tmdbview.domain.Movie
import com.tserr.tmdbview.view.navigation.TmdbDirection
import com.tserr.tmdbview.view.topbar.TopBarViewModel
import kotlinx.coroutines.flow.Flow

class PopularMoviesViewModel(
    private val interactor: LoadPopularMoviesInteractor,
) : TopBarViewModel(false, true) {

    val pagedPopularMovies: Flow<PagingData<Movie>>
        get() = interactor.loadPopularMovies()

    fun onMovieClicked(movieId: String) {
        navigationState.value = TmdbDirection.MovieDetails(movieId)
    }
}
