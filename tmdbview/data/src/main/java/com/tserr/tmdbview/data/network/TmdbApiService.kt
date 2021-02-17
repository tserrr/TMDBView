package com.tserr.tmdbview.data.network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbApiService {

    @GET("{version}/movie/popular")
    suspend fun getPopularMovies(
        @Path("version") version: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): MovieListResponse

    @GET("{version}/movie/{movie_id}")
    suspend fun getMovie(
        @Path("version") version: Int,
        @Path("movie_id") id: String
    ): MovieResponse

    @GET("{version}/configuration")
    suspend fun getConfiguration(
        @Path("version") version: Int,
        @Query("api_key") apiKey: String,
    ): ConfigurationResponse

    @GET("{version}/search/movie")
    suspend fun searchMovies(
        @Path("version") version: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("query") query: String,
        @Query("page") page: Int
    ): MovieListResponse
}