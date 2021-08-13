package io.indrian.core.domain.usecase

import io.indrian.core.data.Resource
import io.indrian.core.domain.model.Game
import io.indrian.core.domain.model.Genre
import io.indrian.core.domain.repository.IGameRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber

class GameInteractor(private val gameRepository: IGameRepository) : GameUseCase {

    override fun getGamesReleased(): Flow<Resource<List<Game>>> = gameRepository.getGamesReleased()
    override fun getGamesRating(): Flow<Resource<List<Game>>> = gameRepository.getGamesRating()
    override fun getDetailsGames(id: Int): Flow<Resource<Game>> = gameRepository.getDetailsGames(id)

    override fun searchGames(search: CharSequence?): Flow<Resource<List<Game>>> {
        Timber.d("searchGames: $search")
        return if (!search.isNullOrEmpty()) {
            gameRepository.searchGames(search.toString())
        } else {
            Timber.d("search is empty")
            flow {
                Resource.Success(null)
            }
        }
    }

    override fun getFavoriteGames(): Flow<List<Game>> = gameRepository.getFavoriteGame()
    override suspend fun setFavoriteGame(id: Int) = gameRepository.setFavoriteGame(id)

    override fun getGenres(): Flow<Resource<List<Genre>>> = gameRepository.getGenres()
}