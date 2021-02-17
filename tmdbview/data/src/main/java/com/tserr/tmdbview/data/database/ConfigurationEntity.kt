package com.tserr.tmdbview.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "configuration")
class ConfigurationEntity(
    val baseUrl: String,
    val secureBaseUrl: String,
    val posterSizes: List<String>,
    val logoSizes: List<String>,
    val profileSizes: List<String>,
    val stillSizes: List<String>,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
)