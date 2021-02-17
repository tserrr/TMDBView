package com.tserr.tmdbview.view.topbar

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.tserr.tmdbview.view.navigation.TmdbDirection


abstract class TopBarViewModel(
    val isBackDirectionEnabled: Boolean = false,
    val isSearchDirectionEnabled: Boolean = false
) : ViewModel() {

    val navigationState = mutableStateOf<TmdbDirection?>(null)



    fun onBackButtonClicked() {
        if (isBackDirectionEnabled)
            navigationState.value = TmdbDirection.PopularMovies
    }

    fun onSearchButtonClicked() {
        if (isSearchDirectionEnabled)
            navigationState.value = TmdbDirection.SearchMovie
    }
}