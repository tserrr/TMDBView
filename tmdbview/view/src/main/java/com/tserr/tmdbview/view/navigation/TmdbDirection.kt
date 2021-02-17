package com.tserr.tmdbview.view.navigation

sealed class TmdbDirection(val route: String) {

    object PopularMovies : TmdbDirection("popularMovies")

    object SearchMovie : TmdbDirection("searchMovie")

    class MovieDetails(movieId: String) : TmdbDirection("movieDetails/$movieId")
}