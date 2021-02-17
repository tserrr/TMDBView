package com.tserr.tmdbview.data.network

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    val id: String,
    val title: String,
    @SerializedName("poster_path") val posterPath: String?,
    val overview: String?,
    val popularity: String?,
    @SerializedName("release_date")  val releaseDate: String?,
    val budget: Int?,
)