package com.tserr.tmdbview.domain

class MovieDetailsInteractor(
    private val repository: MovieDetailsRepository
) {

    suspend fun getMovieDetails(movieId: String): Movie? = repository.getMovieDetails(movieId)
}