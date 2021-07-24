package io.indrian.core.domain.usecase

import io.indrian.core.data.Resource
import io.indrian.core.domain.model.Game
import io.indrian.core.domain.model.Genre
import io.indrian.core.domain.repository.IGameRepository
import kotlinx.coroutines.flow.Flow

class GameInteractor(private val gameRepository: IGameRepository) : GameUseCase {

    override fun getGamesReleased(): Flow<Resource<List<Game>>> = gameRepository.getGamesReleased()
    override fun getGamesRating(): Flow<Resource<List<Game>>> = gameRepository.getGamesRating()
    override fun getFavoriteGames(): Flow<List<Game>> = gameRepository.getFavoriteGame()
    override fun setFavoriteGame(game: Game, state: Boolean) = gameRepository.setFavoriteGame(game, state)

    override fun getGenres(): Flow<Resource<List<Genre>>> = gameRepository.getGenres()
}