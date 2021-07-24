package io.indrian.whatgames.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.indrian.core.data.Resource
import io.indrian.core.domain.model.Game
import io.indrian.core.domain.usecase.GameUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SearchViewModel(val gameUseCase: GameUseCase) : ViewModel() {

    private val _searchGames = MutableLiveData<Resource<List<Game>>>()
    val searchGames: LiveData<Resource<List<Game>>> get() = _searchGames

    fun searchGames(search: String) {
        viewModelScope.launch {
            gameUseCase.searchGames(search).collect {
                _searchGames.value = it
            }
        }
    }
}