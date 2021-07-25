package io.indrian.whatgames.ui.search

import androidx.lifecycle.ViewModel
import io.indrian.core.domain.usecase.GameUseCase

class SearchViewModel(private val gameUseCase: GameUseCase) : ViewModel() {

    fun search(search: String) = gameUseCase.searchGames(search)
}