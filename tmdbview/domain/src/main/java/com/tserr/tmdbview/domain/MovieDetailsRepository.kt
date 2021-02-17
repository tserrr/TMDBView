package com.tserr.tmdbview.domain


interface MovieDetailsRepository {

    suspend fun getMovieDetails(movieId: String): Movie?
}
