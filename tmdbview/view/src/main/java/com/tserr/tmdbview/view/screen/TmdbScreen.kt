package com.tserr.tmdbview.view.screen

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.tserr.tmdbview.view.di.tmdbViewModel

typealias  TmdbScreenContent<T> = @Composable (
    navController: NavController,
    viewModel: T,
    arguments: Bundle?,
    modifier: Modifier
) -> Unit

@Composable
inline fun <reified T : ViewModel> TmdbScreen(
    navController: NavController,
    arguments: Bundle? = null,
    content: TmdbScreenContent<T>,
) {
    content(navController, tmdbViewModel(), arguments, Modifier)
}