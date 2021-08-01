package io.indrian.core.di

import io.indrian.core.data.GameRepository
import io.indrian.core.data.source.local.LocalDataSource
import io.indrian.core.data.source.remote.RemoteDataSource
import io.indrian.core.domain.repository.IGameRepository
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val repositoryModule = module {
    single { LocalDataSource(get(), get()) }
    single { RemoteDataSource(get(), Dispatchers.IO) }
    single<IGameRepository> {
        GameRepository(
            get(),
            get()
        )
    }
}