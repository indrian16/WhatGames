package io.indrian.core.domain.usecase

import io.indrian.core.data.Resource
import io.indrian.core.domain.model.Game
import io.indrian.core.domain.model.Genre
import kotlinx.coroutines.flow.Flow

interface GameUseCase {
    fun getGamesReleased(): Flow<Resource<List<Game>>>
    fun getGamesRating(): Flow<Resource<List<Game>>>
    fun getDetailsGames(id: Int): Flow<Resource<Game>>
    fun searchGames(search: CharSequence?): Flow<Resource<List<Game>>>
    fun getFavoriteGames(): Flow<List<Game>>
    suspend fun setFavoriteGame(id: Int): Game

    fun getGenres(): Flow<Resource<List<Genre>>>
}