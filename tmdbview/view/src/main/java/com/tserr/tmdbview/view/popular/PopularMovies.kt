package com.tserr.tmdbview.view.popular

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.tserr.tmdbview.view.lazymovielist.LazyMovieList
import com.tserr.tmdbview.view.screen.screenWithTopBar
import com.tserr.tmdbview.view.topbar.tmdbViewTopBar
import timber.log.Timber


val popularMovies =
    screenWithTopBar<PopularMoviesViewModel>(
        topBar = tmdbViewTopBar("Popular movies")
    ) { navController, viewModel, _, _ ->
        Timber.d("popularMovies")
        //val directionState: TmdbDirection? by viewModel.directionStat
        val movieLazyItems = viewModel.pagedPopularMovies.collectAsLazyPagingItems()

        LazyMovieList(
            movies = movieLazyItems,
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            posterModifier = Modifier
                .padding(end = 12.dp)
                .width(148.dp)
        ) { movieId ->
            viewModel.onMovieClicked(movieId)
        }
    }

