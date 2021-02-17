package com.tserr.tmdbview.data.di

import androidx.paging.PagingConfig
import com.tserr.tmdbview.data.*
import com.tserr.tmdbview.data.database.TmdbViewDatabase
import com.tserr.tmdbview.data.database.provideTmdbViewDatabase
import com.tserr.tmdbview.data.network.provideTmdbApi
import com.tserr.tmdbview.domain.MovieDetailsRepository
import com.tserr.tmdbview.domain.PopularMoviesRepository
import com.tserr.tmdbview.domain.SearchMovieRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import org.koin.experimental.builder.create

val dataModule = module {
    single { provideTmdbViewDatabase(androidContext()) }

    single { provideTmdbApi(BuildConfig.TMDB_URL) }

    single { create<PopularMoviesRemoteMediator>() }

    single { PagingConfig(pageSize = 30) }

    single<PopularMoviesRepository> {
        PopularMoviesRepositoryPagingSource(
            get<TmdbViewDatabase>().movieDao(),
            get(),
            get()
        )
    }

    single<MovieDetailsRepository> {
        MovieDetailsRepositoryNetworkLocal(get(), get<TmdbViewDatabase>().movieDao())
    }

    single { create<SearchMoviesRemoteMediator>() }

    single<SearchMovieRepository> {
        SearchMovieRepositoryPagingSource(
            get<TmdbViewDatabase>().movieDao(),
            get(),
            get()
        )
    }
}