package io.indrian.core.domain.usecase

import io.indrian.core.data.Resource
import io.indrian.core.domain.model.Game
import io.indrian.core.domain.model.Genre
import io.indrian.core.domain.repository.IGameRepository
import kotlinx.coroutines.flow.Flow

class GameInteractor(private val gameRepository: IGameRepository) : GameUseCase {

    override fun getGamesReleased(): Flow<Resource<List<Game>>> = gameRepository.getGamesReleased()
    override fun getGamesRating(): Flow<Resource<List<Game>>> = gameRepository.getGamesRating()
    override fun getDetailsGames(id: Int): Flow<Resource<Game>> = gameRepository.getDetailsGames(id)

    override fun searchGames(search: String): Flow<Resource<List<Game>>> = gameRepository.searchGames(search)

    override fun getFavoriteGames(): Flow<List<Game>> = gameRepository.getFavoriteGame()
    override suspend fun setFavoriteGame(id: Int) = gameRepository.setFavoriteGame(id)

    override fun getGenres(): Flow<Resource<List<Genre>>> = gameRepository.getGenres()
}