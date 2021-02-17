package com.tserr.tmdbview.domain.di

import com.tserr.tmdbview.domain.LoadPopularMoviesInteractor
import com.tserr.tmdbview.domain.MovieDetailsInteractor
import com.tserr.tmdbview.domain.SearchMovieInteractor
import org.koin.dsl.module
import org.koin.experimental.builder.create

val domainModule = module {
        factory { create<LoadPopularMoviesInteractor>() }
        factory { create<MovieDetailsInteractor>() }
        factory { create<SearchMovieInteractor>() }
}