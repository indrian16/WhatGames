package io.indrian.whatgames

import android.app.Application
import io.indrian.core.di.databaseModule
import io.indrian.core.di.networkModule
import io.indrian.core.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class WhatGames : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(level = Level.ERROR)
            androidContext(this@WhatGames)
            modules(
                databaseModule,
                networkModule,
                repositoryModule
            )
        }
    }
}