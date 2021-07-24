package io.indrian.core.di

import androidx.room.Room
import io.indrian.core.data.source.local.room.AppDatabase
import io.indrian.core.utils.CoreUtils
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            CoreUtils.DB_NAME
        ).fallbackToDestructiveMigration().build()
    }
}