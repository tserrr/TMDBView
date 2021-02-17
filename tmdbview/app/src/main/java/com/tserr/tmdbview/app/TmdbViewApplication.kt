package com.tserr.tmdbview.app

import android.app.Application
import com.tserr.tmdbview.data.di.dataModule
import com.tserr.tmdbview.domain.di.domainModule
import com.tserr.tmdbview.view.di.viewModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class TmdbViewApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(listOf(viewModule, dataModule, domainModule))
        }
        Timber.plant(Timber.DebugTree())
    }
}