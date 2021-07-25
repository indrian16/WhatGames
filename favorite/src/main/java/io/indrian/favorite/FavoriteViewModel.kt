package io.indrian.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import io.indrian.core.domain.usecase.GameUseCase

class FavoriteViewModel(gameUseCase: GameUseCase) : ViewModel() {

    val games = gameUseCase.getFavoriteGames().asLiveData()
}