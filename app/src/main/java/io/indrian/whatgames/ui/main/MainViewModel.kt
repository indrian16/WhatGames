package io.indrian.whatgames.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import io.indrian.core.domain.usecase.GameUseCase

class MainViewModel(gameUseCase: GameUseCase) : ViewModel() {

    val games = gameUseCase.getGames().asLiveData()
}