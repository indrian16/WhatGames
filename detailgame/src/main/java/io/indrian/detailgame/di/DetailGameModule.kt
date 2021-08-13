package io.indrian.detailgame.di

import io.indrian.detailgame.DetailViewModel
import org.koin.dsl.module

val detailGameModule = module {
    single { DetailViewModel(get()) }
}