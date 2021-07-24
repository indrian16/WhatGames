package io.indrian.whatgames.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import io.indrian.core.domain.usecase.GameUseCase

class MainViewModel(gameUseCase: GameUseCase) : ViewModel() {

    val gamesReleased = gameUseCase.getGamesReleased().asLiveData()

    val gamesRating = gameUseCase.getGamesRating().asLiveData()
}