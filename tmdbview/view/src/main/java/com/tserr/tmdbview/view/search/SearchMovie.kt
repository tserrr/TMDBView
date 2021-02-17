package com.tserr.tmdbview.view.search

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.*
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.tserr.tmdbview.view.lazymovielist.LazyMovieList
import com.tserr.tmdbview.view.screen.screenWithTopBar
import com.tserr.tmdbview.view.topbar.tmdbViewTopBar

val searchMovie =
    screenWithTopBar<SearchMovieViewModel>(
        topBar = tmdbViewTopBar("Search movie")
    ) { _, viewModel, _, _ ->

        val query: String by remember { viewModel.queryState }
        val movieLazyItems = viewModel.pagedSearchResult.collectAsLazyPagingItems()
        var focusState by remember { mutableStateOf(FocusState.Inactive) }
        val focusRequester = FocusRequester()

        TextField(
            value = query,
            onValueChange = viewModel::onSearchValueChanged,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .focusModifier()
                .onFocusChanged { state -> focusState = state }
                .focusRequester(focusRequester)
        )


        LazyMovieList(
            movies = movieLazyItems,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            posterModifier = Modifier
                .padding(end = 8.dp)
                .width(48.dp)
        ) { movieId ->
            viewModel.onMovieClicked(movieId)
        }

        LaunchedEffect(focusState) {
            focusRequester.requestFocus()
        }
    }