package com.tserr.tmdbview.view.di

import com.tserr.tmdbview.view.details.MovieDetailsViewModel
import com.tserr.tmdbview.view.popular.PopularMoviesViewModel
import com.tserr.tmdbview.view.search.SearchMovieViewModel
import org.koin.dsl.module
import org.koin.experimental.builder.create

val viewModule = module {
    factory { create<PopularMoviesViewModel>() }
    factory { create<SearchMovieViewModel>() }
    factory { create<MovieDetailsViewModel>() }
}