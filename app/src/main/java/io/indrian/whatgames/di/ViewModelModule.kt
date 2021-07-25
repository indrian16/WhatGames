package io.indrian.whatgames.di

import io.indrian.whatgames.ui.detail.DetailViewModel
import io.indrian.whatgames.ui.main.MainViewModel
import io.indrian.whatgames.ui.search.SearchViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { SearchViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}