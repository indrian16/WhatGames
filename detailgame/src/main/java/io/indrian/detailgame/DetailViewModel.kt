package io.indrian.detailgame

import androidx.lifecycle.*
import io.indrian.core.domain.model.Game
import io.indrian.core.domain.usecase.GameUseCase
import kotlinx.coroutines.launch

class DetailViewModel(private val gameUseCase: GameUseCase) : ViewModel() {

    private val _updateFavorite = MutableLiveData<Game>()
    val updateFavorite: LiveData<Game> get() = _updateFavorite

    fun getGame(id: Int = 0) = gameUseCase.getDetailsGames(id).asLiveData()

    fun setFavorite(id: Int) {
        viewModelScope.launch {
            _updateFavorite.value = gameUseCase.setFavoriteGame(id)
        }
    }
}