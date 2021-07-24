package io.indrian.core.domain.repository

import io.indrian.core.data.Resource
import io.indrian.core.domain.model.Game
import kotlinx.coroutines.flow.Flow

interface IGameRepository {

    fun getGames(): Flow<Resource<List<Game>>>
    fun getFavoriteGame(): Flow<List<Game>>
    fun setFavoriteGame(game: Game, state: Boolean)
}