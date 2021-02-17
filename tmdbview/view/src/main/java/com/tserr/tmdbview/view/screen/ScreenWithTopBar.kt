package com.tserr.tmdbview.view.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import com.tserr.tmdbview.view.topbar.TopBarViewModel
import com.tserr.tmdbview.view.topbar.tmdbViewTopBar

fun <T : TopBarViewModel> screenWithTopBar(
    topBar: TmdbScreenContent<T> = tmdbViewTopBar(),
    content: TmdbScreenContent<T>
): TmdbScreenContent<T> = { navController, viewModel, arguments, modifier ->
    Column(modifier = Modifier.fillMaxHeight()) {
        topBar(navController, viewModel, arguments, modifier)
        content(navController, viewModel, arguments, modifier.weight(1f))
    }
}