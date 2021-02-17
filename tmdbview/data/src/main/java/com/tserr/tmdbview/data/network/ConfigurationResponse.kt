package com.tserr.tmdbview.data.network

import com.google.gson.annotations.SerializedName

data class ConfigurationResponse(
    val images: ImagesResponse,
    @SerializedName("change_keys") val changeKeys: List<String>,
)

data class ImagesResponse(
    @SerializedName("base_url") val baseUrl: String,
    @SerializedName("secure_base_url") val secureBaseUrl: String,
    @SerializedName("poster_sizes") val posterSizes: List<String>,
    @SerializedName("logo_sizes") val logoSizes: List<String>,
    @SerializedName("profile_sizes") val profileSizes: List<String>,
    @SerializedName("still_sizes") val stillSizes: List<String>,
)
