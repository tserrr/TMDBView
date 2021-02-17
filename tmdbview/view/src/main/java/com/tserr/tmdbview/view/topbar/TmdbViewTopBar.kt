package com.tserr.tmdbview.view.topbar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.navigation.compose.navigate
import com.tserr.tmdbview.view.screen.TmdbScreenContent


fun <T : TopBarViewModel> tmdbViewTopBar(
    title: String = "Tmdb View",
): TmdbScreenContent<T> =
    { navController, viewModel, _, _ ->

        val directionState by viewModel.navigationState

        TopAppBar(
            title = {
                Text(text = title)
            },
            navigationIcon = navigationIcon(viewModel),
            actions = searchIcon(viewModel)
        )

        LaunchedEffect(directionState) {
            directionState?.let { navController.navigate(it.route) }
        }
    }

@Composable
fun navigationIcon(viewModel: TopBarViewModel): @Composable() (() -> Unit)? =
    if (viewModel.isBackDirectionEnabled) {
        {
            IconButton(onClick = {
                viewModel.onBackButtonClicked()
            }) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Arrow Back")
            }

        }
    } else null

@Composable
fun searchIcon(viewModel: TopBarViewModel): @Composable RowScope.() -> Unit {
    return if (viewModel.isSearchDirectionEnabled) {
        {
            IconButton(onClick = {
                viewModel.onSearchButtonClicked()
            }) {
                Icon(Icons.Filled.Search, contentDescription = "Search")
            }
        }
    } else {
        {}
    }
}