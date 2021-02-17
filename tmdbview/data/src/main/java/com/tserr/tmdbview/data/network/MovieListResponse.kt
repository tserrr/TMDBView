package com.tserr.tmdbview.data.network

import com.google.gson.annotations.SerializedName


data class MovieListResponse(
    val page: Int,
    val results: List<MovieResponse>,
    @SerializedName("total_results") val totalResults: Int,
    @SerializedName("total_pages") val totalPages: Int,
)

