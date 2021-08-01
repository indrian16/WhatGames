package io.indrian.core.di

import androidx.room.Room
import io.indrian.core.data.source.local.room.AppDatabase
import io.indrian.core.utils.CoreUtils
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single {

        val passPhrase = SQLiteDatabase.getBytes("indrian.io".toCharArray())
        val factory = SupportFactory(passPhrase)

        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            CoreUtils.DB_NAME
        ).fallbackToDestructiveMigration().openHelperFactory(factory).build()
    }

    single {
        get<AppDatabase>().gameDao()
    }
    single {
        get<AppDatabase>().genreDao()
    }
}