package com.baiana.simplemovies.core

import android.app.Application
import com.baiana.simplemovies.di.apiModule
import com.baiana.simplemovies.di.moviesListModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class CoreApplication() : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CoreApplication)
            modules(apiModule, moviesListModule)
        }

    }
}