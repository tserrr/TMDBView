package com.tserr.tmdbview.view.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tserr.tmdbview.view.details.movieDetails
import com.tserr.tmdbview.view.popular.popularMovies
import com.tserr.tmdbview.view.screen.TmdbScreen
import com.tserr.tmdbview.view.search.searchMovie

@Composable
fun TmdbViewNavHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = TmdbDirection.PopularMovies.route
    ) {
        composable(TmdbDirection.PopularMovies.route) {
            TmdbScreen(
                navController,
                content = popularMovies
            )
        }
        composable(TmdbDirection.SearchMovie.route) {
            TmdbScreen(
                navController,
                content = searchMovie
            )
        }
        composable(TmdbDirection.MovieDetails("{movieId}").route) { navBackStackEntry ->
            TmdbScreen(navController, navBackStackEntry.arguments, movieDetails)
        }
    }
}

