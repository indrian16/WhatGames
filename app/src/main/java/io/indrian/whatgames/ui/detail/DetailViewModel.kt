package io.indrian.whatgames.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import io.indrian.core.domain.usecase.GameUseCase

class DetailViewModel(private val gameUseCase: GameUseCase) : ViewModel() {

    fun getGame(id: Int = 0) = gameUseCase.getDetailsGames(id).asLiveData()
}