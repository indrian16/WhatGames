package io.indrian.core.domain.repository

import io.indrian.core.data.Resource
import io.indrian.core.domain.model.Game
import io.indrian.core.domain.model.Genre
import kotlinx.coroutines.flow.Flow

interface IGameRepository {

    fun getGamesReleased(): Flow<Resource<List<Game>>>
    fun getGamesRating(): Flow<Resource<List<Game>>>
    fun searchGames(search: String): Flow<Resource<List<Game>>>
    fun getFavoriteGame(): Flow<List<Game>>
    fun setFavoriteGame(game: Game, state: Boolean)

    fun getGenres(): Flow<Resource<List<Genre>>>
}