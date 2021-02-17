package com.tserr.tmdbview.view.di

import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.viewModel
import androidx.lifecycle.ViewModel

/**
 * Workaround to combine benefits from usage compose [viewModel] and koin
 */
@Composable
inline fun <reified T : ViewModel> tmdbViewModel(): T = viewModel(factory = TmdbViewModelFactory())
