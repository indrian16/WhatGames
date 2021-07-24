package io.indrian.whatgames.di

import io.indrian.core.domain.usecase.GameInteractor
import io.indrian.core.domain.usecase.GameUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory<GameUseCase> { GameInteractor(get()) }
}