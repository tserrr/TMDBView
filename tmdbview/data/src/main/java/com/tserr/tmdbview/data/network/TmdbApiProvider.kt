package com.tserr.tmdbview.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun provideTmdbApi(baseUrl: String): TmdbApiService = Retrofit.Builder()
    .baseUrl(baseUrl)
    .addConverterFactory(GsonConverterFactory.create())
    .build()
    .create(TmdbApiService::class.java)