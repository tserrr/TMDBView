package com.tserr.tmdbview.data.database

import android.content.Context
import androidx.room.Room

fun provideTmdbViewDatabase(appContext: Context) =
    Room.databaseBuilder(appContext, TmdbViewDatabase::class.java, "TmdbViewDatabase").build()