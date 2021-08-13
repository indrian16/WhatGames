package io.indrian.whatgames.di

import io.indrian.whatgames.ui.detail.DetailViewModel
import io.indrian.whatgames.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}