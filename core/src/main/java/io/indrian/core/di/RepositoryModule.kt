package io.indrian.core.di

import io.indrian.core.data.GameRepository
import io.indrian.core.data.source.local.LocalDataSource
import io.indrian.core.data.source.remote.RemoteDataSource
import io.indrian.core.domain.repository.IGameRepository
import io.indrian.core.utils.AppExecutors
import org.koin.dsl.module

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IGameRepository> {
        GameRepository(
            get(),
            get(),
            get()
        )
    }
}