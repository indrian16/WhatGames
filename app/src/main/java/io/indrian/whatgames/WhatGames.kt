@file:Suppress("unused")

package io.indrian.whatgames

import android.app.Application
import io.indrian.core.di.databaseModule
import io.indrian.core.di.networkModule
import io.indrian.core.di.repositoryModule
import io.indrian.whatgames.di.useCaseModule
import io.indrian.whatgames.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

/**
 *
 * @author INDRIAN
 * @github https://github.com/indrian16/
 * @email indrian16dev@gmail.com
 * */
class WhatGames : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        startKoin {
            androidLogger(level = Level.ERROR)
            androidContext(this@WhatGames)
            modules(
                databaseModule,
                networkModule,
                repositoryModule,
                useCaseModule,
                viewModelModule
            )
        }
    }
}