package io.indrian.search

import androidx.lifecycle.ViewModel
import io.indrian.core.domain.usecase.GameUseCase

class SearchViewModel(private val gameUseCase: GameUseCase) : ViewModel() {

    fun search(search: CharSequence?) = gameUseCase.searchGames(search)
}