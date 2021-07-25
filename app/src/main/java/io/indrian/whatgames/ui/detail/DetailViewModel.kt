package io.indrian.whatgames.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import io.indrian.core.domain.usecase.GameUseCase
import kotlinx.coroutines.launch

class DetailViewModel(private val gameUseCase: GameUseCase) : ViewModel() {

    fun getGame(id: Int = 0) = gameUseCase.getDetailsGames(id).asLiveData()

    fun setFavorite(id: Int) {
        viewModelScope.launch {
            gameUseCase.setFavoriteGame(id)
        }
    }
}