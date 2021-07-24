package io.indrian.core.domain.usecase

import io.indrian.core.data.Resource
import io.indrian.core.domain.model.Game
import kotlinx.coroutines.flow.Flow

interface GameUseCase {
    fun getGamesReleased(): Flow<Resource<List<Game>>>
    fun getGamesRating(): Flow<Resource<List<Game>>>
    fun getFavoriteGames(): Flow<List<Game>>
    fun setFavoriteGame(game: Game, state: Boolean)
}